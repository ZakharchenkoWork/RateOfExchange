package com.znshadows.exchangerate.general.models;

/**
 * Created by Natali Zakharchenko on 23.05.2017.
 */

public class UnifiedBankResponse {
    public static final double NO_VALUE = -1;
    private String name;
    private String code;
    private double buy;
    private double sale;

    public UnifiedBankResponse(String name, String code, double rate) {
        this.name = name;
        this.code = code;
        this.buy = rate;
        this.sale = NO_VALUE;
    }

    public UnifiedBankResponse(String name, String code, double buy, double sale) {
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

