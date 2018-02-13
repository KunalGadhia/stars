/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author webdesign
 */
@RestController
@RequestMapping("/employee")
public class EmployeeRest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private EmployeeDAL employeeDAL;
    
     @Autowired
    private EmployeeService employeeService;
    
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
    
    @RequestMapping(value = "/find/emp_no_like", method = RequestMethod.GET)
    public List<Employee> findByEmpNumLike(@RequestParam("empNo") String empNo) {
        return employeeDAL.findByEmpNumLike(empNo);
    }
    
    @RequestMapping(value = "/find/department_head", method = RequestMethod.GET)
    public List<Employee> findByDepartmentHead(@RequestParam("departmentHeadId") Integer departmentHeadId) {
        return employeeDAL.findByDepartmentHead(departmentHeadId);
    }
    
    @RequestMapping(value = "/find_all_list", method = RequestMethod.GET)
    public List<Employee> findAllList() {
        return employeeDAL.findAllList();
    }
    
    @RequestMapping(value = "/{id}/attachment", method = RequestMethod.POST)
    public Employee uploadAttachment(
            @PathVariable Integer id,
            @RequestParam MultipartFile attachment
    ) throws IOException {
        System.out.println("MULTIPART ATTACHMENT LOGGER+++++++++++++++++" + attachment.getName());
        return employeeService.insertAttachments(id, attachment);
    }

    @RequestMapping(value = "/{id}/attachment", method = RequestMethod.GET)
    public void getAttachment(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        File photoFile = employeeService.getPhoto(id);
        logger.info("Photo FIle :" + photoFile);
        response.setContentType(Files.probeContentType(Paths.get(photoFile.getAbsolutePath())));
        response.setContentLengthLong(photoFile.length());
        logger.debug("filename: {}, size: {}", photoFile.getAbsoluteFile(), photoFile.length());
        FileCopyUtils.copy(new FileInputStream(photoFile), response.getOutputStream());
    }
}
