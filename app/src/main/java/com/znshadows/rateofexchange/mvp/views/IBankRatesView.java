package com.znshadows.rateofexchange.mvp.views;

import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;

import java.util.List;

/**
 * Created by kostya on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.rateofexchange.general.activities.rate_list.BankRatesActivity}
 */
public interface IBankRatesView extends IBaseView{

    void showResponce(List<UnifiedBankResponce> nbuResponse);
}
