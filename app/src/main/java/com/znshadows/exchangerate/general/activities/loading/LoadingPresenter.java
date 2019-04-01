package com.znshadows.exchangerate.general.activities.loading;

import com.znshadows.exchangerate.App;
import com.znshadows.exchangerate.general.activities.BasePresenter;
import com.znshadows.exchangerate.mvp.models.IUserData;
import com.znshadows.exchangerate.mvp.presenters.ILoadingPresenter;
import com.znshadows.exchangerate.mvp.views.ILoadingView;

import javax.inject.Inject;

/**
 * Created by Natali Zakharchenko on 17.05.2017.
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
