package com.znshadows.rateofexchange.general.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kostya on 30.05.2017.
 */

@DatabaseTable(tableName = "choosen_banks")
public class ChoosenBanks {
    @DatabaseField

    private List<BANKS> banksList = new ArrayList<>();

    public List<BANKS> getBanksList() {
        return banksList;
    }
    public void addBank(BANKS bank){
        if(!banksList.contains(bank)){
            banksList.add(bank);
        }
    }
}
