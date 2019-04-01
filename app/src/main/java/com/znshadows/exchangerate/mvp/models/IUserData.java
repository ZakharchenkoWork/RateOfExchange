package com.znshadows.exchangerate.mvp.models;

import com.znshadows.exchangerate.general.models.BANKS;
import com.znshadows.exchangerate.general.models.ChosenBank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Natali Zakharchenko on 21.06.2017.
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
    ArrayList<ChosenBank> getBanksList();


    /**
     * @return list of all banks that was choosen by user.
     */
    List<BANKS> getRawBanksList();

    /**
     * @return banks settings for specified bank.
     */
    ChosenBank getChosenBank(BANKS bank);

    /**
     * @return list of currencies for specified bank, choosen by user.
     */
    List<String> getChosenCurrencies(BANKS bank);

    /**
     * Adds currency to specific bank
     *
     * @param bank
     * @param currency
     */
    void addCurencyToBank(BANKS bank, String currency);

    /**
     * Removes currency from specified bank
     *
     * @param bank
     * @param currency
     */
    void removeCurencyFromBank(BANKS bank, String currency);

    /**
     * Setter for banks list
     *
     * @param banks
     */
    void setChosenBanks(List<BANKS> banks);
}
