package com.znshadows.rateofexchange.mvp.views;

import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;

import java.util.List;

/**
 * Created by kostya on 17.05.2017.
 */

public interface IWidgetView extends IBaseView{

    void showResponce(int[] allWidgetIds, List<UnifiedBankResponce> bankResponse);
}
