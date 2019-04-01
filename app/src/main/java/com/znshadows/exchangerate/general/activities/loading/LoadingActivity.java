package com.znshadows.exchangerate.general.activities.loading;

import android.content.Intent;
import android.os.Bundle;

import com.znshadows.exchangerate.App;
import com.znshadows.exchangerate.general.activities.BaseActivity;
import com.znshadows.exchangerate.general.activities.main.MainActivity;
import com.znshadows.exchangerate.mvp.presenters.ILoadingPresenter;
import com.znshadows.exchangerate.mvp.views.ILoadingView;

import javax.inject.Inject;
/**
 * Created by Natali Zakharchenko on 17.05.2017.
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
