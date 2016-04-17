package com.sky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by thandomafela on 15/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class StatementWrapper {

    @JsonProperty("period")
    private PeriodWrapper period;

    private String generated;
    private String due;

    public String getGenerated() {
        return generated;
    }

    public void setGenerated(String generated) {
        this.generated = generated;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public PeriodWrapper getPeriod() {
        return period;
    }

    public void setPeriod(PeriodWrapper period) {
        this.period = period;
    }
}
