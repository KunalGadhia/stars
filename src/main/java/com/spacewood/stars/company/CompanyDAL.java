/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.company;

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
 * @author user
 */
@Repository
public class CompanyDAL {
   public static final class Columns {

        public static final String ID = "id";
        public static final String COMPANY_NAME = "company_name";
        public static final String ADDRESS = "address";        
    }

    public static final String TABLE_NAME = "company_master";

    private final SimpleJdbcInsert insertCompany;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CompanyDAL(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        insertCompany = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingColumns(
                        Columns.COMPANY_NAME,
                        Columns.ADDRESS
                )
                .usingGeneratedKeyColumns(Columns.ID);
    }

    public List<Company> findAll(Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{offset}, new BeanPropertyRowMapper<>(Company.class));
    }

    public List<Company> findAllList() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(Company.class));
    }

    public Company findById(Integer id) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new BeanPropertyRowMapper<>(Company.class));
    }

//    public Company findByEmployeeId(Integer employeeId) {
//        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.EMPLOYEE_ID + " = ?";
//        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{employeeId}, new BeanPropertyRowMapper<>(AdditionalDetails.class));
//    }

    public Company insert(Company company) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Columns.COMPANY_NAME, company.getCompanyName());
        parameters.put(Columns.ADDRESS, company.getAddress());        
        Number newId = insertCompany.executeAndReturnKey(parameters);
        company = findById(newId.intValue());
        return company;
    }

    public void delete(Integer id) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET deleted=? WHERE " + Columns.ID + "=?";
        jdbcTemplate.update(sqlQuery, new Object[]{true, id});
    }

    public Company update(Company company) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET "
                + Columns.COMPANY_NAME + " = ?, "                
                + Columns.ADDRESS + " = ?  WHERE " + Columns.ID + " = ?";
        Number updatedCount = jdbcTemplate.update(sqlQuery,
                new Object[]{
                    company.getCompanyName(),
                    company.getAddress(),                    
                    company.getId()
                });
        company = findById(company.getId());
        return company;
    } 
}
