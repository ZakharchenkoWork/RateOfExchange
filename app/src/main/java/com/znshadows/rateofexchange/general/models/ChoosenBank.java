package com.znshadows.rateofexchange.general.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kostya on 17.06.2017.
 */
@DatabaseTable(tableName = "choosen_banks")
public class ChoosenBank {
    public static final String NOT_SET = "";
    @DatabaseField
    private BANKS bank = null;
    @DatabaseField
    private List<String> curencies = new ArrayList<>();

    public ChoosenBank(BANKS bank) {
        this.bank = bank;
    }

    public List<String> getCurencies() {
        return curencies;
    }
    public void addCurrency(String curency){
        for (int i = 0; i < curencies.size(); i++) {
            if(curencies.equals(curency)){
                return;
            }
        }
        curencies.add(curency);
    }

    public BANKS getBank() {
        return bank;
    }


    public void removeCurrency(String currency) {
        for (int i = 0; i < curencies.size(); i++) {
            if(curencies.get(i).equals(currency)){
                curencies.remove(i);
                return;
            }
        }
    }
}
