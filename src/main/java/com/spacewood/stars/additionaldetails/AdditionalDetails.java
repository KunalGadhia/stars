/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.additionaldetails;

import java.util.Objects;

/**
 *
 * @author User
 */
public class AdditionalDetails {
    private Integer id;
    private Integer employeeId;
    private String specialContribution;
    private String areaOfImprovement;
    private Boolean commInterpersonalSkill;
    private Boolean supervisoryDevelopment;
    private Boolean teamBuilding;
    private Boolean sellingNegotiationSkill;
    private Boolean crm;
    private Boolean presentationSkill;
    private Boolean timeManagement;
    private Boolean functional;
    private String hodComment;
    private String hrComment;
    private String noComment;
    private Double correctionFactor;
    private Integer year;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getSpecialContribution() {
        return specialContribution;
    }

    public void setSpecialContribution(String specialContribution) {
        this.specialContribution = specialContribution;
    }

    public String getAreaOfImprovement() {
        return areaOfImprovement;
    }

    public void setAreaOfImprovement(String areaOfImprovement) {
        this.areaOfImprovement = areaOfImprovement;
    }

    public Boolean getCommInterpersonalSkill() {
        return commInterpersonalSkill;
    }

    public void setCommInterpersonalSkill(Boolean commInterpersonalSkill) {
        this.commInterpersonalSkill = commInterpersonalSkill;
    }

    public Boolean getSupervisoryDevelopment() {
        return supervisoryDevelopment;
    }

    public void setSupervisoryDevelopment(Boolean supervisoryDevelopment) {
        this.supervisoryDevelopment = supervisoryDevelopment;
    }

    public Boolean getTeamBuilding() {
        return teamBuilding;
    }

    public void setTeamBuilding(Boolean teamBuilding) {
        this.teamBuilding = teamBuilding;
    }

    public Boolean getSellingNegotiationSkill() {
        return sellingNegotiationSkill;
    }

    public void setSellingNegotiationSkill(Boolean sellingNegotiationSkill) {
        this.sellingNegotiationSkill = sellingNegotiationSkill;
    }

    public Boolean getCrm() {
        return crm;
    }

    public void setCrm(Boolean crm) {
        this.crm = crm;
    }

    public Boolean getPresentationSkill() {
        return presentationSkill;
    }

    public void setPresentationSkill(Boolean presentationSkill) {
        this.presentationSkill = presentationSkill;
    }

    public Boolean getTimeManagement() {
        return timeManagement;
    }

    public void setTimeManagement(Boolean timeManagement) {
        this.timeManagement = timeManagement;
    }

    public Boolean getFunctional() {
        return functional;
    }

    public void setFunctional(Boolean functional) {
        this.functional = functional;
    }

    public String getHodComment() {
        return hodComment;
    }

    public void setHodComment(String hodComment) {
        this.hodComment = hodComment;
    }

    public String getHrComment() {
        return hrComment;
    }

    public void setHrComment(String hrComment) {
        this.hrComment = hrComment;
    }

    public String getNoComment() {
        return noComment;
    }

    public void setNoComment(String noComment) {
        this.noComment = noComment;
    }

    public Double getCorrectionFactor() {
        return correctionFactor;
    }

    public void setCorrectionFactor(Double correctionFactor) {
        this.correctionFactor = correctionFactor;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.employeeId);
        hash = 79 * hash + Objects.hashCode(this.specialContribution);
        hash = 79 * hash + Objects.hashCode(this.areaOfImprovement);
        hash = 79 * hash + Objects.hashCode(this.commInterpersonalSkill);
        hash = 79 * hash + Objects.hashCode(this.supervisoryDevelopment);
        hash = 79 * hash + Objects.hashCode(this.teamBuilding);
        hash = 79 * hash + Objects.hashCode(this.sellingNegotiationSkill);
        hash = 79 * hash + Objects.hashCode(this.crm);
        hash = 79 * hash + Objects.hashCode(this.presentationSkill);
        hash = 79 * hash + Objects.hashCode(this.timeManagement);
        hash = 79 * hash + Objects.hashCode(this.functional);
        hash = 79 * hash + Objects.hashCode(this.hodComment);
        hash = 79 * hash + Objects.hashCode(this.hrComment);
        hash = 79 * hash + Objects.hashCode(this.noComment);
        hash = 79 * hash + Objects.hashCode(this.correctionFactor);
        hash = 79 * hash + Objects.hashCode(this.year);
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
        final AdditionalDetails other = (AdditionalDetails) obj;
        if (!Objects.equals(this.specialContribution, other.specialContribution)) {
            return false;
        }
        if (!Objects.equals(this.areaOfImprovement, other.areaOfImprovement)) {
            return false;
        }
        if (!Objects.equals(this.hodComment, other.hodComment)) {
            return false;
        }
        if (!Objects.equals(this.hrComment, other.hrComment)) {
            return false;
        }
        if (!Objects.equals(this.noComment, other.noComment)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.employeeId, other.employeeId)) {
            return false;
        }
        if (!Objects.equals(this.commInterpersonalSkill, other.commInterpersonalSkill)) {
            return false;
        }
        if (!Objects.equals(this.supervisoryDevelopment, other.supervisoryDevelopment)) {
            return false;
        }
        if (!Objects.equals(this.teamBuilding, other.teamBuilding)) {
            return false;
        }
        if (!Objects.equals(this.sellingNegotiationSkill, other.sellingNegotiationSkill)) {
            return false;
        }
        if (!Objects.equals(this.crm, other.crm)) {
            return false;
        }
        if (!Objects.equals(this.presentationSkill, other.presentationSkill)) {
            return false;
        }
        if (!Objects.equals(this.timeManagement, other.timeManagement)) {
            return false;
        }
        if (!Objects.equals(this.functional, other.functional)) {
            return false;
        }
        if (!Objects.equals(this.correctionFactor, other.correctionFactor)) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AdditionalDetails{" + "id=" + id + ", employeeId=" + employeeId + ", specialContribution=" + specialContribution + ", areaOfImprovement=" + areaOfImprovement + ", commInterpersonalSkill=" + commInterpersonalSkill + ", supervisoryDevelopment=" + supervisoryDevelopment + ", teamBuilding=" + teamBuilding + ", sellingNegotiationSkill=" + sellingNegotiationSkill + ", crm=" + crm + ", presentationSkill=" + presentationSkill + ", timeManagement=" + timeManagement + ", functional=" + functional + ", hodComment=" + hodComment + ", hrComment=" + hrComment + ", noComment=" + noComment + ", correctionFactor=" + correctionFactor + ", year=" + year + '}';
    }    
       
}
