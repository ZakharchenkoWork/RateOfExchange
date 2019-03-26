
package com.znshadows.rateofexchange.general.models.responces;

import com.google.gson.annotations.SerializedName;

public class OtpBankResponce {

    @SerializedName("txt")
    private String name;

    @SerializedName("rate")
    private double rate;

    @SerializedName("cc")
    private String code;

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public String getCode() {
        return code;
    }

}
