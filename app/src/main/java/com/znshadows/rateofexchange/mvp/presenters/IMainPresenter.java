package com.znshadows.rateofexchange.mvp.presenters;

import com.znshadows.rateofexchange.mvp.views.IMainView;

/**
 * Created by kostya on 17.05.2017.
 */

public interface IMainPresenter<ViewType extends IMainView> extends IBasePreseter<ViewType> {
    void getNBU();

    void getChoosenBanks();
}
