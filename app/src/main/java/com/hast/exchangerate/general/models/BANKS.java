package com.hast.exchangerate.general.models;

/**
 * Created by Konstantyn Zakharchenko on 30.05.2017.
 */

/**
 * Procedure for adding new bank.
 * Add a constant to this enum
 * Add interface and Implementation to mvp/models package
 * Add provide code in class ModelModule
 * Add a case to the switch in class UnifiedModel
 * Add injection of the Api in class UnifiedModel
 * Add name of the bank to bankNames array at strings.xml
 * Add bank icon to res/drawable and a reference at arrays.xml

 */
public enum BANKS {
    NBU,
    PRIVATE_BANK,
    A_BANK,
    ALFABANK,
    OTP_BANK,
    RAIFFEIZEN_BANK_AVAL
}
