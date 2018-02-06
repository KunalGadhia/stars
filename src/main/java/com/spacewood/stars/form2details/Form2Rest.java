/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.form2details;

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
@RequestMapping("/form2_details")
public class Form2Rest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private Form2DetailsDAL form2DetailsDAL;

    @RequestMapping(method = RequestMethod.GET)
    public List<Form2Details> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) throws SQLException {
        return form2DetailsDAL.findAll(offset);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Form2Details findById(@PathVariable("id") Integer id) throws SQLException {
        return form2DetailsDAL.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Form2Details insert(@RequestBody Form2Details form2Details) {
        return form2DetailsDAL.insert(form2Details);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Form2Details update(@RequestBody Form2Details form2Details) {
        return form2DetailsDAL.update(form2Details);
    }

//    @RolesAllowed("ROLE_SUPER_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        form2DetailsDAL.delete(id);
    }
    
    @RequestMapping(value = "/find/employeeId", method = RequestMethod.GET)
    public List<Form2Details> findByEmployeeId(@RequestParam("employeeId") Integer employeeId) {
        return form2DetailsDAL.findByEmployeeId(employeeId);
    }

    @RequestMapping(value = "/find_all_list", method = RequestMethod.GET)
    public List<Form2Details> findAllList() {
        return form2DetailsDAL.findAllList();
    }
}
