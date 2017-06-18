package com.znshadows.rateofexchange.general.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by kostya on 17.06.2017.
 */
@DatabaseTable(tableName = "choosen_banks")
public class ChoosenBank {
    @DatabaseField
    private BANKS bank = null;
    @DatabaseField
    private String curency = "USD";

    public ChoosenBank(BANKS bank, String curency) {
        this.bank = bank;
        this.curency = curency;
    }

    public BANKS getBank() {
        return bank;
    }

    public String getCurency() {
        return curency;
    }

    public void setCurency(String curency) {
        this.curency = curency;
    }
}
