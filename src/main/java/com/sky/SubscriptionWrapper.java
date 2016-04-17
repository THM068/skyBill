package com.sky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by thandomafela on 17/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class SubscriptionWrapper {

    private String type;
    private String name;
    private double cost;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
