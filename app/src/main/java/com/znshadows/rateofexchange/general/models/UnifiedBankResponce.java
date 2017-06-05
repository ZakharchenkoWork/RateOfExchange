package com.znshadows.rateofexchange.general.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kostya on 23.05.2017.
 */

public class UnifiedBankResponce {
    public static final double NO_VALUE = -1;
    private String name;
    private String code;
    private double buy;
    private double sale;

    public UnifiedBankResponce(String name, String code, double rate) {
        this.name = name;
        this.code = code;
        this.buy = rate;
        this.sale = NO_VALUE;
    }

    public UnifiedBankResponce(String name, String code, double buy, double sale) {
        this.name = name;
        this.code = code;
        this.buy = buy;
        this.sale = sale;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getBuy() {
        return buy;
    }

    public double getSale() {
        return sale;
    }
}

