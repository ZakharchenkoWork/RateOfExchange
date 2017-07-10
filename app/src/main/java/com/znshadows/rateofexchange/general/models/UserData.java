package com.znshadows.rateofexchange.general.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.mvp.models.IDatabaseManager;
import com.znshadows.rateofexchange.mvp.models.IUserData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.znshadows.rateofexchange.general.models.ChoosenBank.NOT_SET;

/**
 * Created by kostya on 30.05.2017.
 */

@DatabaseTable(tableName = "user_data")
public class UserData implements IUserData {
    @Inject
    IDatabaseManager databaseManager;
    @DatabaseField(id = true)
    private int userId = 1;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private ArrayList<ChoosenBank> banksList = new ArrayList<>();

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
    public ArrayList<ChoosenBank> getBanksList() {
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
    public ChoosenBank getChosenBank(BANKS bank) {
        for (ChoosenBank choosenBank : banksList) {
            if (choosenBank.getBank() == bank) {
                return choosenBank;
            }
        }
        return null;
    }

    @Override
    public List<String> getChoosenCurrencies(BANKS bank) {
        for (ChoosenBank choosenBank : banksList) {
            if (choosenBank.getBank() == bank) {
                return choosenBank.getCurencies();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void addCurencyToBank(BANKS bank, String currency) {
        ChoosenBank choosenBank;
        if ((choosenBank = getChosenBank(bank)) == null) {
            choosenBank = new ChoosenBank(bank);
            choosenBank.addCurrency(currency);
            banksList.add(choosenBank);
        } else {
            choosenBank.addCurrency(currency);
        }
    }

    @Override
    public void removeCurencyFromBank(BANKS bank, String currency) {
        ChoosenBank choosenBank;
        if ((choosenBank = getChosenBank(bank)) != null) {
            choosenBank.removeCurrency(currency);
        }
    }
    /**
     * Use to update list if banks choosen by users, preserves old banks settings if they are still in the list.
     * @param banks list of all banks that has been choosen this time.
     */
    @Override
    public void setChoosenBanks(List<BANKS> banks) {
        for (int bankIndex = 0; bankIndex < banks.size(); bankIndex++) {
            boolean isBankInList = false;
            for (int choosenBankIndex = 0; choosenBankIndex < banksList.size(); choosenBankIndex++) {
                if (banksList.get(choosenBankIndex).getBank() == banks.get(bankIndex)) {
                    isBankInList = true;
                }
            }
            if (!isBankInList) {
                banksList.add(new ChoosenBank(banks.get(bankIndex)));
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
