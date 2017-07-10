package com.znshadows.rateofexchange.general.activities.main;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.activities.BaseActivity;

import com.znshadows.rateofexchange.general.activities.choose_bank.ChooseBankActivity;
import com.znshadows.rateofexchange.general.activities.rate_list.BankRatesActivity;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.ChoosenBank;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;

import com.znshadows.rateofexchange.mvp.presenters.IMainPresenter;
import com.znshadows.rateofexchange.mvp.views.IMainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by kostya on 17.05.2017.
 */


public class MainActivity extends BaseActivity implements IMainView {
    @Inject
    IMainPresenter presenter;
    private TextView noBanksText;
    private RecyclerView list;

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getChoosenBanks();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosen_banks_list);
        setupToolbar(R.drawable.no_icon, getString(R.string.main_activity_title));

        list = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        noBanksText = (TextView) findViewById(R.id.noBanksText);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener((view) -> {
            Intent intent = new Intent(this, ChooseBankActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void showChoosenBanks(List<ChoosenBank> banks) {
        if(banks != null && banks.size() > 0 ) {
            list.setAdapter(new ChoosenBanksListAdapter(this, banks)
                    .setOnItemClickListener((bank)->{
                        Intent intent = new Intent(this, BankRatesActivity.class);
                        intent.putExtra(BankRatesActivity.EXTRA_BANK_INDEX, bank.ordinal());
                        startActivity(intent);
            }));
            noBanksText.setVisibility(View.GONE);
        } else {
            list.setAdapter(new ChoosenBanksListAdapter(this, banks));
            noBanksText.setVisibility(View.VISIBLE);
        }
    }


}

