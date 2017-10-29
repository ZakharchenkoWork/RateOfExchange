package com.znshadows.rateofexchange.mvp.presenters;

import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.views.IBankRatesView;

import java.util.List;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.rateofexchange.general.activities.rate_list.BankRatesPresenter}
 *
 * @param <ViewType>
 */
public interface IBankRatesPresenter<ViewType extends IBankRatesView> extends IBasePresenter<ViewType> {
    /**
     * Get bank rates from API of the specified bank
     *
     * @param bank
     */
    void getBankRates(BANKS bank);

    /**
     * @return list of currencies for specified bank, choosen by user.
     */
    List<String> getChosenCurrencies(BANKS banks);

    /**
     * Adds currency to specific bank
     *
     * @param bank
     * @param code
     */
    List<String> addCurrency(BANKS bank, String code);

    /**
     * Removes currency from specified bank
     *
     * @param bank
     * @param code
     */
    List<String> removeCurrency(BANKS bank, String code);
}
