package com.znshadows.exchangerate.general.models.responces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Natali Zakharchenko on 03.06.2017.
 */

public class PrivateBankResponse {
    //{"ccy":"EUR","base_ccy":"UAH","buy":"29.20000","sale":"29.80000"}
    @SerializedName("buy")
    private double buy;

    @SerializedName("sale")
    private double sale;

    @SerializedName("ccy")
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
