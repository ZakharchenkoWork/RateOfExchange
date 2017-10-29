package com.znshadows.rateofexchange.general.activities.loading;

import android.content.Intent;
import android.os.Bundle;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BaseActivity;
import com.znshadows.rateofexchange.general.activities.main.MainActivity;
import com.znshadows.rateofexchange.mvp.presenters.ILoadingPresenter;
import com.znshadows.rateofexchange.mvp.views.ILoadingView;

import javax.inject.Inject;
/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

public class LoadingActivity extends BaseActivity implements ILoadingView {
    @Inject
    ILoadingPresenter presenter;

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
        presenter.setView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.loadData();

    }

    /**
     * Moves to MainActivity
     * Called after {@link ILoadingPresenter#loadData()}.
     */
    @Override
    public void onDataLoaded() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
