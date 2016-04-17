package com.sky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by thandomafela on 15/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomerBillWrapper implements Serializable {

    private double total;

    @JsonProperty("statement")
    private StatementWrapper statement;

    @JsonProperty("package")
    private PackageWrapper packageWrapper;

    @JsonProperty("callCharges")
    private CallChargeWrapper callCharges;

    @JsonProperty("skyStore")
    private SkyStoreWrapper skyStore;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public StatementWrapper getStatement() {
        return statement;
    }

    public void setStatement(StatementWrapper statement) {
        this.statement = statement;
    }


    public PackageWrapper getPackageWrapper() {
        return packageWrapper;
    }

    public void setPackageWrapper(PackageWrapper packageWrapper) {
        this.packageWrapper = packageWrapper;
    }

    public CallChargeWrapper getCallCharges() {
        return callCharges;
    }

    public void setCallCharges(CallChargeWrapper callCharges) {
        this.callCharges = callCharges;
    }

    public SkyStoreWrapper getSkyStore() {
        return skyStore;
    }

    public void setSkyStore(SkyStoreWrapper skyStore) {
        this.skyStore = skyStore;
    }
}
