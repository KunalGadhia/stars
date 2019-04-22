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
    private String temporaryAddress1;
    private String temporaryAddress2;
    private String temporaryAddress3;
    private String temporaryAddress4;
    private String permanentAddress1;
    private String permanentAddress2;
    private String permanentAddress3;
    private String permanentAddress4;
    private String personalContactNo;
    private String officialContactNo;
    private String emergencyContactNo;
    private String officialMailId;
    private String personalMailId;
    private String gender;
    private String bloodgroup;
    private String panNumber;
    private String aadharNo;
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

    public String getTemporaryAddress1() {
        return temporaryAddress1;
    }

    public void setTemporaryAddress1(String temporaryAddress1) {
        this.temporaryAddress1 = temporaryAddress1;
    }

    public String getTemporaryAddress2() {
        return temporaryAddress2;
    }

    public void setTemporaryAddress2(String temporaryAddress2) {
        this.temporaryAddress2 = temporaryAddress2;
    }

    public String getTemporaryAddress3() {
        return temporaryAddress3;
    }

    public void setTemporaryAddress3(String temporaryAddress3) {
        this.temporaryAddress3 = temporaryAddress3;
    }

    public String getTemporaryAddress4() {
        return temporaryAddress4;
    }

    public void setTemporaryAddress4(String temporaryAddress4) {
        this.temporaryAddress4 = temporaryAddress4;
    }

    public String getPermanentAddress1() {
        return permanentAddress1;
    }

    public void setPermanentAddress1(String permanentAddress1) {
        this.permanentAddress1 = permanentAddress1;
    }

    public String getPermanentAddress2() {
        return permanentAddress2;
    }

    public void setPermanentAddress2(String permanentAddress2) {
        this.permanentAddress2 = permanentAddress2;
    }

    public String getPermanentAddress3() {
        return permanentAddress3;
    }

    public void setPermanentAddress3(String permanentAddress3) {
        this.permanentAddress3 = permanentAddress3;
    }

    public String getPermanentAddress4() {
        return permanentAddress4;
    }

    public void setPermanentAddress4(String permanentAddress4) {
        this.permanentAddress4 = permanentAddress4;
    }

    public String getPersonalContactNo() {
        return personalContactNo;
    }

    public void setPersonalContactNo(String personalContactNo) {
        this.personalContactNo = personalContactNo;
    }

    public String getOfficialContactNo() {
        return officialContactNo;
    }

    public void setOfficialContactNo(String officialContactNo) {
        this.officialContactNo = officialContactNo;
    }

    public String getEmergencyContactNo() {
        return emergencyContactNo;
    }

    public void setEmergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

    public String getOfficialMailId() {
        return officialMailId;
    }

    public void setOfficialMailId(String officialMailId) {
        this.officialMailId = officialMailId;
    }

    public String getPersonalMailId() {
        return personalMailId;
    }

    public void setPersonalMailId(String personalMailId) {
        this.personalMailId = personalMailId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.empNo);
        hash = 83 * hash + Objects.hashCode(this.doorCode);
        hash = 83 * hash + Objects.hashCode(this.empName);
        hash = 83 * hash + Objects.hashCode(this.empDoj);
        hash = 83 * hash + Objects.hashCode(this.empDob);
        hash = 83 * hash + Objects.hashCode(this.department);
        hash = 83 * hash + Objects.hashCode(this.grade);
        hash = 83 * hash + Objects.hashCode(this.designation);
        hash = 83 * hash + Objects.hashCode(this.eduQual);
        hash = 83 * hash + Objects.hashCode(this.totalExp);
        hash = 83 * hash + Objects.hashCode(this.location);
        hash = 83 * hash + Objects.hashCode(this.reportingTo);
        hash = 83 * hash + Objects.hashCode(this.lastPromotion);
        hash = 83 * hash + Objects.hashCode(this.departmentHead);
        hash = 83 * hash + Objects.hashCode(this.companyId);
        hash = 83 * hash + Objects.hashCode(this.temporaryAddress1);
        hash = 83 * hash + Objects.hashCode(this.temporaryAddress2);
        hash = 83 * hash + Objects.hashCode(this.temporaryAddress3);
        hash = 83 * hash + Objects.hashCode(this.temporaryAddress4);
        hash = 83 * hash + Objects.hashCode(this.permanentAddress1);
        hash = 83 * hash + Objects.hashCode(this.permanentAddress2);
        hash = 83 * hash + Objects.hashCode(this.permanentAddress3);
        hash = 83 * hash + Objects.hashCode(this.permanentAddress4);
        hash = 83 * hash + Objects.hashCode(this.personalContactNo);
        hash = 83 * hash + Objects.hashCode(this.officialContactNo);
        hash = 83 * hash + Objects.hashCode(this.emergencyContactNo);
        hash = 83 * hash + Objects.hashCode(this.officialMailId);
        hash = 83 * hash + Objects.hashCode(this.personalMailId);
        hash = 83 * hash + Objects.hashCode(this.gender);
        hash = 83 * hash + Objects.hashCode(this.bloodgroup);
        hash = 83 * hash + Objects.hashCode(this.panNumber);
        hash = 83 * hash + Objects.hashCode(this.aadharNo);
        hash = 83 * hash + Objects.hashCode(this.image);
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
        if (!Objects.equals(this.temporaryAddress1, other.temporaryAddress1)) {
            return false;
        }
        if (!Objects.equals(this.temporaryAddress2, other.temporaryAddress2)) {
            return false;
        }
        if (!Objects.equals(this.temporaryAddress3, other.temporaryAddress3)) {
            return false;
        }
        if (!Objects.equals(this.temporaryAddress4, other.temporaryAddress4)) {
            return false;
        }
        if (!Objects.equals(this.permanentAddress1, other.permanentAddress1)) {
            return false;
        }
        if (!Objects.equals(this.permanentAddress2, other.permanentAddress2)) {
            return false;
        }
        if (!Objects.equals(this.permanentAddress3, other.permanentAddress3)) {
            return false;
        }
        if (!Objects.equals(this.permanentAddress4, other.permanentAddress4)) {
            return false;
        }
        if (!Objects.equals(this.personalContactNo, other.personalContactNo)) {
            return false;
        }
        if (!Objects.equals(this.officialContactNo, other.officialContactNo)) {
            return false;
        }
        if (!Objects.equals(this.emergencyContactNo, other.emergencyContactNo)) {
            return false;
        }
        if (!Objects.equals(this.officialMailId, other.officialMailId)) {
            return false;
        }
        if (!Objects.equals(this.personalMailId, other.personalMailId)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        if (!Objects.equals(this.bloodgroup, other.bloodgroup)) {
            return false;
        }
        if (!Objects.equals(this.panNumber, other.panNumber)) {
            return false;
        }
        if (!Objects.equals(this.aadharNo, other.aadharNo)) {
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
        return "Employee{" + "id=" + id + ", empNo=" + empNo + ", doorCode=" + doorCode + ", empName=" + empName + ", empDoj=" + empDoj + ", empDob=" + empDob + ", department=" + department + ", grade=" + grade + ", designation=" + designation + ", eduQual=" + eduQual + ", totalExp=" + totalExp + ", location=" + location + ", reportingTo=" + reportingTo + ", lastPromotion=" + lastPromotion + ", departmentHead=" + departmentHead + ", companyId=" + companyId + ", temporaryAddress1=" + temporaryAddress1 + ", temporaryAddress2=" + temporaryAddress2 + ", temporaryAddress3=" + temporaryAddress3 + ", temporaryAddress4=" + temporaryAddress4 + ", permanentAddress1=" + permanentAddress1 + ", permanentAddress2=" + permanentAddress2 + ", permanentAddress3=" + permanentAddress3 + ", permanentAddress4=" + permanentAddress4 + ", personalContactNo=" + personalContactNo + ", officialContactNo=" + officialContactNo + ", emergencyContactNo=" + emergencyContactNo + ", officialMailId=" + officialMailId + ", personalMailId=" + personalMailId + ", gender=" + gender + ", bloodgroup=" + bloodgroup + ", panNumber=" + panNumber + ", aadharNo=" + aadharNo + ", image=" + image + '}';
    }                    
}
