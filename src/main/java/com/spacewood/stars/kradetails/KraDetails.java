/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.kradetails;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author webdesign
 */
public class KraDetails {
    private Integer id;
    private KraParameter kraParameter;
    private String performanceObjective;
    private String measurableTarget;
    private MIRMatrix mirMatrix;
    private Double weightage;
    private Integer rating;
    private Double ratingScore;
    private Integer employeeId;
    private Integer year;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public KraParameter getKraParameter() {
        return kraParameter;
    }

    public void setKraParameter(KraParameter kraParameter) {
        this.kraParameter = kraParameter;
    }

    public String getPerformanceObjective() {
        return performanceObjective;
    }

    public void setPerformanceObjective(String performanceObjective) {
        this.performanceObjective = performanceObjective;
    }

    public String getMeasurableTarget() {
        return measurableTarget;
    }

    public void setMeasurableTarget(String measurableTarget) {
        this.measurableTarget = measurableTarget;
    }

    public MIRMatrix getMirMatrix() {
        return mirMatrix;
    }

    public void setMirMatrix(MIRMatrix mirMatrix) {
        this.mirMatrix = mirMatrix;
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.kraParameter);
        hash = 41 * hash + Objects.hashCode(this.performanceObjective);
        hash = 41 * hash + Objects.hashCode(this.measurableTarget);
        hash = 41 * hash + Objects.hashCode(this.mirMatrix);
        hash = 41 * hash + Objects.hashCode(this.weightage);
        hash = 41 * hash + Objects.hashCode(this.rating);
        hash = 41 * hash + Objects.hashCode(this.ratingScore);
        hash = 41 * hash + Objects.hashCode(this.employeeId);
        hash = 41 * hash + Objects.hashCode(this.year);
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
        final KraDetails other = (KraDetails) obj;
        if (!Objects.equals(this.performanceObjective, other.performanceObjective)) {
            return false;
        }
        if (!Objects.equals(this.measurableTarget, other.measurableTarget)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.kraParameter != other.kraParameter) {
            return false;
        }
        if (this.mirMatrix != other.mirMatrix) {
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
        if (!Objects.equals(this.employeeId, other.employeeId)) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KraDetails{" + "id=" + id + ", kraParameter=" + kraParameter + ", performanceObjective=" + performanceObjective + ", measurableTarget=" + measurableTarget + ", mirMatrix=" + mirMatrix + ", weightage=" + weightage + ", rating=" + rating + ", ratingScore=" + ratingScore + ", employeeId=" + employeeId + ", year=" + year + '}';
    }

    
}
