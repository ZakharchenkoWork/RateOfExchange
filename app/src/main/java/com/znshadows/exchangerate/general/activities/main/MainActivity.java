package com.znshadows.exchangerate.general.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.znshadows.exchangerate.App;
import com.znshadows.exchangerate.R;
import com.znshadows.exchangerate.general.activities.BaseActivity;
import com.znshadows.exchangerate.general.activities.choose_bank.ChooseBankActivity;
import com.znshadows.exchangerate.general.activities.rate_list.BankRatesActivity;
import com.znshadows.exchangerate.general.models.ChosenBank;
import com.znshadows.exchangerate.mvp.presenters.IMainPresenter;
import com.znshadows.exchangerate.mvp.views.IMainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Natali Zakharchenko on 17.05.2017.
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
        presenter.getChosenBanks();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chosen_banks_list);
        setupToolbar(R.drawable.no_icon, getString(R.string.main_activity_title));
        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list = findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        noBanksText = findViewById(R.id.noBanksText);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((view) -> {
            Intent intent = new Intent(this, ChooseBankActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Shows list of banks <p>
     * Called after {@link MainPresenter#getChosenBanks()}
     *
     * @param banks from presenter
     */
    @Override
    public void showChosenBanks(List<ChosenBank> banks) {
        list.setAdapter(new ChosenBanksListAdapter(this, banks)
                .setOnItemClickListener((bank) -> {
                    Intent intent = new Intent(this, BankRatesActivity.class);
                    intent.putExtra(BankRatesActivity.EXTRA_BANK_INDEX, bank.ordinal());
                    startActivity(intent);
                }));
        noBanksText.setVisibility(View.GONE);
    }

    /**
     * Shows message that there are no banks <p>
     * Called after {@link MainPresenter#getChosenBanks()}
     */
    @Override
    public void showNoBanksMessage() {
        list.setAdapter(new ChosenBanksListAdapter(this, new ArrayList<>()));
        noBanksText.setVisibility(View.VISIBLE);
    }


}

