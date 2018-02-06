/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.form2details;

import java.util.Objects;

/**
 *
 * @author User
 */
public class Form2Details {
    private Integer id;
    private Integer employeeId;
    private Parameter parameter;
    private Double weightage;
    private Integer rating;
    private Double ratingScore;
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

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public Double getWeightage() {
        return weightage;
    }

    public void setWeightage(Double weightage) {
        this.weightage = weightage;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(Double ratingScore) {
        this.ratingScore = ratingScore;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.employeeId);
        hash = 53 * hash + Objects.hashCode(this.parameter);
        hash = 53 * hash + Objects.hashCode(this.weightage);
        hash = 53 * hash + Objects.hashCode(this.rating);
        hash = 53 * hash + Objects.hashCode(this.ratingScore);
        hash = 53 * hash + Objects.hashCode(this.year);
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
        final Form2Details other = (Form2Details) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.employeeId, other.employeeId)) {
            return false;
        }
        if (this.parameter != other.parameter) {
            return false;
        }
        if (!Objects.equals(this.weightage, other.weightage)) {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating)) {
            return false;
        }
        if (!Objects.equals(this.ratingScore, other.ratingScore)) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Form2Details{" + "id=" + id + ", employeeId=" + employeeId + ", parameter=" + parameter + ", weightage=" + weightage + ", rating=" + rating + ", ratingScore=" + ratingScore + ", year=" + year + '}';
    }

        
}
