package com.znshadows.rateofexchange.general.activities.main;

import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.activities.BaseActivity;

import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;

import com.znshadows.rateofexchange.mvp.presenters.IMainPresenter;
import com.znshadows.rateofexchange.mvp.views.IMainView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by kostya on 17.05.2017.
 */


public class MainActivity extends BaseActivity implements IMainView {
    @Inject
    IMainPresenter presenter;

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
        presenter.setView(this);
    }

    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        presenter.getNBU();
    }

    @Override
    public void showResponce(List<UnifiedBankResponce> nbuResponse) {
        list.setAdapter(new RateListAdapter(this, nbuResponse));
    }
}

