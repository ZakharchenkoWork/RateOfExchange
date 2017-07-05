package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.ChoosenBank;
import com.znshadows.rateofexchange.general.models.UserData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kostya on 21.06.2017.
 */

public interface IUserData {




    void saveData();

    void loadData();

    ArrayList<ChoosenBank> getBanksList();
    List<BANKS> getRawBanksList();

    ChoosenBank getChosenBank(BANKS bank);

    List<String> getChoosenCurrencies(BANKS bank);

    void addCurencyToBank(BANKS bank, String currency);
    void removeCurencyFromBank(BANKS bank, String currency);

    void addBank(BANKS bank);

    void setChoosenBanks(List<BANKS> banks);
}
