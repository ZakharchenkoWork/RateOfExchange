package com.znshadows.rateofexchange.mvp.presenters;

import com.znshadows.rateofexchange.mvp.views.IBaseView;
import com.znshadows.rateofexchange.mvp.views.ILoadingView;

/**
 * Created by kostya on 17.05.2017.
 */

public interface ILoadingPresenter<ViewType extends ILoadingView> extends IBasePreseter<ViewType> {
    void loadData();
}
