package com.hast.exchangerate.general.activities.loading;

import android.content.Intent;
import android.os.Bundle;

import com.hast.exchangerate.App;
import com.hast.exchangerate.general.activities.BaseActivity;
import com.hast.exchangerate.general.activities.main.MainActivity;
import com.hast.exchangerate.mvp.presenters.ILoadingPresenter;
import com.hast.exchangerate.mvp.views.ILoadingView;

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
