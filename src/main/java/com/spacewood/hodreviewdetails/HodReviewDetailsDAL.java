/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.hodreviewdetails;

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
public class HodReviewDetailsDAL {

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

    private final SimpleJdbcInsert insertHodReviewDetails;
    private final JdbcTemplate jdbcTemplate;
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public HodReviewDetailsDAL(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        insertHodReviewDetails = new SimpleJdbcInsert(jdbcTemplate)
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

    public List<HodReviewDetails> findAll(Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{offset}, hodReviewRowMapper);
    }

    public List<HodReviewDetails> findAllList() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, hodReviewRowMapper);
    }

    public HodReviewDetails findById(Integer id) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, hodReviewRowMapper);
    }

    public HodReviewDetails findByEmployeeId(Integer employeeId) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.EMPLOYEE_ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{employeeId}, hodReviewRowMapper);
    }

    public HodReviewDetails insert(HodReviewDetails hodReviewDetails) throws JsonProcessingException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Columns.EMPLOYEE_ID, hodReviewDetails.getEmployeeId());
        parameters.put(Columns.PERFORMANCE, hodReviewDetails.getPerformance());
        parameters.put(Columns.CHALLENGE, hodReviewDetails.getChallenge());
        parameters.put(Columns.TRAINING, hodReviewDetails.getTraining());
        parameters.put(Columns.EXPECTATION, hodReviewDetails.getExpectation());
        parameters.put(Columns.NO_COMMENT, hodReviewDetails.getNoComment());
        parameters.put(Columns.DIR_COMMENT, hodReviewDetails.getDirComment());
        parameters.put(Columns.NO_UPDATED_DATE, hodReviewDetails.getNoUpdatedDate());
        parameters.put(Columns.DIR_UPDATED_DATE, hodReviewDetails.getDirUpdatedDate());
        parameters.put(Columns.TAGS, hodReviewDetails.getTags() == null ? "[]" : mapper.writeValueAsString(hodReviewDetails.getTags()));

        Number newId = insertHodReviewDetails.executeAndReturnKey(parameters);
        hodReviewDetails = findById(newId.intValue());
        return hodReviewDetails;
    }

    public void delete(Integer id) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET deleted=? WHERE " + Columns.ID + "=?";
        jdbcTemplate.update(sqlQuery, new Object[]{true, id});
    }

    public HodReviewDetails update(HodReviewDetails additionaldetails) throws JsonProcessingException {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET "
                + Columns.EMPLOYEE_ID + " = ?, "
                + Columns.PERFORMANCE + " = ?, "
                + Columns.CHALLENGE + " = ?,"
                + Columns.TRAINING + " = ?,"
                + Columns.EXPECTATION + " = ?,"
                + Columns.NO_COMMENT + " = ?,"
                + Columns.DIR_COMMENT + " = ?,"
                + Columns.NO_UPDATED_DATE + " = ?,"
                + Columns.DIR_UPDATED_DATE + " = ?,"
                + Columns.TAGS + " = ?  WHERE " + Columns.ID + " = ?";
        Number updatedCount = jdbcTemplate.update(sqlQuery,
                new Object[]{
                    additionaldetails.getEmployeeId(),
                    additionaldetails.getPerformance(),
                    additionaldetails.getChallenge(),
                    additionaldetails.getTraining(),
                    additionaldetails.getExpectation(),
                    additionaldetails.getNoComment(),
                    additionaldetails.getDirComment(),
                    additionaldetails.getNoUpdatedDate(),
                    additionaldetails.getDirUpdatedDate(),
                    additionaldetails.getTags() == null ? "[]" : mapper.writeValueAsString(additionaldetails.getTags()),
                    additionaldetails.getId()
                });
        additionaldetails = findById(additionaldetails.getId());
        return additionaldetails;
    }

    private final RowMapper<HodReviewDetails> hodReviewRowMapper = new RowMapper<HodReviewDetails>() {

        @Override
        public HodReviewDetails mapRow(ResultSet rs, int i) throws SQLException {
            HodReviewDetails hodReviewConstraint = new HodReviewDetails();
            hodReviewConstraint.setId(rs.getInt(Columns.ID));
            hodReviewConstraint.setEmployeeId(rs.getInt(Columns.EMPLOYEE_ID));
            hodReviewConstraint.setPerformance(rs.getString(Columns.PERFORMANCE));
            hodReviewConstraint.setChallenge(rs.getString(Columns.CHALLENGE));
            hodReviewConstraint.setTraining(rs.getString(Columns.TRAINING));
            hodReviewConstraint.setExpectation(rs.getString(Columns.EXPECTATION));
            hodReviewConstraint.setNoComment(rs.getString(Columns.NO_COMMENT));
            hodReviewConstraint.setDirComment(rs.getString(Columns.DIR_COMMENT));
            hodReviewConstraint.setNoUpdatedDate(rs.getDate(Columns.NO_UPDATED_DATE));
            hodReviewConstraint.setDirUpdatedDate(rs.getDate(Columns.DIR_UPDATED_DATE));
            String tagsList = rs.getString(Columns.TAGS);
            try {
                ObjectMapper mapper = new ObjectMapper();
                List<Integer> tags = mapper.readValue(tagsList, new TypeReference<List<Integer>>() {
                });
                hodReviewConstraint.setTags(tags);
            } catch (IOException ex) {
                throw new RuntimeException("Error parsing partiesList: '" + tagsList + "' ", ex);
            }            
            return hodReviewConstraint;
        }

    };
}
