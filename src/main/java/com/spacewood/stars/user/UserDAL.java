/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.user;

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
public class UserDAL {

    public static final class Columns {

        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String ROLE = "role";
        public static final String NAME = "name";
        public static final String EMPLOYEE_ID = "employee_id";
        public static final String COMPANY_ID = "company_id";
        public static final String MOBILE_NO = "mobile_no";
    }

    public static final String TABLE_NAME = "user";

    private final SimpleJdbcInsert insertUser;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAL(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingColumns(
                        Columns.USERNAME,
                        Columns.PASSWORD,
                        Columns.ROLE,
                        Columns.NAME,
                        Columns.EMPLOYEE_ID,
                        Columns.COMPANY_ID,
                        Columns.MOBILE_NO
                )
                .usingGeneratedKeyColumns(Columns.ID);
    }

    public List<User> findAll(Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{offset}, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findUsersByCompany(Integer companyId, Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.COMPANY_ID + " = ? ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{companyId, offset}, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findSfplUsers(Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND company_id = 3 ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{offset}, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findSosUsers(Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND company_id = 4 ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{offset}, new BeanPropertyRowMapper<>(User.class));
    }

    public User findById(Integer id) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    public User findByEmployeeId(Integer employeeId) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.EMPLOYEE_ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{employeeId}, new BeanPropertyRowMapper<>(User.class));
    }

    public User findByUsername(String username) {
        System.out.println("Username in DAL :" + username);
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.USERNAME + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{username}, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findHod() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND role='ROLE_HOD'";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findHodByCompanyId(Integer companyId) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND role='ROLE_HOD' AND " + Columns.COMPANY_ID + " = ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{companyId}, new BeanPropertyRowMapper<>(User.class));
    }
    
    public List<User> findDirectorByCompanyId(Integer companyId) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND role='ROLE_DIRECTOR' AND " + Columns.COMPANY_ID + " = ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{companyId}, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findByNameLike(String username) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND lower(username) LIKE?";
        String userNameLike = "%" + username.toLowerCase() + "%";
        return jdbcTemplate.query(sqlQuery, new Object[]{userNameLike}, new BeanPropertyRowMapper<>(User.class));
    }

    public User insert(User user) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Columns.USERNAME, user.getUsername());
        parameters.put(Columns.PASSWORD, user.getPassword());
        parameters.put(Columns.ROLE, user.getRole().name());
        parameters.put(Columns.NAME, user.getName());
        parameters.put(Columns.EMPLOYEE_ID, user.getEmployeeId());
        parameters.put(Columns.COMPANY_ID, user.getCompanyId());
        parameters.put(Columns.MOBILE_NO, user.getMobileNo());
        Number newId = insertUser.executeAndReturnKey(parameters);
        user = findById(newId.intValue());
        return user;
    }

    public void delete(Integer id) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET deleted=? WHERE " + Columns.ID + "=?";
        jdbcTemplate.update(sqlQuery, new Object[]{true, id});
    }

    public User update(User user) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET "
                + Columns.USERNAME + " = ?,"
                + Columns.PASSWORD + " = ?, "
                + Columns.ROLE + " = ?,"
                + Columns.NAME + " = ?,"
                + Columns.EMPLOYEE_ID + " = ?,"
                + Columns.COMPANY_ID + " = ?,"
                + Columns.MOBILE_NO + " = ? WHERE " + Columns.ID + " = ?";
        Number updatedCount = jdbcTemplate.update(sqlQuery,
                new Object[]{
                    user.getUsername(),
                    user.getPassword(),
                    user.getRole().name(),
                    user.getName(),
                    user.getEmployeeId(),
                    user.getCompanyId(),
                    user.getMobileNo(),
                    user.getId()
                });
        user = findById(user.getId());
        return user;
    }
}
