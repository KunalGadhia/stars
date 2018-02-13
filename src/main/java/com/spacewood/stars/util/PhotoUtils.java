/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.util;

import com.spacewood.stars.employee.Employee;
import com.spacewood.stars.util.AttachmentUtils.AttachmentType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

/**
 *
 * @author webdesign
 */
@Service
public class PhotoUtils {
   private String PHOTO_FILE_NAME = "";
    @Autowired
    private AttachmentUtils attachmentUtils;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    ////////////////////////////////////////////////////////
    public File getEmployeePhoto(Employee employee) throws FileNotFoundException, IOException {
        if (employee.getImage() != null) {
            PHOTO_FILE_NAME = employee.getImage().get(0).toString();
        }
        File photoFile = getEmployeePhotoFile(employee);
        return photoFile;
    }

    public File setEmployeePhoto(
            InputStream inputStream,
            Employee employee)
            throws IOException {

        File photoFile = getEmployeePhoto(employee);
        FileCopyUtils.copy(inputStream, new FileOutputStream(photoFile));
        return photoFile;
    }

    public File getEmployeePhotoFile(Employee employee) throws IOException {
        File employeeDir = attachmentUtils.getDirectoryByAttachmentTypeAndEntityId(AttachmentType.EMPLOYEE, employee.getId(), true);
        return new File(employeeDir, PHOTO_FILE_NAME);
    } 
}
