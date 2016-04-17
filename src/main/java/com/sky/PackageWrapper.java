package com.sky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thandomafela on 15/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class PackageWrapper {

    @JsonProperty("subscriptions")
    private List<SubscriptionWrapper> subscriptions = new ArrayList<SubscriptionWrapper>();

    private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<SubscriptionWrapper> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriptionWrapper> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
