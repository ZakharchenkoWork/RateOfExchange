package com.znshadows.rateofexchange.general.activities.rate_list;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.activities.BaseActivity;
import com.znshadows.rateofexchange.general.activities.main.*;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.mvp.presenters.IBankRatesPresenter;
import com.znshadows.rateofexchange.mvp.views.IBankRatesView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by kostya on 01.06.2017.
 */

public class BankRatesActivity extends BaseActivity implements IBankRatesView{
    public static final String EXTRA_BANK_INDEX = "bank index";
    @Inject
    IBankRatesPresenter presenter;
    private RecyclerView list;
    private int bankIndx = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!getIntent().hasExtra(EXTRA_BANK_INDEX) || (bankIndx = getIntent().getIntExtra(EXTRA_BANK_INDEX, -1)) == -1 ){finish();}
        setContentView(R.layout.activity_choose_bank);

        setupToolbar(R.drawable.nav_drawer_icon, getResources().getStringArray(R.array.bankNames)[bankIndx]);
        list = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        presenter.getBankRates(BANKS.values()[bankIndx]);
    }

    @Override
    public void showResponce(List<UnifiedBankResponce> nbuResponse) {
        list.setAdapter(new RateListAdapter(this, BANKS.values()[bankIndx], nbuResponse));
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
        presenter.setView(this);
    }
}
