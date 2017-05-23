package com.znshadows.rateofexchange.general.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kostya on 23.05.2017.
 */

public class UnifiedBankResponce {
    private String name;
    private String code;
    private double rate;

    public UnifiedBankResponce(String name, String code, double rate) {
        this.name = name;
        this.code = code;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getRate() {
        return rate;
    }
}

