package com.znshadows.rateofexchange.mvp.presenters;

import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.views.IBaseView;
import com.znshadows.rateofexchange.mvp.views.IChooseBankView;
import com.znshadows.rateofexchange.mvp.views.IMainView;

import java.util.List;

/**
 * Created by kostya on 17.05.2017.
 */

public interface IChoosseBankPresenter<ViewType extends IChooseBankView> extends IBasePreseter<ViewType> {

    List<BANKS> getChoosenBanks();
    void saveChoosenBanks(List<BANKS> banks);
}
