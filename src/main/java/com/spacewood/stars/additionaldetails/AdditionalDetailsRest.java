/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.additionaldetails;

import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/additional_details")
public class AdditionalDetailsRest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AdditionalDetailsDAL additionalDetailsDAL;

    @RequestMapping(method = RequestMethod.GET)
    public List<AdditionalDetails> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) throws SQLException {
        return additionalDetailsDAL.findAll(offset);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AdditionalDetails findById(@PathVariable("id") Integer id) throws SQLException {
        return additionalDetailsDAL.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public AdditionalDetails insert(@RequestBody AdditionalDetails additionalDetails) {
        return additionalDetailsDAL.insert(additionalDetails);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public AdditionalDetails update(@RequestBody AdditionalDetails additionalDetails) {
        return additionalDetailsDAL.update(additionalDetails);
    }

//    @RolesAllowed("ROLE_SUPER_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        additionalDetailsDAL.delete(id);
    }
    
    @RequestMapping(value = "/find/employeeId", method = RequestMethod.GET)
    public AdditionalDetails findByEmployeeId(@RequestParam("employeeId") Integer employeeId) {
        return additionalDetailsDAL.findByEmployeeId(employeeId);
    }

    @RequestMapping(value = "/find_all_list", method = RequestMethod.GET)
    public List<AdditionalDetails> findAllList() {
        return additionalDetailsDAL.findAllList();
    }
}
