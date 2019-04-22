/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.form3details;

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
public class Form3DetailsDAL {
    public static final class Columns {

        public static final String ID = "id";
        public static final String EMPLOYEE_ID = "employee_id";
        public static final String COMMENT_VISIBLE = "comment_visible";
        public static final String ANSWER1_EMP = "answer1_emp";
        public static final String ANSWER1_HOD = "answer1_hod";
        public static final String ANSWER2_EMP = "answer2_emp";
        public static final String ANSWER2_HOD = "answer2_hod";
        public static final String ANSWER3_EMP = "answer3_emp";
        public static final String ANSWER3_HOD = "answer3_hod";
        public static final String ANSWER4_EMP = "answer4_emp";
        public static final String ANSWER4_HOD = "answer4_hod";
        public static final String ANSWER5_EMP = "answer5_emp";
        public static final String ANSWER5_HOD = "answer5_hod";
        public static final String ANSWER6_EMP = "answer6_emp";
        public static final String ANSWER6_HOD = "answer6_hod";
        public static final String ANSWER7_EMP = "answer7_emp";
        public static final String ANSWER7_HOD = "answer7_hod";
        public static final String ANSWER8_EMP = "answer8_emp";
        public static final String ANSWER8_HOD = "answer8_hod";
        public static final String ANSWER9_EMP = "answer9_emp";
        public static final String ANSWER9_HOD = "answer9_hod";
        public static final String ANSWER10_EMP = "answer10_emp";
        public static final String ANSWER10_HOD = "answer10_hod";
        public static final String REMARKS = "remarks";
    }

    public static final String TABLE_NAME = "form3_details";

    private final SimpleJdbcInsert insertForm3Details;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Form3DetailsDAL(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        insertForm3Details = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingColumns(
                        Columns.EMPLOYEE_ID,
                        Columns.COMMENT_VISIBLE,
                        Columns.ANSWER1_EMP,
                        Columns.ANSWER1_HOD,
                        Columns.ANSWER2_EMP,
                        Columns.ANSWER2_HOD,
                        Columns.ANSWER3_EMP,
                        Columns.ANSWER3_HOD,
                        Columns.ANSWER4_EMP,
                        Columns.ANSWER4_HOD,
                        Columns.ANSWER5_EMP,
                        Columns.ANSWER5_HOD,
                        Columns.ANSWER6_EMP,
                        Columns.ANSWER6_HOD,
                        Columns.ANSWER7_EMP,
                        Columns.ANSWER7_HOD,
                        Columns.ANSWER8_EMP,
                        Columns.ANSWER8_HOD,
                        Columns.ANSWER9_EMP,
                        Columns.ANSWER9_HOD,
                        Columns.ANSWER10_EMP,
                        Columns.ANSWER10_HOD,                        
                        Columns.REMARKS
                )
                .usingGeneratedKeyColumns(Columns.ID);
    }

