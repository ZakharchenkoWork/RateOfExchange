package com.znshadows.rateofexchange.general.activities.loading;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.auxilary.OneButtonDialog;
import com.znshadows.rateofexchange.general.activities.BaseActivity;
import com.znshadows.rateofexchange.general.activities.main.MainActivity;
import com.znshadows.rateofexchange.general.activities.widget_settings.WidgetSettingsActivity;
import com.znshadows.rateofexchange.mvp.presenters.ILoadingPresenter;
import com.znshadows.rateofexchange.mvp.views.ILoadingView;

import javax.inject.Inject;

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
