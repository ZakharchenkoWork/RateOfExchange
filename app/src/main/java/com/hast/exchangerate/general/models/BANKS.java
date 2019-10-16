package com.hast.exchangerate.general.models;

/**
 * Created by Konstantyn Zakharchenko on 30.05.2017.
 */

/**
 * Procedure for adding new bank.
 * Add a constant to this enum
 * Add interface and Implementation to {@link com.hast.exchangerate.mvp.models} package
 * Add provide code in class {@link com.hast.exchangerate.di.modules.ModelModule}
 * Add a case to the switch in class {@link com.hast.exchangerate.mvp.models.UnifiedModel#getBankApi(BANKS)}
 * Add injection of the Api in class {@link com.hast.exchangerate.mvp.models.UnifiedModel}
 * Add name of the bank to bankNames array at strings.xml
 * Add bank icon to res/drawable and a reference at arrays.xml

 */
public enum BANKS {
    NBU,
    PRIVATE_BANK,
    A_BANK,
    ALFABANK,
    OTP_BANK,
    RAIFFEIZEN_BANK_AVAL,
    UKRSIBBANK
}
