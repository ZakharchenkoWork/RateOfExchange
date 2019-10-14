package com.hast.exchangerate.general.activities.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.hast.exchangerate.App;
import com.hast.exchangerate.auxilary.Constants;
import com.hast.exchangerate.general.activities.BaseActivity;
import com.hast.exchangerate.general.activities.choose_bank.ChooseBankActivity;
import com.hast.exchangerate.general.activities.rate_list.BankRatesActivity;
import com.hast.exchangerate.general.models.ChosenBank;
import com.hast.exchangerate.mvp.presenters.IMainPresenter;
import com.hast.exchangerate.mvp.views.IMainView;
import com.hast.rateofexchange.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */


public class MainActivity extends BaseActivity implements IMainView {

    public static final String ADMOB_SETTINGS = "admob settings";
    public static final String APP_OPENED_COUNT = "app opened";

    private InterstitialAd mInterstitialAd;
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

        list = findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        noBanksText = findViewById(R.id.noBanksText);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((view) -> {
            Intent intent = new Intent(this, ChooseBankActivity.class);
            startActivity(intent);
        });

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial_id));
        if (isTimeToShowInterstitial()) {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }
    }

    private boolean isTimeToShowInterstitial() {
        SharedPreferences sharedPreferences = getSharedPreferences(ADMOB_SETTINGS, MODE_PRIVATE);
        int openedCount = sharedPreferences.getInt(APP_OPENED_COUNT, 0);

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(APP_OPENED_COUNT, ++openedCount);
        edit.apply();
        return openedCount > Constants.ADS_FREE_OPENINIGS;
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

