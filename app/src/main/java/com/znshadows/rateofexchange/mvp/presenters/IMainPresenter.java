package com.znshadows.rateofexchange.mvp.presenters;

import com.znshadows.rateofexchange.general.activities.main.MainPresenter;
import com.znshadows.rateofexchange.general.models.ChoosenBank;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.mvp.views.IMainView;

import java.util.List;

/**
 * Created by kostya on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.rateofexchange.general.activities.main.MainPresenter}
 * @param <ViewType> Activity
 */
public interface IMainPresenter<ViewType extends IMainView> extends IBasePreseter<ViewType> {
    interface OnBankRatesLoadedListener {
        void onFinish(List<UnifiedBankResponce> result);
    }

    /**
     * Retrieves rates from specified banks API
     * @param bank target
     * @param onBankRatesLoadedListener callback
     */
    void getBankRates(ChoosenBank bank, OnBankRatesLoadedListener onBankRatesLoadedListener);
    /**
     * Retrieves banks that was choosen by user. <p>
     * After finish calls {@link IMainView#showChoosenBanks(List)}
     * <p>or<p>
     * calls {@link IMainView#showNoBanksMessage()}
     */
    void getChoosenBanks();
}
