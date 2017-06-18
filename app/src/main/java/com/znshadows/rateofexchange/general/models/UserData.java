package com.znshadows.rateofexchange.general.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kostya on 30.05.2017.
 */

@DatabaseTable(tableName = "user_data")
public class UserData {

    @DatabaseField
    private List<ChoosenBank> banksList = new ArrayList<>();

    public List<BANKS> getBanksList() {
        List<BANKS> result = new ArrayList<>();
        for (int i = 0; i < banksList.size(); i++) {
            result.add(banksList.get(i).getBank());
        }

        return result;
    }
    public ChoosenBank getChosenBank(BANKS bank){
        for (ChoosenBank choosenBank : banksList) {
            if(choosenBank.getBank() == bank){
                return choosenBank;
            }
        }
        return null;
    }

    public void addCurencyToBank(BANKS bank, String currency){
        ChoosenBank choosenBank;
        if((choosenBank = getChosenBank(bank)) == null){
            banksList.add(new ChoosenBank(bank, currency));
        } else {
            choosenBank.setCurency(currency);
        }
    }

    public void addBank(BANKS bank){

        if(getChosenBank(bank) == null){
            banksList.add(new ChoosenBank(bank, "USD"));
        }
    }

}
