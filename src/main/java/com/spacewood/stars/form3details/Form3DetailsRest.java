/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.form3details;

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
 * @author webdesign
 */
@RestController
@RequestMapping("/form3_details")
public class Form3DetailsRest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private Form3DetailsDAL form3DetailsDAL;

    @RequestMapping(method = RequestMethod.GET)
    public List<Form3Details> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) throws SQLException {
        return form3DetailsDAL.findAll(offset);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Form3Details findById(@PathVariable("id") Integer id) throws SQLException {
        return form3DetailsDAL.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Form3Details insert(@RequestBody Form3Details form3Details) {
        System.out.println("Getting Anything :"+form3Details);
        return form3DetailsDAL.insert(form3Details);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Form3Details update(@RequestBody Form3Details form3Details) {
        return form3DetailsDAL.update(form3Details);
    }

//    @RolesAllowed("ROLE_SUPER_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        form3DetailsDAL.delete(id);
    }
    
    @RequestMapping(value = "/find/employeeId", method = RequestMethod.GET)
    public Form3Details findByEmployeeId(@RequestParam("employeeId") Integer employeeId) {
        return form3DetailsDAL.findByEmployeeId(employeeId);
    }

    @RequestMapping(value = "/find_all_list", method = RequestMethod.GET)
    public List<Form3Details> findAllList() {
        return form3DetailsDAL.findAllList();
    }
}
