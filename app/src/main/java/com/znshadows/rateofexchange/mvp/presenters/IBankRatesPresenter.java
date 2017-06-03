package com.znshadows.rateofexchange.mvp.presenters;

import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.views.IBankRatesView;
import com.znshadows.rateofexchange.mvp.views.IMainView;

/**
 * Created by kostya on 17.05.2017.
 */

public interface IBankRatesPresenter<ViewType extends IBankRatesView> extends IBasePreseter<ViewType> {
    void getBankRates(BANKS bank);

}
