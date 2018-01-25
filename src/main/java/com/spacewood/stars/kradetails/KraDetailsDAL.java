/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.kradetails;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 *
 * @author webdesign
 */
@Repository
public class KraDetailsDAL {
    public static final class Columns {

        public static final String ID = "id";
        public static final String KRA_PARAMETER = "kra_parameter";
        public static final String PERFORMANCE_OBJECTIVE = "performance_objective";
        public static final String MEASURABLE_TARGET = "measurable_target";
        public static final String MIR_MATRIX = "mir_matrix";
        public static final String WEIGHTAGE = "weightage";
        public static final String RATING = "rating";
        public static final String RATING_SCORE = "rating_score";
        public static final String EMPLOYEE_ID = "employee_id";
        public static final String YEAR = "year";
    }

    public static final String TABLE_NAME = "kra_details";

    private final SimpleJdbcInsert insertKraDetails;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public KraDetailsDAL(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        insertKraDetails = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingColumns(
                        Columns.KRA_PARAMETER,
                        Columns.PERFORMANCE_OBJECTIVE,
                        Columns.MEASURABLE_TARGET,
                        Columns.MIR_MATRIX,
                        Columns.WEIGHTAGE,
                        Columns.RATING,
                        Columns.RATING_SCORE,
                        Columns.EMPLOYEE_ID,
                        Columns.YEAR
                )
                .usingGeneratedKeyColumns(Columns.ID);
    }

    public List<KraDetails> findAll(Integer offset) {

        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE ORDER BY " + Columns.ID + " DESC LIMIT 5 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{offset}, new BeanPropertyRowMapper<>(KraDetails.class));
    }

    public KraDetails findById(Integer id) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new BeanPropertyRowMapper<>(KraDetails.class));
    }
    
    public List<KraDetails> findByEmployeeId(Integer employeeId) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.EMPLOYEE_ID + " = ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{employeeId}, new BeanPropertyRowMapper<>(KraDetails.class));
    }

//    public User findByUsername(String username) {
//        System.out.println("Username in DAL :" + username);
//        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.USERNAME + " = ?";
//        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{username}, new BeanPropertyRowMapper<>(User.class));
//    }

//    public List<User> findByNameLike(String username) {
//        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND lower(username) LIKE?";
//        String userNameLike = "%" + username.toLowerCase() + "%";
//        return jdbcTemplate.query(sqlQuery, new Object[]{userNameLike}, new BeanPropertyRowMapper<>(User.class));
//    }

    public KraDetails insert(KraDetails kraDetails) {
        Date date = new Date();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Columns.KRA_PARAMETER, kraDetails.getKraParameter().name());
        parameters.put(Columns.PERFORMANCE_OBJECTIVE, kraDetails.getPerformanceObjective());
        parameters.put(Columns.MEASURABLE_TARGET, kraDetails.getMeasurableTarget());
        if(kraDetails.getMirMatrix() == null){
         parameters.put(Columns.MIR_MATRIX, MIRMatrix.NOT_APPLICABLE);   
        }else{
         parameters.put(Columns.MIR_MATRIX, kraDetails.getMirMatrix().name());   
        }        
        parameters.put(Columns.WEIGHTAGE, kraDetails.getWeightage());
        parameters.put(Columns.RATING, kraDetails.getRating());
        parameters.put(Columns.RATING_SCORE, kraDetails.getRatingScore());
        parameters.put(Columns.EMPLOYEE_ID, kraDetails.getEmployeeId());
        parameters.put(Columns.YEAR, kraDetails.getYear());
        Number newId = insertKraDetails.executeAndReturnKey(parameters);
        kraDetails = findById(newId.intValue());
        return kraDetails;
    }

    public void delete(Integer id) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET deleted=? WHERE " + Columns.ID + "=?";
        jdbcTemplate.update(sqlQuery, new Object[]{true, id});
    }

    public KraDetails update(KraDetails kraDetails) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET "
                + Columns.KRA_PARAMETER + " = ?,"
                + Columns.PERFORMANCE_OBJECTIVE + " = ?, "
                + Columns.MEASURABLE_TARGET + " = ?,"
                + Columns.MIR_MATRIX + " = ?,"
                + Columns.WEIGHTAGE + " = ?,"
                + Columns.RATING + " = ?,"
                + Columns.RATING_SCORE + " = ?,"
                + Columns.EMPLOYEE_ID + " = ?,"
                + Columns.YEAR + " = ? WHERE " + Columns.ID + " = ?";
        Number updatedCount = jdbcTemplate.update(sqlQuery,
                new Object[]{
                    kraDetails.getKraParameter().name(),
                    kraDetails.getPerformanceObjective(),
                    kraDetails.getMeasurableTarget(),
                    kraDetails.getMirMatrix().name(),
                    kraDetails.getWeightage(),
                    kraDetails.getRating(),
                    kraDetails.getRatingScore(),
                    kraDetails.getEmployeeId(),
                    kraDetails.getYear(),
                    kraDetails.getId()
                });
        kraDetails = findById(kraDetails.getId());
        return kraDetails;
    }
}
