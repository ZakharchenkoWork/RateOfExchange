package com.znshadows.exchangerate.general.activities.widget_settings;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

import com.znshadows.exchangerate.App;
import com.znshadows.exchangerate.R;
import com.znshadows.exchangerate.general.activities.BaseActivity;
import com.znshadows.exchangerate.general.models.BANKS;
import com.znshadows.exchangerate.general.models.UnifiedBankResponse;
import com.znshadows.exchangerate.general.models.WidgetInfo;
import com.znshadows.exchangerate.mvp.presenters.IWidgetSettingsPresenter;
import com.znshadows.exchangerate.mvp.views.IWidgetSettingsView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Natali Zakharchenko on 22.06.2017.
 */

public class WidgetSettingsActivity extends BaseActivity implements IWidgetSettingsView {

    @Inject
    IWidgetSettingsPresenter presenter;

    private RecyclerView banksList;
    private RecyclerView currenciesList;
    private EditText bankName;
    private EditText currencyName;
    private LinearLayout currencyHolder;
    private Button save;

    private int widgetId;
    private BANKS chosenBank;
    private String chosenCurrency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_settings);
        prepareResultIntent(false);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            widgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        save = findViewById(R.id.save);
        save.setVisibility(View.GONE);

        bankName = findViewById(R.id.bankName);
        bankName.setKeyListener(null);

        banksList = findViewById(R.id.banksList);
        banksList.setLayoutManager(new LinearLayoutManager(this));
        banksList.setVisibility(View.GONE);

        currencyName = findViewById(R.id.curencyName);
        currencyName.setKeyListener(null);
        currencyHolder = findViewById(R.id.currencyHolder);

        currenciesList = findViewById(R.id.currenciesList);
        currenciesList.setLayoutManager(new LinearLayoutManager(this));
        currenciesList.setVisibility(View.GONE);

        currencyHolder.setVisibility(View.GONE);

        AllBanksListAdapter adapter = new AllBanksListAdapter(this, Arrays.asList(BANKS.values()));

        banksList.setAdapter(adapter);
        adapter.setOnBankChosenListener(bank -> {
            banksList.setVisibility(View.GONE);
            bankName.setText(getResources().getStringArray(R.array.bankNames)[bank.ordinal()]);
            currencyName.setText("");
            currencyHolder.setVisibility(View.VISIBLE);
            chosenBank = bank;

        });

        bankName.setOnClickListener(new BankClickListener());
        bankName.setOnFocusChangeListener(new BankClickListener());
        currencyName.setOnClickListener(new CurrencyClickListener());
        currencyName.setOnFocusChangeListener(new CurrencyClickListener());

        save.setOnClickListener((v) -> {
            presenter.saveWidgetInfo(new WidgetInfo(widgetId, chosenBank, chosenCurrency));
            updateWidget();
            prepareResultIntent(true);
            finish();
        });
    }

    public void showBanksViews() {
        banksList.setVisibility(View.VISIBLE);
        currencyHolder.setVisibility(View.GONE);
        save.setVisibility(View.GONE);
        currenciesList.setVisibility(View.GONE);
    }

    private void showCurrenciesViews() {
        save.setVisibility(View.GONE);
        presenter.getCurrenciesForBank(chosenBank);
    }

    @Override
    public void showCurrenciesList(List<UnifiedBankResponse> dataList) {
        currenciesList.setVisibility(View.VISIBLE);
        CurrenciesListAdapter adapter = new CurrenciesListAdapter(this, dataList);
        currenciesList.setAdapter(adapter);

        adapter.setOnCurrencyChoosenListener((currency) -> {
            this.chosenCurrency = currency;
            currenciesList.setVisibility(View.GONE);
            save.setVisibility(View.VISIBLE);
            currencyName.setText(currency);
        });
    }

    class BankClickListener implements View.OnClickListener, View.OnFocusChangeListener {

        @Override
        public void onClick(View view) {
            showBanksViews();
        }

        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (hasFocus) {
                showBanksViews();
            }
        }
    }


    class CurrencyClickListener implements View.OnClickListener, View.OnFocusChangeListener {

        @Override
        public void onClick(View view) {
            showCurrenciesViews();
        }

        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (hasFocus) {
                showCurrenciesViews();
            }
        }
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
        presenter.setView(this);
    }

    private void prepareResultIntent(boolean isOk) {
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        if (isOk) {
            setResult(RESULT_OK, resultValue);
        } else {
            setResult(RESULT_CANCELED, resultValue);
        }
    }

    private void updateWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_layout);
        appWidgetManager.updateAppWidget(widgetId, views);
    }

}
