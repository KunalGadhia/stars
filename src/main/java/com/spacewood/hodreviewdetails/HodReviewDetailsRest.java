/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.hodreviewdetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/hrd")
public class HodReviewDetailsRest {
    @Autowired
    private HodReviewDetailsDAL hodReviewDetailsDAL;

    @RequestMapping(method = RequestMethod.GET)
    public List<HodReviewDetails> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) throws SQLException {
        return hodReviewDetailsDAL.findAll(offset);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HodReviewDetails findById(@PathVariable("id") Integer id) throws SQLException {
        return hodReviewDetailsDAL.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public HodReviewDetails insert(@RequestBody HodReviewDetails hodReviewDetails) throws JsonProcessingException {
        return hodReviewDetailsDAL.insert(hodReviewDetails);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public HodReviewDetails update(@RequestBody HodReviewDetails hodReviewDetails) throws JsonProcessingException {
        return hodReviewDetailsDAL.update(hodReviewDetails);
    }

//    @RolesAllowed("ROLE_SUPER_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        hodReviewDetailsDAL.delete(id);
    }
    
    @RequestMapping(value = "/find/employeeId", method = RequestMethod.GET)
    public HodReviewDetails findByEmployeeId(@RequestParam("employeeId") Integer employeeId) {
        return hodReviewDetailsDAL.findByEmployeeId(employeeId);
    }

    @RequestMapping(value = "/find_all_list", method = RequestMethod.GET)
    public List<HodReviewDetails> findAllList() {
        return hodReviewDetailsDAL.findAllList();
    }
}
