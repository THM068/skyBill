package com.sky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by thandomafela on 17/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalWrapper {

    private String title;
    private double cost;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
