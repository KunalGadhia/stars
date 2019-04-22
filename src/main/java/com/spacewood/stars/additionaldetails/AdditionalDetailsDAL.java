/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.additionaldetails;

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
public class AdditionalDetailsDAL {

    public static final class Columns {

        public static final String ID = "id";
        public static final String EMPLOYEE_ID = "employee_id";
        public static final String SPECIAL_CONTRIBUTION = "special_contribution";
        public static final String AREA_OF_IMPROVEMENT = "area_of_improvement";
        public static final String COMM_INTERPERSONAL_SKILL = "comm_interpersonal_skill";
        public static final String SUPERVISORY_DEVELOPMENT = "supervisory_development";
        public static final String TEAM_BUILDING = "team_building";
        public static final String SELLING_NEGOTIATION_SKILL = "selling_negotiation_skill";
        public static final String CRM = "crm";
        public static final String PRESENTATION_SKILL = "presentation_skill";
        public static final String TIME_MANAGEMENT = "time_management";
        public static final String FUNCTIONAL = "functional";
        public static final String HOD_COMMENT = "hod_comment";
        public static final String HR_COMMENT = "hr_comment";
        public static final String NO_COMMENT = "no_comment";
        public static final String CORRECTION_FACTOR = "correction_factor";
        public static final String YEAR = "year";
    }

    public static final String TABLE_NAME = "additional_details";

    private final SimpleJdbcInsert insertAdditionalDetails;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdditionalDetailsDAL(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        insertAdditionalDetails = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingColumns(
                        Columns.EMPLOYEE_ID,
                        Columns.SPECIAL_CONTRIBUTION,
                        Columns.AREA_OF_IMPROVEMENT,
                        Columns.COMM_INTERPERSONAL_SKILL,
                        Columns.SUPERVISORY_DEVELOPMENT,
                        Columns.TEAM_BUILDING,
                        Columns.SELLING_NEGOTIATION_SKILL,
                        Columns.CRM,
                        Columns.PRESENTATION_SKILL,
                        Columns.TIME_MANAGEMENT,
                        Columns.FUNCTIONAL,
                        Columns.HOD_COMMENT,
                        Columns.HR_COMMENT,
                        Columns.NO_COMMENT,
                        Columns.CORRECTION_FACTOR,
                        Columns.YEAR
                )
                .usingGeneratedKeyColumns(Columns.ID);
    }

