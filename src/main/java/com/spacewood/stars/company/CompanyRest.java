/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.company;

import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/company")
public class CompanyRest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CompanyDAL companyDAL;

    @RequestMapping(method = RequestMethod.GET)
    public List<Company> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) throws SQLException {
        return companyDAL.findAll(offset);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Company findById(@PathVariable("id") Integer id) throws SQLException {
        return companyDAL.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Company insert(@RequestBody Company company) {
        return companyDAL.insert(company);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Company update(@RequestBody Company company) {
        return companyDAL.update(company);
    }

//    @RolesAllowed("ROLE_SUPER_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        companyDAL.delete(id);
    }       

    @RequestMapping(value = "/find_all_list", method = RequestMethod.GET)
    public List<Company> findAllList() {
        return companyDAL.findAllList();
    }
}
