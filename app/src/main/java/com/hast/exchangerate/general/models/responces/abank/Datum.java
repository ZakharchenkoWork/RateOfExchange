
package com.hast.exchangerate.general.models.responces.abank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("rateA")
    @Expose
    private double rateA;
    @SerializedName("rateB")
    @Expose
    private double rateB;
    @SerializedName("ccyA")
    @Expose
    private String ccyA;
    @SerializedName("ccyB")
    @Expose
    private String ccyB;

    public double getRateA() {
        return rateA;
    }

    public void setRateA(double rateA) {
        this.rateA = rateA;
    }

    public double getRateB() {
        return rateB;
    }

    public void setRateB(double rateB) {
        this.rateB = rateB;
    }

    public String getCcyA() {
        return ccyA;
    }

    public void setCcyA(String ccyA) {
        this.ccyA = ccyA;
    }

    public String getCcyB() {
        return ccyB;
    }

    public void setCcyB(String ccyB) {
        this.ccyB = ccyB;
    }

}
