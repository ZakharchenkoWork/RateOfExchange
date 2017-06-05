package com.znshadows.rateofexchange.general.models.responces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kostya on 03.06.2017.
 */

public class PrivateBankResponce {
    //{"ccy":"EUR","base_ccy":"UAH","buy":"29.20000","sale":"29.80000"}
    @SerializedName("buy")
    @Expose
    private double buy;
    @SerializedName("sale")
    @Expose
    private double sale;
    @SerializedName("ccy")
    @Expose
    private String code;

    public double getBuy() {
        return buy;
    }

    public double getSale() {
        return sale;
    }

    public String getCode() {
        return code;
    }


}
