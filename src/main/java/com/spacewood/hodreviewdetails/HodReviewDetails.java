/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.hodreviewdetails;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author webdesign
 */
public class HodReviewDetails {

    private Integer id;
    private Integer employeeId;
    private String performance;
    private String challenge;
    private String training;
    private String expectation;
    private String noComment;
    private String dirComment;
    private Date noUpdatedDate;
    private Date dirUpdatedDate;
    private List<Integer> tags;

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

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getExpectation() {
        return expectation;
    }

    public void setExpectation(String expectation) {
        this.expectation = expectation;
    }

    public String getNoComment() {
        return noComment;
    }

    public void setNoComment(String noComment) {
        this.noComment = noComment;
    }

    public String getDirComment() {
        return dirComment;
    }

    public void setDirComment(String dirComment) {
        this.dirComment = dirComment;
    }

    public Date getNoUpdatedDate() {
        return noUpdatedDate;
    }

    public void setNoUpdatedDate(Date noUpdatedDate) {
        this.noUpdatedDate = noUpdatedDate;
    }

    public Date getDirUpdatedDate() {
        return dirUpdatedDate;
    }

    public void setDirUpdatedDate(Date dirUpdatedDate) {
        this.dirUpdatedDate = dirUpdatedDate;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.employeeId);
        hash = 53 * hash + Objects.hashCode(this.performance);
        hash = 53 * hash + Objects.hashCode(this.challenge);
        hash = 53 * hash + Objects.hashCode(this.training);
        hash = 53 * hash + Objects.hashCode(this.expectation);
        hash = 53 * hash + Objects.hashCode(this.noComment);
        hash = 53 * hash + Objects.hashCode(this.dirComment);
        hash = 53 * hash + Objects.hashCode(this.noUpdatedDate);
        hash = 53 * hash + Objects.hashCode(this.dirUpdatedDate);
        hash = 53 * hash + Objects.hashCode(this.tags);
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
        final HodReviewDetails other = (HodReviewDetails) obj;
        if (!Objects.equals(this.performance, other.performance)) {
            return false;
        }
        if (!Objects.equals(this.challenge, other.challenge)) {
            return false;
        }
        if (!Objects.equals(this.training, other.training)) {
            return false;
        }
        if (!Objects.equals(this.expectation, other.expectation)) {
            return false;
        }
        if (!Objects.equals(this.noComment, other.noComment)) {
            return false;
        }
        if (!Objects.equals(this.dirComment, other.dirComment)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.employeeId, other.employeeId)) {
            return false;
        }
        if (!Objects.equals(this.noUpdatedDate, other.noUpdatedDate)) {
            return false;
        }
        if (!Objects.equals(this.dirUpdatedDate, other.dirUpdatedDate)) {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HodReviewDetails{" + "id=" + id + ", employeeId=" + employeeId + ", performance=" + performance + ", challenge=" + challenge + ", training=" + training + ", expectation=" + expectation + ", noComment=" + noComment + ", dirComment=" + dirComment + ", noUpdatedDate=" + noUpdatedDate + ", dirUpdatedDate=" + dirUpdatedDate + ", tags=" + tags + '}';
    }
    
}
