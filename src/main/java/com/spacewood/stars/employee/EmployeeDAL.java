/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.employee;

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
public class EmployeeDAL {

    public static final class Columns {

        public static final String ID = "id";
        public static final String EMP_NO = "emp_no";
        public static final String DOOR_CODE = "door_code";
        public static final String EMP_NAME = "emp_name";
        public static final String EMP_DOJ = "emp_doj";
        public static final String EMP_DOB = "emp_dob";
        public static final String DEPARTMENT = "department";
        public static final String GRADE = "grade";
        public static final String DESIGNATION = "designation";
        public static final String EDU_QUAL = "edu_qual";
        public static final String TOTAL_EXP = "total_exp";
        public static final String LOCATION = "location";
        public static final String REPORTING_TO = "reporting_to";
        public static final String LAST_PROMOTION = "last_promotion";
        public static final String DEPARTMENT_HEAD = "department_head";
        public static final String COMPANY_ID = "company_id";
        public static final String TEMPORARY_ADDRESS1 = "temporary_address1";
        public static final String TEMPORARY_ADDRESS2 = "temporary_address2";
        public static final String TEMPORARY_ADDRESS3 = "temporary_address3";
        public static final String TEMPORARY_ADDRESS4 = "temporary_address4";
        public static final String PERMANENT_ADDRESS1 = "permanent_address1";
        public static final String PERMANENT_ADDRESS2 = "permanent_address2";
        public static final String PERMANENT_ADDRESS3 = "permanent_address3";
        public static final String PERMANENT_ADDRESS4 = "permanent_address4";
        public static final String PERSONAL_CONTACT_NO = "personal_contact_no";
        public static final String OFFICIAL_CONTACT_NO = "official_contact_no";
        public static final String EMERGENCY_CONTACT_NO = "emergency_contact_no";
        public static final String OFFICIAL_MAIL_ID = "official_mail_id";
        public static final String PERSONAL_MAIL_ID = "personal_mail_id";
        public static final String GENDER = "gender";
        public static final String BLOODGROUP = "bloodgroup";
        public static final String PAN_NUMBER = "pan_number";
        public static final String AADHAR_NO = "aadhar_no";
        public static final String IMAGE = "image";
    }

    public static final String TABLE_NAME = "employee_master";

    private final SimpleJdbcInsert insertEmployee;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDAL(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        insertEmployee = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingColumns(
                        Columns.EMP_NO,
                        Columns.DOOR_CODE,
                        Columns.EMP_NAME,
                        Columns.EMP_DOJ,
                        Columns.EMP_DOB,
                        Columns.DEPARTMENT,
                        Columns.GRADE,
                        Columns.DESIGNATION,
                        Columns.EDU_QUAL,
                        Columns.TOTAL_EXP,
                        Columns.LOCATION,
                        Columns.REPORTING_TO,
                        Columns.LAST_PROMOTION,
                        Columns.DEPARTMENT_HEAD,
                        Columns.COMPANY_ID,
                        Columns.TEMPORARY_ADDRESS1,
                        Columns.TEMPORARY_ADDRESS2,
                        Columns.TEMPORARY_ADDRESS3,
                        Columns.TEMPORARY_ADDRESS4,
                        Columns.PERMANENT_ADDRESS1,
                        Columns.PERMANENT_ADDRESS2,
                        Columns.PERMANENT_ADDRESS3,
                        Columns.PERMANENT_ADDRESS4,
                        Columns.PERSONAL_CONTACT_NO,
                        Columns.OFFICIAL_CONTACT_NO,
                        Columns.EMERGENCY_CONTACT_NO,
                        Columns.OFFICIAL_MAIL_ID,
                        Columns.PERSONAL_MAIL_ID,
                        Columns.GENDER,
                        Columns.BLOODGROUP,
                        Columns.PAN_NUMBER,
                        Columns.AADHAR_NO
                        
                )
                .usingGeneratedKeyColumns(Columns.ID);
    }

