package com.znshadows.rateofexchange.mvp.views;

import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.general.models.nbu.NBUResponse;

import java.util.List;

/**
 * Created by kostya on 17.05.2017.
 */

public interface IMainView extends IBaseView{
    void showResponce(List<UnifiedBankResponce> nbuResponse);
}
