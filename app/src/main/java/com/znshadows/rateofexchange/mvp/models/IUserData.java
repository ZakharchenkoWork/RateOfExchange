package com.znshadows.rateofexchange.mvp.models;

import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.ChoosenBank;

import java.util.List;

/**
 * Created by kostya on 21.06.2017.
 */

public interface IUserData {


    List<ChoosenBank> getBanksList();
    List<BANKS> getRawBanksList();

    ChoosenBank getChosenBank(BANKS bank);

    List<String> getChoosenCurrencies(BANKS bank);

    void addCurencyToBank(BANKS bank, String currency);
    void removeCurencyFromBank(BANKS bank, String currency);

    void addBank(BANKS bank);

    void saveChoosenBanks(List<BANKS> banks);
}
