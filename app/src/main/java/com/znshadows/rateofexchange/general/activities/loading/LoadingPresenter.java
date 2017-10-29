package com.znshadows.rateofexchange.general.activities.loading;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.mvp.models.IUserData;
import com.znshadows.rateofexchange.mvp.presenters.ILoadingPresenter;
import com.znshadows.rateofexchange.mvp.views.ILoadingView;

import javax.inject.Inject;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

public class LoadingPresenter extends BasePresenter<ILoadingView> implements ILoadingPresenter<ILoadingView> {

    @Inject
    IUserData userData;

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void loadData() {
        userData.loadData();
        getView().onDataLoaded();
    }
}
