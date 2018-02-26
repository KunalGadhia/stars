/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.employee;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author webdesign
 */
public class Employee {

    private Integer id;
    private String empNo;
    private String doorCode;
    private String empName;
    private Date empDoj;
    private Date empDob;
    private Department department;
    private String grade;
    private Designation designation;
    private String eduQual;
    private String totalExp;
    private String location;
    private Integer reportingTo;
    private String lastPromotion;
    private String departmentHead;
    private Integer companyId;
    private List<String> image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getDoorCode() {
        return doorCode;
    }

    public void setDoorCode(String doorCode) {
        this.doorCode = doorCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Date getEmpDoj() {
        return empDoj;
    }

    public void setEmpDoj(Date empDoj) {
        this.empDoj = empDoj;
    }

    public Date getEmpDob() {
        return empDob;
    }

    public void setEmpDob(Date empDob) {
        this.empDob = empDob;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public String getEduQual() {
        return eduQual;
    }

    public void setEduQual(String eduQual) {
        this.eduQual = eduQual;
    }

    public String getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(String totalExp) {
        this.totalExp = totalExp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getReportingTo() {
        return reportingTo;
    }

    public void setReportingTo(Integer reportingTo) {
        this.reportingTo = reportingTo;
    }

    public String getLastPromotion() {
        return lastPromotion;
    }

    public void setLastPromotion(String lastPromotion) {
        this.lastPromotion = lastPromotion;
    }

    public String getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(String departmentHead) {
        this.departmentHead = departmentHead;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.empNo);
        hash = 97 * hash + Objects.hashCode(this.doorCode);
        hash = 97 * hash + Objects.hashCode(this.empName);
        hash = 97 * hash + Objects.hashCode(this.empDoj);
        hash = 97 * hash + Objects.hashCode(this.empDob);
        hash = 97 * hash + Objects.hashCode(this.department);
        hash = 97 * hash + Objects.hashCode(this.grade);
        hash = 97 * hash + Objects.hashCode(this.designation);
        hash = 97 * hash + Objects.hashCode(this.eduQual);
        hash = 97 * hash + Objects.hashCode(this.totalExp);
        hash = 97 * hash + Objects.hashCode(this.location);
        hash = 97 * hash + Objects.hashCode(this.reportingTo);
        hash = 97 * hash + Objects.hashCode(this.lastPromotion);
        hash = 97 * hash + Objects.hashCode(this.departmentHead);
        hash = 97 * hash + Objects.hashCode(this.companyId);
        hash = 97 * hash + Objects.hashCode(this.image);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.empNo, other.empNo)) {
            return false;
        }
        if (!Objects.equals(this.doorCode, other.doorCode)) {
            return false;
        }
        if (!Objects.equals(this.empName, other.empName)) {
            return false;
        }
        if (!Objects.equals(this.grade, other.grade)) {
            return false;
        }
        if (!Objects.equals(this.eduQual, other.eduQual)) {
            return false;
        }
        if (!Objects.equals(this.totalExp, other.totalExp)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.lastPromotion, other.lastPromotion)) {
            return false;
        }
        if (!Objects.equals(this.departmentHead, other.departmentHead)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.empDoj, other.empDoj)) {
            return false;
        }
        if (!Objects.equals(this.empDob, other.empDob)) {
            return false;
        }
        if (this.department != other.department) {
            return false;
        }
        if (this.designation != other.designation) {
            return false;
        }
        if (!Objects.equals(this.reportingTo, other.reportingTo)) {
            return false;
        }
        if (!Objects.equals(this.companyId, other.companyId)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", empNo=" + empNo + ", doorCode=" + doorCode + ", empName=" + empName + ", empDoj=" + empDoj + ", empDob=" + empDob + ", department=" + department + ", grade=" + grade + ", designation=" + designation + ", eduQual=" + eduQual + ", totalExp=" + totalExp + ", location=" + location + ", reportingTo=" + reportingTo + ", lastPromotion=" + lastPromotion + ", departmentHead=" + departmentHead + ", companyId=" + companyId + ", image=" + image + '}';
    }

                
}
