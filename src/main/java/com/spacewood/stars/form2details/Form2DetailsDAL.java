/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.form2details;

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
 * @author User
 */
@Repository
public class Form2DetailsDAL {

    public static final class Columns {

        public static final String ID = "id";
        public static final String EMPLOYEE_ID = "employee_id";
        public static final String PARAMETER = "parameter";
        public static final String WEIGHTAGE = "weightage";
        public static final String RATING = "rating";
        public static final String RATING_SCORE = "rating_score";
        public static final String YEAR = "year";
    }

    public static final String TABLE_NAME = "form2_details";

    private final SimpleJdbcInsert insertForm2Details;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Form2DetailsDAL(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        insertForm2Details = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingColumns(
                        Columns.EMPLOYEE_ID,
                        Columns.PARAMETER,
                        Columns.WEIGHTAGE,
                        Columns.RATING,
                        Columns.RATING_SCORE,
                        Columns.YEAR
                )
                .usingGeneratedKeyColumns(Columns.ID);
    }

    public List<Form2Details> findAll(Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{offset}, new BeanPropertyRowMapper<>(Form2Details.class));
    }

    public List<Form2Details> findAllList() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(Form2Details.class));
    }

    public Form2Details findById(Integer id) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new BeanPropertyRowMapper<>(Form2Details.class));
    }

    public List<Form2Details> findByEmployeeId(Integer employeeId) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.EMPLOYEE_ID + " = ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{employeeId}, new BeanPropertyRowMapper<>(Form2Details.class));
    }

    public Form2Details insert(Form2Details form2Details) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Columns.EMPLOYEE_ID, form2Details.getEmployeeId());
        parameters.put(Columns.PARAMETER, form2Details.getParameter().name());
        parameters.put(Columns.WEIGHTAGE, form2Details.getWeightage());
        parameters.put(Columns.RATING, form2Details.getRating());
        parameters.put(Columns.RATING_SCORE, form2Details.getRatingScore());
        parameters.put(Columns.YEAR, form2Details.getYear());
        Number newId = insertForm2Details.executeAndReturnKey(parameters);
        form2Details = findById(newId.intValue());
        return form2Details;
    }

    public void delete(Integer id) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET deleted=? WHERE " + Columns.ID + "=?";
        jdbcTemplate.update(sqlQuery, new Object[]{true, id});
    }

    public Form2Details update(Form2Details form2Details) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET "
                + Columns.EMPLOYEE_ID + " = ?, "
                + Columns.PARAMETER + " = ?, "
                + Columns.WEIGHTAGE + " = ?,"
                + Columns.RATING + " = ?,"
                + Columns.RATING_SCORE + " = ?,"
                + Columns.YEAR + " = ?  WHERE " + Columns.ID + " = ?";
        Number updatedCount = jdbcTemplate.update(sqlQuery,
                new Object[]{
                    form2Details.getEmployeeId(),
                    form2Details.getParameter().name(),
                    form2Details.getWeightage(),
                    form2Details.getRating(),
                    form2Details.getRatingScore(),
                    form2Details.getYear(),
                    form2Details.getId()
                });
        form2Details = findById(form2Details.getId());
        return form2Details;
    }
}
