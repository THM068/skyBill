package com.sky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by thandomafela on 17/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallWrapper {

    private String called;
    private String duration;
    private double cost;

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
