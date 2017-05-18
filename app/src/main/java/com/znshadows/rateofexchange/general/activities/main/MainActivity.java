package com.znshadows.rateofexchange.general.activities.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.activities.BaseActivity;
import com.znshadows.rateofexchange.general.activities.loading.LoadingPresenter;
import com.znshadows.rateofexchange.mvp.presenters.ILoadingPresenter;
import com.znshadows.rateofexchange.mvp.views.ILoadingView;
import com.znshadows.rateofexchange.mvp.views.IMainView;

/**
 * Created by kostya on 17.05.2017.
 */



public class MainActivity extends BaseActivity implements IMainView {
    ILoadingPresenter presenter = new LoadingPresenter();

    @Override
    public void resolveDaggerDependencies() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder_layout);
    }
}

