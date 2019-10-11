package com.hast.exchangerate.general.activities.loading;

import com.hast.exchangerate.App;
import com.hast.exchangerate.general.activities.BasePresenter;
import com.hast.exchangerate.mvp.models.IUserData;
import com.hast.exchangerate.mvp.presenters.ILoadingPresenter;
import com.hast.exchangerate.mvp.views.ILoadingView;

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
