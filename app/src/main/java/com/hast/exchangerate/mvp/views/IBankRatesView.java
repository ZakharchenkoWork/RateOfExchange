package com.hast.exchangerate.mvp.views;

import com.hast.exchangerate.general.models.UnifiedBankResponse;

import java.util.List;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.hast.exchangerate.general.activities.rate_list.BankRatesActivity}
 */
public interface IBankRatesView extends IBaseView {

    void showResponse(List<UnifiedBankResponse> nbuResponse);
}
