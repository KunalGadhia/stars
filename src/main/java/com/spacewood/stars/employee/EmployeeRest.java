/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.employee;

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
@RequestMapping("/employee")
public class EmployeeRest {
    @Autowired
    private EmployeeDAL employeeDAL;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) throws SQLException {
        return employeeDAL.findAll(offset);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Employee findById(@PathVariable("id") Integer id) throws SQLException {
        return employeeDAL.findById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Employee insert(@RequestBody Employee user) {
        return employeeDAL.insert(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Employee update(@RequestBody Employee user) {
        return employeeDAL.update(user);
    }

//    @RolesAllowed("ROLE_SUPER_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        employeeDAL.delete(id);
    }

    @RequestMapping(value = "/find/name", method = RequestMethod.GET)

    public Employee findByName(@RequestParam("name") String name) throws Exception {
        return employeeDAL.findByName(name);
    }
    
    @RequestMapping(value = "/find/user_like", method = RequestMethod.GET)
    public List<Employee> findByNameLike(@RequestParam("name") String name) {
        return employeeDAL.findByNameLike(name);
    }
    
    @RequestMapping(value = "/find_all_list", method = RequestMethod.GET)
    public List<Employee> findAllList() {
        return employeeDAL.findAllList();
    }
}
