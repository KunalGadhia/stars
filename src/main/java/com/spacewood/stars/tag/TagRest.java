/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.tag;

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
@RequestMapping("/tag")
public class TagRest {
   @Autowired
    private TagDAL tagDAL;        
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Tag> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) throws SQLException {
        return tagDAL.findAll(offset);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Tag findById(@PathVariable("id") Integer id) throws SQLException {
        return tagDAL.findById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public Tag insert(@RequestBody Tag tag) {
        return tagDAL.insert(tag);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Tag update(@RequestBody Tag tag) {
        return tagDAL.update(tag);
    }

//    @RolesAllowed("ROLE_SUPER_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) throws Exception {
        tagDAL.delete(id);
    }

    @RequestMapping(value = "/find/name", method = RequestMethod.GET)

    public Tag findByName(@RequestParam("name") String name) throws Exception {
        return tagDAL.findByName(name);
    }
    
    @RequestMapping(value = "/find/user_like", method = RequestMethod.GET)
    public List<Tag> findByNameLike(@RequestParam("name") String name) {
        return tagDAL.findByNameLike(name);
    }        
    
    @RequestMapping(value = "/find_all_list", method = RequestMethod.GET)
    public List<Tag> findAllList() {
        return tagDAL.findAllList();
    } 
}
