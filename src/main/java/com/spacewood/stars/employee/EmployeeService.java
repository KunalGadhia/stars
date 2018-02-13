/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spacewood.stars.util.AttachmentUtils;
import com.spacewood.stars.util.PhotoUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author webdesign
 */
@Service
@Transactional
public class EmployeeService {
    public final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    AttachmentUtils attachmentUtils;    

    @Autowired
    PhotoUtils photoUtils;
    
    @Autowired
    EmployeeDAL employeeDAL;

    public Employee insertAttachments(Integer employeeId, MultipartFile attachmentMultipartFile) throws JsonProcessingException, IOException {
        Employee employee = employeeDAL.findById(employeeId);
        Boolean isView = false;
        File outputFile = attachmentUtils.storeAttachmentByAttachmentTypeAndEntityId(
                attachmentMultipartFile.getOriginalFilename(),
                attachmentMultipartFile.getInputStream(),
                AttachmentUtils.AttachmentType.EMPLOYEE,
                employee.getId(),
                isView
        );
        System.out.println("THIS IS OUTPUTFILE==================" + outputFile.toString());
        List<String> attachments = new ArrayList<>();
        attachments.add(outputFile.getName().toString());
        employee.setImage(attachments);
//
        employeeDAL.update(employee);
        return employee;
    }

    public File getPhoto(Integer employeeId) throws FileNotFoundException, IOException {
        Employee employee = employeeDAL.findById(employeeId);
        return photoUtils.getEmployeePhoto(employee);
    }

    public File getImage(Employee employee) throws IOException {
        if (employee.getImage().size() != 0) {
            String PHOTO_FILE_NAME = employee.getImage().get(0).toString();
            File photoFile = photoUtils.getEmployeePhotoFile(employee);
            return photoFile;
        } else {
            File photoFiles = new File(getClass().getResource("images/default.png").getFile());
            return photoFiles;
        }
    }
}
