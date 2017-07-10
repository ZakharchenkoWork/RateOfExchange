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


    /**
     * saves date from this user to the database.
     */
    void saveData();

    /**
     * Populates this user with data from database.
     */
    void loadData();

    /**
     * Retrieves list of all banks that was choosen by user,
     * with rates.
     */
    ArrayList<ChoosenBank> getBanksList();


    /**
     * @return list of all banks that was choosen by user.
     */
    List<BANKS> getRawBanksList();
    /**
     * @return banks settings for specified bank.
     */
    ChoosenBank getChosenBank(BANKS bank);

    /**
     * @return list of currencies for specified bank, choosen by user.
     */
    List<String> getChoosenCurrencies(BANKS bank);

    /**
     * Adds currency to specific bank
     * @param bank
     * @param currency
     */
    void addCurencyToBank(BANKS bank, String currency);

    /**
     * Removes currency from specified bank
     * @param bank
     * @param currency
     */
    void removeCurencyFromBank(BANKS bank, String currency);

    /**
     * Setter for banks list
     * @param banks
     */
    void setChoosenBanks(List<BANKS> banks);
}
