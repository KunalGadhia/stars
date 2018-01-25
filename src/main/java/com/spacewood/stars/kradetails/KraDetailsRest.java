/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.kradetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.SQLException;
import java.text.ParseException;
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
@RequestMapping("/kra_details")
public class KraDetailsRest {

    @Autowired
    private KraDetailsDAL kraDetailsDAL;

    @RequestMapping(method = RequestMethod.GET)
    public List<KraDetails> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) throws SQLException {
        return kraDetailsDAL.findAll(offset);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public KraDetails findById(@PathVariable("id") Integer id) throws SQLException {

        return kraDetailsDAL.findById(id);

    }

    @RequestMapping(method = RequestMethod.POST)
    public KraDetails insert(@RequestBody KraDetails kraDetails) throws JsonProcessingException, ParseException {
        return kraDetailsDAL.insert(kraDetails);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public KraDetails update(@RequestBody KraDetails kraDetails) throws Exception {
        return kraDetailsDAL.update(kraDetails);
    }

//    @RolesAllowed("ROLE_SUPER_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        kraDetailsDAL.delete(id);
    }

//    @RequestMapping(value = "/find/username", method = RequestMethod.GET)
//
//    public User findByUsername(@RequestParam("username") String username) throws Exception {
//        return kraDetailsDAL.findByUsername(username);
//    }
    @RequestMapping(value = "/find/employeeId", method = RequestMethod.GET)
    public List<KraDetails> findByEmployeeId(@RequestParam("employeeId") Integer employeeId) {
        return kraDetailsDAL.findByEmployeeId(employeeId);
    }
}
