package com.znshadows.rateofexchange.mvp.presenters;

import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.views.IChooseBankView;
import com.znshadows.rateofexchange.mvp.views.IWidgetView;

import java.util.List;

/**
 * Created by kostya on 17.05.2017.
 */

public interface IWidgetPresenter<ViewType extends IWidgetView> extends IBasePreseter<ViewType> {

    List<BANKS> getChoosenBanks();

    void getRatesInMyBank(int[] allWidgetIds);
}
