package com.hast.exchangerate.mvp.presenters;

import com.hast.exchangerate.general.models.ChosenBank;
import com.hast.exchangerate.general.models.UnifiedBankResponse;
import com.hast.exchangerate.mvp.views.IMainView;

import java.util.List;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.hast.exchangerate.general.activities.main.MainPresenter}
 *
 * @param <ViewType> Activity
 */
public interface IMainPresenter<ViewType extends IMainView> extends IBasePresenter<ViewType> {
    interface OnBankRatesLoadedListener {
        void onFinish(List<UnifiedBankResponse> result);
    }

    /**
     * Retrieves rates from specified banks API
     *
     * @param bank                      target
     * @param onBankRatesLoadedListener callback
     */
    void getBankRates(ChosenBank bank, OnBankRatesLoadedListener onBankRatesLoadedListener);

    /**
     * Retrieves banks that was choosen by user. <p>
     * After finish calls {@link IMainView#showChosenBanks(List)}
     * <p>or<p>
     * calls {@link IMainView#showNoBanksMessage()}
     */
    void getChosenBanks();
}
