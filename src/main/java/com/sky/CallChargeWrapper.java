package com.sky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thandomafela on 17/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallChargeWrapper {

    @JsonProperty("calls")
    private List<CallWrapper> calls = new ArrayList<CallWrapper>();

    @JsonProperty("total")
    private double total;

    public List<CallWrapper> getCalls() {
        return calls;
    }

    public void setCalls(List<CallWrapper> calls) {
        this.calls = calls;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
