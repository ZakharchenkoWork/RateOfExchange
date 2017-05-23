
package com.znshadows.rateofexchange.general.models.nbu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NBUResponse {

    @SerializedName("r030")
    @Expose
    private int r030;
    @SerializedName("txt")
    @Expose
    private String name;
    @SerializedName("rate")
    @Expose
    private double rate;
    @SerializedName("cc")
    @Expose
    private String code;
    @SerializedName("exchangedate")
    @Expose
    private String exchangedate;

    public int getR030() {
        return r030;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public String getCode() {
        return code;
    }

    public String getExchangedate() {
        return exchangedate;
    }
}
