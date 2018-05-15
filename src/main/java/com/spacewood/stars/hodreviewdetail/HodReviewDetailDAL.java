/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.hodreviewdetail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 *
 * @author webdesign
 */
@Repository
public class HodReviewDetailDAL {

    public static final class Columns {

        public static final String ID = "id";
        public static final String EMPLOYEE_ID = "employee_id";
        public static final String PERFORMANCE = "performance";
        public static final String CHALLENGE = "challenge";
        public static final String TRAINING = "training";
        public static final String EXPECTATION = "expectation";
        public static final String NO_COMMENT = "no_comment";
        public static final String DIR_COMMENT = "dir_comment";
        public static final String NO_UPDATED_DATE = "no_updated_date";
        public static final String DIR_UPDATED_DATE = "dir_updated_date";
        public static final String TAGS = "tags";
    }

    public static final String TABLE_NAME = "hod_review_details";

    private final SimpleJdbcInsert insertHodReviewDetail;
    private final JdbcTemplate jdbcTemplate;
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public HodReviewDetailDAL(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        insertHodReviewDetail = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingColumns(
                        Columns.EMPLOYEE_ID,
                        Columns.PERFORMANCE,
                        Columns.CHALLENGE,
                        Columns.TRAINING,
                        Columns.EXPECTATION,
                        Columns.NO_COMMENT,
                        Columns.DIR_COMMENT,
                        Columns.NO_UPDATED_DATE,
                        Columns.DIR_UPDATED_DATE,
                        Columns.TAGS
                )
                .usingGeneratedKeyColumns(Columns.ID);
    }

    public List<HodReviewDetail> findAll(Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{offset}, hodReviewRowMapper);
    }

    public HodReviewDetail findById(Integer id) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, hodReviewRowMapper);
    }
    
    public HodReviewDetail findByEmployeeId(Integer employeeId) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.EMPLOYEE_ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{employeeId}, hodReviewRowMapper);
    }

//    public User findByUsername(String username) {
//        System.out.println("Inside DAL with String :" + username);
//        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.USERNAME + " = ?";
//        System.out.println("SQL String :" + sqlQuery);
//        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{username}, userRowMapper);
//    }
//
//    public List<User> findByNameLike(String username) {
//        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND lower(username) LIKE?";
//        String userNameLike = "%" + username.toLowerCase() + "%";
//        return jdbcTemplate.query(sqlQuery, new Object[]{userNameLike}, userRowMapper);
//    }
    public HodReviewDetail insert(HodReviewDetail hodReviewDetail) throws JsonProcessingException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Columns.EMPLOYEE_ID, hodReviewDetail.getEmployeeId());
        parameters.put(Columns.PERFORMANCE, hodReviewDetail.getPerformance());
        parameters.put(Columns.CHALLENGE, hodReviewDetail.getChallenge());
        parameters.put(Columns.TRAINING, hodReviewDetail.getTraining());
        parameters.put(Columns.EXPECTATION, hodReviewDetail.getExpectation());
        parameters.put(Columns.NO_COMMENT, hodReviewDetail.getNoComment());
        parameters.put(Columns.DIR_COMMENT, hodReviewDetail.getDirComment());
        parameters.put(Columns.NO_UPDATED_DATE, hodReviewDetail.getNoUpdatedDate());
        parameters.put(Columns.DIR_UPDATED_DATE, hodReviewDetail.getDirUpdatedDate());
        parameters.put(Columns.TAGS, hodReviewDetail.getTags() == null ? "[]" : mapper.writeValueAsString(hodReviewDetail.getTags()));
        Number newId = insertHodReviewDetail.executeAndReturnKey(parameters);
        hodReviewDetail = findById(newId.intValue());
        return hodReviewDetail;
    }

    public void delete(Integer id) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET deleted=? WHERE " + Columns.ID + "=?";
        jdbcTemplate.update(sqlQuery, new Object[]{true, id});
    }

    public HodReviewDetail update(HodReviewDetail hodReviewDetail) throws JsonProcessingException {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET "
                + Columns.EMPLOYEE_ID + " = ?,"
                + Columns.PERFORMANCE + " = ?, "
                + Columns.CHALLENGE + " = ?,"
                + Columns.TRAINING + " = ?, "
                + Columns.EXPECTATION + " = ?,"
                + Columns.NO_COMMENT + " = ?, "
                + Columns.DIR_COMMENT + " = ?,"
                + Columns.NO_UPDATED_DATE + " = ?,"
                + Columns.DIR_UPDATED_DATE + " = ?,"
                + Columns.TAGS + " = ? WHERE " + Columns.ID + " = ?";
        Number updatedCount = jdbcTemplate.update(sqlQuery,
                new Object[]{
                    hodReviewDetail.getEmployeeId(),
                    hodReviewDetail.getPerformance(),
                    hodReviewDetail.getChallenge(),
                    hodReviewDetail.getTraining(),
                    hodReviewDetail.getExpectation(),
                    hodReviewDetail.getNoComment(),
                    hodReviewDetail.getDirComment(),
                    hodReviewDetail.getNoUpdatedDate(),
                    hodReviewDetail.getDirUpdatedDate(),
                    hodReviewDetail.getTags() == null ? "[]" : mapper.writeValueAsString(hodReviewDetail.getTags()),
                    hodReviewDetail.getId()
                });
        hodReviewDetail = findById(hodReviewDetail.getId());
        return hodReviewDetail;
    }

    private final RowMapper<HodReviewDetail> hodReviewRowMapper = new RowMapper<HodReviewDetail>() {

        @Override
        public HodReviewDetail mapRow(ResultSet rs, int i) throws SQLException {
            HodReviewDetail hodReviewDetailConstraint = new HodReviewDetail();
            hodReviewDetailConstraint.setId(rs.getInt(Columns.ID));
            hodReviewDetailConstraint.setEmployeeId(rs.getInt(Columns.EMPLOYEE_ID));
            hodReviewDetailConstraint.setPerformance(rs.getString(Columns.PERFORMANCE));
            hodReviewDetailConstraint.setChallenge(rs.getString(Columns.CHALLENGE));
            hodReviewDetailConstraint.setTraining(rs.getString(Columns.TRAINING));
            hodReviewDetailConstraint.setExpectation(rs.getString(Columns.EXPECTATION));
            hodReviewDetailConstraint.setNoComment(rs.getString(Columns.NO_COMMENT));
            hodReviewDetailConstraint.setDirComment(rs.getString(Columns.DIR_COMMENT));
            hodReviewDetailConstraint.setNoUpdatedDate(rs.getTimestamp(Columns.NO_UPDATED_DATE));
            hodReviewDetailConstraint.setDirUpdatedDate(rs.getTimestamp(Columns.DIR_UPDATED_DATE));

            String tagsList = rs.getString(Columns.TAGS);
            try {
                ObjectMapper mapper = new ObjectMapper();
                List<Integer> tags = mapper.readValue(tagsList, new TypeReference<List<Integer>>() {
                });
                hodReviewDetailConstraint.setTags(tags);
            } catch (IOException ex) {
                throw new RuntimeException("Error parsing hod Review List: '" + tagsList + "' ", ex);
            }            
            return hodReviewDetailConstraint;
        }

    };
}
