package com.znshadows.exchangerate.general.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.znshadows.exchangerate.App;
import com.znshadows.exchangerate.mvp.models.IDatabaseManager;
import com.znshadows.exchangerate.mvp.models.IUserData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Natali Zakharchenko on 30.05.2017.
 */

@DatabaseTable(tableName = "user_data")
public class UserData implements IUserData {
    private static final int ID = 1;
    @Inject
    IDatabaseManager databaseManager;
    @DatabaseField(id = true)
    private int userId = ID;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<ChosenBank> banksList = new ArrayList<>();

    public UserData() {
    }


    @Override
    public void saveData() {
        databaseManager.saveUserData(this);
    }

    @Override
    public void loadData() {
        if (databaseManager == null) {
            App.getAppComponent().inject(this);
        }
        UserData oldUserData;
        if ((oldUserData = databaseManager.getLastUserData()) != null) {
            this.banksList = oldUserData.banksList;
        }
    }

    @Override
    public ArrayList<ChosenBank> getBanksList() {
        return banksList;
    }

    @Override
    public List<BANKS> getRawBanksList() {
        List<BANKS> result = new ArrayList<>();
        for (int i = 0; i < banksList.size(); i++) {
            result.add(banksList.get(i).getBank());
        }
        return result;
    }

    /**
     * @return banks settings for specified bank. Or null if bank is not set yet.
     */
    @Override
    public ChosenBank getChosenBank(BANKS bank) {
        for (ChosenBank chosenBank : banksList) {
            if (chosenBank.getBank() == bank) {
                return chosenBank;
            }
        }
        return null;
    }

    @Override
    public List<String> getChosenCurrencies(BANKS bank) {
        for (ChosenBank chosenBank : banksList) {
            if (chosenBank.getBank() == bank) {
                return chosenBank.getCurencies();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void addCurencyToBank(BANKS bank, String currency) {
        ChosenBank chosenBank;
        if ((chosenBank = getChosenBank(bank)) == null) {
            chosenBank = new ChosenBank(bank);
            chosenBank.addCurrency(currency);
            banksList.add(chosenBank);
        } else {
            chosenBank.addCurrency(currency);
        }
    }

    @Override
    public void removeCurencyFromBank(BANKS bank, String currency) {
        ChosenBank chosenBank;
        if ((chosenBank = getChosenBank(bank)) != null) {
            chosenBank.removeCurrency(currency);
        }
    }

    /**
     * Use to update list if banks choosen by users, preserves old banks settings if they are still in the list.
     *
     * @param banks list of all banks that has been choosen this time.
     */
    @Override
    public void setChosenBanks(List<BANKS> banks) {
        for (int bankIndex = 0; bankIndex < banks.size(); bankIndex++) {
            boolean isBankInList = false;
            for (int chosenBankIndex = 0; chosenBankIndex < banksList.size(); chosenBankIndex++) {
                if (banksList.get(chosenBankIndex).getBank() == banks.get(bankIndex)) {
                    isBankInList = true;
                }
            }
            if (!isBankInList) {
                banksList.add(new ChosenBank(banks.get(bankIndex)));
            }
        }
        for (int i = 0; i < banksList.size(); i++) {
            if (!banks.contains(banksList.get(i).getBank())) {
                banksList.remove(i);
                i--;
            }
        }

    }


}
