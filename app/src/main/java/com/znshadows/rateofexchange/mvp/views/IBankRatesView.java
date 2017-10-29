package com.znshadows.rateofexchange.mvp.views;

import com.znshadows.rateofexchange.general.models.UnifiedBankResponse;

import java.util.List;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.rateofexchange.general.activities.rate_list.BankRatesActivity}
 */
public interface IBankRatesView extends IBaseView {

    void showResponse(List<UnifiedBankResponse> nbuResponse);
}