    public List<Employee> findAll(Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{offset}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public List<Employee> findSfplEmployee(Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND company_id = 3 ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{offset}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public List<Employee> findSosEmployee(Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND company_id = 4 ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{offset}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public List<Employee> findEmployeeByCompany(Integer companyId, Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.COMPANY_ID + " = ? ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{companyId, offset}, new BeanPropertyRowMapper<>(Employee.class));
    }
    
    public List<Employee> findByCompany(Integer companyId) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.COMPANY_ID + " = ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{companyId}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public List<Employee> findAllList() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee findById(Integer id) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee findByName(String name) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.EMP_NAME + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{name}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public List<Employee> findByNameLike(String name) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND lower(emp_name) LIKE?";
        String nameLike = "%" + name.toLowerCase() + "%";
        return jdbcTemplate.query(sqlQuery, new Object[]{nameLike}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public List<Employee> findByEmpNumLike(String empNo) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND lower(emp_no) LIKE?";
        String nameLike = "%" + empNo.toLowerCase() + "%";
        return jdbcTemplate.query(sqlQuery, new Object[]{nameLike}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public List<Employee> findByNameLikeByCompany(Integer companyId, String name) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.COMPANY_ID + " = ? AND lower(emp_name) LIKE?";
        String nameLike = "%" + name.toLowerCase() + "%";
        return jdbcTemplate.query(sqlQuery, new Object[]{companyId, nameLike}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public List<Employee> findByDepartmentHead(Integer departmentHeadId) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.DEPARTMENT_HEAD + " = ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{departmentHeadId}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee insert(Employee employee) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Columns.EMP_NO, employee.getEmpNo());
        parameters.put(Columns.DOOR_CODE, employee.getDoorCode());
        parameters.put(Columns.EMP_NAME, employee.getEmpName());
        parameters.put(Columns.EMP_DOJ, employee.getEmpDoj());
        parameters.put(Columns.EMP_DOB, employee.getEmpDob());
        if (employee.getDepartment() == null) {
            parameters.put(Columns.DEPARTMENT, Department.NO_DEPARTMENT);
        } else {
            parameters.put(Columns.DEPARTMENT, employee.getDepartment().name());
        }
        parameters.put(Columns.GRADE, employee.getGrade());
        if (employee.getDesignation() == null) {
            parameters.put(Columns.DESIGNATION, Designation.NO_DESIGNATION);
        } else {
            parameters.put(Columns.DESIGNATION, employee.getDesignation());
        }
        parameters.put(Columns.EDU_QUAL, employee.getEduQual());
        parameters.put(Columns.TOTAL_EXP, employee.getTotalExp());
        parameters.put(Columns.LOCATION, employee.getLocation());
        parameters.put(Columns.REPORTING_TO, employee.getReportingTo());
        parameters.put(Columns.LAST_PROMOTION, employee.getLastPromotion());
        parameters.put(Columns.DEPARTMENT_HEAD, employee.getDepartmentHead());
        parameters.put(Columns.COMPANY_ID, employee.getCompanyId());
        parameters.put(Columns.TEMPORARY_ADDRESS1, employee.getTemporaryAddress1());
        parameters.put(Columns.TEMPORARY_ADDRESS2, employee.getTemporaryAddress2());
        parameters.put(Columns.TEMPORARY_ADDRESS3, employee.getTemporaryAddress3());
        parameters.put(Columns.TEMPORARY_ADDRESS4, employee.getTemporaryAddress4());
        parameters.put(Columns.PERMANENT_ADDRESS1, employee.getPermanentAddress1());
        parameters.put(Columns.PERMANENT_ADDRESS2, employee.getPermanentAddress2());
        parameters.put(Columns.PERMANENT_ADDRESS3, employee.getPermanentAddress3());
        parameters.put(Columns.PERMANENT_ADDRESS4, employee.getPermanentAddress4());
        parameters.put(Columns.PERSONAL_CONTACT_NO, employee.getPersonalContactNo());
        parameters.put(Columns.OFFICIAL_CONTACT_NO, employee.getOfficialContactNo());
        parameters.put(Columns.EMERGENCY_CONTACT_NO, employee.getEmergencyContactNo());
        parameters.put(Columns.OFFICIAL_MAIL_ID, employee.getOfficialMailId());
        parameters.put(Columns.PERSONAL_MAIL_ID, employee.getPersonalMailId());
        parameters.put(Columns.GENDER, employee.getGender());
        parameters.put(Columns.BLOODGROUP, employee.getBloodgroup());
        parameters.put(Columns.PAN_NUMBER, employee.getPanNumber());
        parameters.put(Columns.AADHAR_NO, employee.getAadharNo());

        Number newId = insertEmployee.executeAndReturnKey(parameters);
        employee = findById(newId.intValue());
        return employee;
    }

    public void delete(Integer id) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET deleted=? WHERE " + Columns.ID + "=?";
        jdbcTemplate.update(sqlQuery, new Object[]{true, id});
    }

    public Employee update(Employee employee) {
        String path = employee.getImage().get(0).toString().replace("\\", "\\\\");
        System.out.println("Image Path :" + path);
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET "
                + Columns.EMP_NO + " = ?,"
                + Columns.DOOR_CODE + " = ?,"
                + Columns.EMP_NAME + " = ?, "
                + Columns.EMP_DOJ + " = ?,"
                + Columns.EMP_DOB + " = ?,"
                + Columns.DEPARTMENT + " = ?,"
                + Columns.GRADE + " = ?,"
                + Columns.DESIGNATION + " = ?,"
                + Columns.EDU_QUAL + " = ?,"
                + Columns.TOTAL_EXP + " = ?,"
                + Columns.LOCATION + " = ?,"
                + Columns.REPORTING_TO + " = ?,"
                + Columns.LAST_PROMOTION + " = ?,"
                + Columns.DEPARTMENT_HEAD + " = ?,"
                + Columns.COMPANY_ID + " = ?,"
                + Columns.TEMPORARY_ADDRESS1 + " = ?,"
                + Columns.TEMPORARY_ADDRESS2 + " = ?,"
                + Columns.TEMPORARY_ADDRESS3 + " = ?,"
                + Columns.TEMPORARY_ADDRESS4 + " = ?,"
                + Columns.PERMANENT_ADDRESS1 + " = ?,"
                + Columns.PERMANENT_ADDRESS2 + " = ?,"
                + Columns.PERMANENT_ADDRESS3 + " = ?,"
                + Columns.PERMANENT_ADDRESS4 + " = ?,"
                + Columns.PERSONAL_CONTACT_NO + " = ?,"
                + Columns.OFFICIAL_CONTACT_NO + " = ?,"
                + Columns.EMERGENCY_CONTACT_NO + " = ?,"
                + Columns.OFFICIAL_MAIL_ID + " = ?,"
                + Columns.PERSONAL_MAIL_ID + " = ?,"
                + Columns.GENDER + " = ?,"
                + Columns.BLOODGROUP + " = ?,"
                + Columns.PAN_NUMBER + " = ?,"
                + Columns.AADHAR_NO + " = ?,"
                + Columns.IMAGE + " = '" + path + "' WHERE " + Columns.ID + " = ?";
        Number updatedCount = jdbcTemplate.update(sqlQuery,
                new Object[]{
                    employee.getEmpNo(),
                    employee.getDoorCode(),
                    employee.getEmpName(),
                    employee.getEmpDoj(),
                    employee.getEmpDob(),
                    employee.getDepartment().name(),
                    employee.getGrade(),
                    employee.getDesignation().name(),
                    employee.getEduQual(),
                    employee.getTotalExp(),
                    employee.getLocation(),
                    employee.getReportingTo(),
                    employee.getLastPromotion(),
                    employee.getDepartmentHead(),
                    employee.getCompanyId(),
                    employee.getTemporaryAddress1(),
                    employee.getTemporaryAddress2(),
                    employee.getTemporaryAddress3(),
                    employee.getTemporaryAddress4(),
                    employee.getPermanentAddress1(),
                    employee.getPermanentAddress2(),
                    employee.getPermanentAddress3(),
                    employee.getPermanentAddress4(),
                    employee.getPersonalContactNo(),
                    employee.getOfficialContactNo(),
                    employee.getEmergencyContactNo(),
                    employee.getOfficialMailId(),
                    employee.getPersonalMailId(),
                    employee.getGender(),
                    employee.getBloodgroup(),
                    employee.getPanNumber(),
                    employee.getAadharNo(),
                    employee.getId()
                });
        employee = findById(employee.getId());
        return employee;
    }
}