    public List<AdditionalDetails> findAll(Integer offset) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE ORDER BY " + Columns.ID + " DESC LIMIT 10 OFFSET ?";
        return jdbcTemplate.query(sqlQuery, new Object[]{offset}, new BeanPropertyRowMapper<>(AdditionalDetails.class));
    }

    public List<AdditionalDetails> findAllList() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(AdditionalDetails.class));
    }

    public List<AdditionalDetails> findByCommSkill() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.COMM_INTERPERSONAL_SKILL + " = TRUE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(AdditionalDetails.class));
    }

    public List<AdditionalDetails> findBySupervisoryDevelopment() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.SUPERVISORY_DEVELOPMENT + " = TRUE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(AdditionalDetails.class));
    }

    public List<AdditionalDetails> findByTeamBuilding() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.TEAM_BUILDING + " = TRUE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(AdditionalDetails.class));
    }

    public List<AdditionalDetails> findByNegotiationSkill() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.SELLING_NEGOTIATION_SKILL + " = TRUE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(AdditionalDetails.class));
    }

    public List<AdditionalDetails> findByCrm() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.CRM + " = TRUE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(AdditionalDetails.class));
    }

    public List<AdditionalDetails> findByPresentationSkill() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.PRESENTATION_SKILL + " = TRUE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(AdditionalDetails.class));
    }

    public List<AdditionalDetails> findByTimeManagement() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.TIME_MANAGEMENT + " = TRUE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(AdditionalDetails.class));
    }

    public List<AdditionalDetails> findByFunctional() {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.FUNCTIONAL + " = TRUE";
        return jdbcTemplate.query(sqlQuery, new Object[]{}, new BeanPropertyRowMapper<>(AdditionalDetails.class));
    }

    public AdditionalDetails findById(Integer id) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, new BeanPropertyRowMapper<>(AdditionalDetails.class));
    }

    public AdditionalDetails findByEmployeeId(Integer employeeId) {
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE deleted = FALSE AND " + Columns.EMPLOYEE_ID + " = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new Object[]{employeeId}, new BeanPropertyRowMapper<>(AdditionalDetails.class));
    }

    public AdditionalDetails insert(AdditionalDetails additionalDetails) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Columns.EMPLOYEE_ID, additionalDetails.getEmployeeId());
        parameters.put(Columns.SPECIAL_CONTRIBUTION, additionalDetails.getSpecialContribution());
        parameters.put(Columns.AREA_OF_IMPROVEMENT, additionalDetails.getAreaOfImprovement());
        parameters.put(Columns.COMM_INTERPERSONAL_SKILL, additionalDetails.getCommInterpersonalSkill());
        parameters.put(Columns.SUPERVISORY_DEVELOPMENT, additionalDetails.getSupervisoryDevelopment());
        parameters.put(Columns.TEAM_BUILDING, additionalDetails.getTeamBuilding());
        parameters.put(Columns.SELLING_NEGOTIATION_SKILL, additionalDetails.getSellingNegotiationSkill());
        parameters.put(Columns.CRM, additionalDetails.getCrm());
        parameters.put(Columns.PRESENTATION_SKILL, additionalDetails.getPresentationSkill());
        parameters.put(Columns.TIME_MANAGEMENT, additionalDetails.getTimeManagement());
        parameters.put(Columns.FUNCTIONAL, additionalDetails.getFunctional());
        parameters.put(Columns.HOD_COMMENT, additionalDetails.getHodComment());
        parameters.put(Columns.HR_COMMENT, additionalDetails.getHrComment());
        parameters.put(Columns.NO_COMMENT, additionalDetails.getNoComment());
        parameters.put(Columns.CORRECTION_FACTOR, additionalDetails.getCorrectionFactor());
        parameters.put(Columns.YEAR, additionalDetails.getYear());
        Number newId = insertAdditionalDetails.executeAndReturnKey(parameters);
        additionalDetails = findById(newId.intValue());
        return additionalDetails;
    }

    public void delete(Integer id) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET deleted=? WHERE " + Columns.ID + "=?";
        jdbcTemplate.update(sqlQuery, new Object[]{true, id});
    }

    public AdditionalDetails update(AdditionalDetails additionaldetails) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET "
                + Columns.EMPLOYEE_ID + " = ?, "
                + Columns.SPECIAL_CONTRIBUTION + " = ?, "
                + Columns.AREA_OF_IMPROVEMENT + " = ?,"
                + Columns.COMM_INTERPERSONAL_SKILL + " = ?,"
                + Columns.SUPERVISORY_DEVELOPMENT + " = ?,"
                + Columns.TEAM_BUILDING + " = ?,"
                + Columns.SELLING_NEGOTIATION_SKILL + " = ?,"
                + Columns.CRM + " = ?,"
                + Columns.PRESENTATION_SKILL + " = ?,"
                + Columns.TIME_MANAGEMENT + " = ?,"
                + Columns.FUNCTIONAL + " = ?,"
                + Columns.HOD_COMMENT + " = ?,"
                + Columns.HR_COMMENT + " = ?,"
                + Columns.NO_COMMENT + " = ?,"
                + Columns.CORRECTION_FACTOR + " = ?,"
                + Columns.YEAR + " = ?  WHERE " + Columns.ID + " = ?";
        Number updatedCount = jdbcTemplate.update(sqlQuery,
                new Object[]{
                    additionaldetails.getEmployeeId(),
                    additionaldetails.getSpecialContribution(),
                    additionaldetails.getAreaOfImprovement(),
                    additionaldetails.getCommInterpersonalSkill(),
                    additionaldetails.getSupervisoryDevelopment(),
                    additionaldetails.getTeamBuilding(),
                    additionaldetails.getSellingNegotiationSkill(),
                    additionaldetails.getCrm(),
                    additionaldetails.getPresentationSkill(),
                    additionaldetails.getTimeManagement(),
                    additionaldetails.getFunctional(),
                    additionaldetails.getHodComment(),
                    additionaldetails.getHrComment(),
                    additionaldetails.getNoComment(),
                    additionaldetails.getCorrectionFactor(),
                    additionaldetails.getYear(),
                    additionaldetails.getId()
                });
        additionaldetails = findById(additionaldetails.getId());
        return additionaldetails;
    }
}
