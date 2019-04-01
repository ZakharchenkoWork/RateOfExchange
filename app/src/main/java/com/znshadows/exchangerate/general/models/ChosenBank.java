package com.znshadows.exchangerate.general.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natali Zakharchenko on 17.06.2017.
 */
@DatabaseTable(tableName = "choosen_banks")
public class ChosenBank implements Serializable{
    public static final String NOT_SET = "";
    @DatabaseField
    private BANKS bank = null;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<String> curencies = new ArrayList<>();

    public ChosenBank() {

    }
    public ChosenBank(BANKS bank) {
        this.bank = bank;
    }

    public List<String> getCurencies() {
        return curencies;
    }

    public void addCurrency(String curency) {
        for (int i = 0; i < curencies.size(); i++) {
            if (curencies.equals(curency)) {
                return;
            }
        }
        curencies.add(curency);
    }

    public BANKS getBank() {
        return bank;
    }

    public boolean checkCurrency(String currency) {
        for (String curencyFromList : curencies) {
            if (curencyFromList.equals(currency)) {
                return true;
            }
        }
        return false;
    }

    public void removeCurrency(String currency) {
        for (int i = 0; i < curencies.size(); i++) {
            if (curencies.get(i).equals(currency)) {
                curencies.remove(i);
                return;
            }
        }
    }
}