    public List<Form3Details> findAll(Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{offset}, new BeanPropertyRowMapper<>(Form3Details.class));
    }

    public List<Form3Details> findAllList() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(Form3Details.class));
    }

    public Form3Details findById(Integer id) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new BeanPropertyRowMapper<>(Form3Details.class));
    }

    public Form3Details findByEmployeeId(Integer employeeId) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.EMPLOYEE_ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{employeeId}, new BeanPropertyRowMapper<>(Form3Details.class));
    }

    public Form3Details insert(Form3Details form3Details) {
        System.out.println("IN DAL :"+form3Details);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Columns.EMPLOYEE_ID, form3Details.getEmployeeId());
        parameters.put(Columns.COMMENT_VISIBLE, form3Details.getCommentVisible());
        parameters.put(Columns.ANSWER1_EMP, form3Details.getAnswer1Emp());
        parameters.put(Columns.ANSWER1_HOD, form3Details.getAnswer1Hod());
        parameters.put(Columns.ANSWER2_EMP, form3Details.getAnswer2Emp());
        parameters.put(Columns.ANSWER2_HOD, form3Details.getAnswer2Hod());
        parameters.put(Columns.ANSWER3_EMP, form3Details.getAnswer3Emp());
        parameters.put(Columns.ANSWER3_HOD, form3Details.getAnswer3Hod());
        parameters.put(Columns.ANSWER4_EMP, form3Details.getAnswer4Emp());
        parameters.put(Columns.ANSWER4_HOD, form3Details.getAnswer4Hod());
        parameters.put(Columns.ANSWER5_EMP, form3Details.getAnswer5Emp());
        parameters.put(Columns.ANSWER5_HOD, form3Details.getAnswer5Hod());
        parameters.put(Columns.ANSWER6_EMP, form3Details.getAnswer6Emp());
        parameters.put(Columns.ANSWER6_HOD, form3Details.getAnswer6Hod());
        parameters.put(Columns.ANSWER7_EMP, form3Details.getAnswer7Emp());
        parameters.put(Columns.ANSWER7_HOD, form3Details.getAnswer7Hod());
        parameters.put(Columns.ANSWER8_EMP, form3Details.getAnswer8Emp());
        parameters.put(Columns.ANSWER8_HOD, form3Details.getAnswer8Hod());
        parameters.put(Columns.ANSWER9_EMP, form3Details.getAnswer9Emp());
        parameters.put(Columns.ANSWER9_HOD, form3Details.getAnswer9Hod());
        parameters.put(Columns.ANSWER10_EMP, form3Details.getAnswer10Emp());
        parameters.put(Columns.ANSWER10_HOD, form3Details.getAnswer10Hod());
        parameters.put(Columns.REMARKS, form3Details.getRemarks());        
        Number newId = insertForm3Details.executeAndReturnKey(parameters);
        form3Details = findById(newId.intValue());
        return form3Details;
    }

    public void delete(Integer id) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET deleted=? WHERE " + Columns.ID + "=?";
        jdbcTemplate.update(sqlQuery, new Object[]{true, id});
    }

    public Form3Details update(Form3Details form3Details) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET "
                + Columns.EMPLOYEE_ID + " = ?, "
                + Columns.COMMENT_VISIBLE + " = ?, "
                + Columns.ANSWER1_EMP + " = ?,"
                + Columns.ANSWER1_HOD + " = ?,"
                + Columns.ANSWER2_EMP + " = ?,"
                + Columns.ANSWER2_HOD + " = ?,"
                + Columns.ANSWER3_EMP + " = ?,"
                + Columns.ANSWER3_HOD + " = ?,"
                + Columns.ANSWER4_EMP + " = ?,"
                + Columns.ANSWER4_HOD + " = ?,"
                + Columns.ANSWER5_EMP + " = ?,"
                + Columns.ANSWER5_HOD + " = ?,"
                + Columns.ANSWER6_EMP + " = ?,"
                + Columns.ANSWER6_HOD + " = ?,"
                + Columns.ANSWER7_EMP + " = ?,"
                + Columns.ANSWER7_HOD + " = ?,"
                + Columns.ANSWER8_EMP + " = ?,"
                + Columns.ANSWER8_HOD + " = ?,"
                + Columns.ANSWER9_EMP + " = ?,"
                + Columns.ANSWER9_HOD + " = ?,"
                + Columns.ANSWER10_EMP + " = ?,"
                + Columns.ANSWER10_HOD + " = ?,"                
                + Columns.REMARKS + " = ?  WHERE " + Columns.ID + " = ?";
        Number updatedCount = jdbcTemplate.update(sqlQuery,
                new Object[]{
                    form3Details.getEmployeeId(),
                    form3Details.getCommentVisible(),
                    form3Details.getAnswer1Emp(),
                    form3Details.getAnswer1Hod(),
                    form3Details.getAnswer2Emp(),
                    form3Details.getAnswer2Hod(),
                    form3Details.getAnswer3Emp(),
                    form3Details.getAnswer3Hod(),
                    form3Details.getAnswer4Emp(),
                    form3Details.getAnswer4Hod(),
                    form3Details.getAnswer5Emp(),
                    form3Details.getAnswer5Hod(),
                    form3Details.getAnswer6Emp(),
                    form3Details.getAnswer6Hod(),
                    form3Details.getAnswer7Emp(),
                    form3Details.getAnswer7Hod(),
                    form3Details.getAnswer8Emp(),
                    form3Details.getAnswer8Hod(),
                    form3Details.getAnswer9Emp(),
                    form3Details.getAnswer9Hod(),
                    form3Details.getAnswer10Emp(),
                    form3Details.getAnswer10Hod(),
                    form3Details.getRemarks(),                    
                    form3Details.getId()
                });
        form3Details = findById(form3Details.getId());
        return form3Details;
    }
}
