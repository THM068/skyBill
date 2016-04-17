package com.sky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by thandomafela on 15/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class PeriodWrapper {

    private String to;
    private String from;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
