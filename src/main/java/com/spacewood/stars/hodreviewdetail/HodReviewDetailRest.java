/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.hodreviewdetail;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author webdesign
 */
@RestController
@RequestMapping("/review_hod")
public class HodReviewDetailRest {

    @Autowired
    private HodReviewDetailDAL hodReviewDetailDAL;    

    @RequestMapping(method = RequestMethod.GET)
    public List<HodReviewDetail> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) throws SQLException {
        return hodReviewDetailDAL.findAll(offset);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HodReviewDetail findById(@PathVariable("id") Integer id) throws SQLException {

        return hodReviewDetailDAL.findById(id);

    }
    
    @RequestMapping(value = "/find/employeeId", method = RequestMethod.GET)
    public HodReviewDetail findByEmployeeId(@RequestParam("employeeId") Integer employeeId) throws SQLException {
        return hodReviewDetailDAL.findByEmployeeId(employeeId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public HodReviewDetail insert(@RequestBody HodReviewDetail hodReviewDetail) throws JsonProcessingException, ParseException {
        return hodReviewDetailDAL.insert(hodReviewDetail);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public HodReviewDetail update(@RequestBody HodReviewDetail hodReviewDetail) throws Exception {
        return hodReviewDetailDAL.update(hodReviewDetail);
    }

//    @RolesAllowed("ROLE_SUPER_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        hodReviewDetailDAL.delete(id);
    }
}
