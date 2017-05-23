package com.znshadows.rateofexchange.general.activities.loading;

import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.mvp.presenters.ILoadingPresenter;
import com.znshadows.rateofexchange.mvp.views.ILoadingView;

/**
 * Created by kostya on 17.05.2017.
 */

public class LoadingPresenter extends BasePresenter<ILoadingView> implements ILoadingPresenter<ILoadingView>{

    @Override
    public void resolveDaggerDependencies() {

    }
}
