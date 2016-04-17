package com.sky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thandomafela on 17/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkyStoreWrapper {

    @JsonProperty("total")
    private double total;

    @JsonProperty("buyAndKeep")
    private List<BuyAndKeepWrapper> buyAndKeep = new ArrayList<BuyAndKeepWrapper>();

    @JsonProperty("rentals")
    private List<RentalWrapper> rentals = new ArrayList<RentalWrapper>();

    public List<RentalWrapper> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalWrapper> rentals) {
        this.rentals = rentals;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<BuyAndKeepWrapper> getBuyAndKeep() {
        return buyAndKeep;
    }

    public void setBuyAndKeep(List<BuyAndKeepWrapper> buyAndKeep) {
        this.buyAndKeep = buyAndKeep;
    }
}
