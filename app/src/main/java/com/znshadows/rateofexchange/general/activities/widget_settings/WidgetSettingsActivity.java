package com.znshadows.rateofexchange.general.activities.widget_settings;

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

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.activities.BaseActivity;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.general.models.WidgetInfo;
import com.znshadows.rateofexchange.mvp.presenters.IWidgetSettingsPresenter;
import com.znshadows.rateofexchange.mvp.views.IWidgetSettingsView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by kostya on 22.06.2017.
 */

public class WidgetSettingsActivity extends BaseActivity implements IWidgetSettingsView {

    @Inject
    IWidgetSettingsPresenter presenter;

    private RecyclerView banksList;
    private RecyclerView currenciesList;
    private EditText bankName;
    private EditText curencyName;
    private LinearLayout currencyHolder;
    private Button save;

    private int widgetId;
    private BANKS choosenBank;
    private String choosenCurrency;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_settings);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            widgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        save = (Button) findViewById(R.id.save);
        save.setVisibility(View.GONE);

        bankName = (EditText) findViewById(R.id.bankName);
        bankName.setKeyListener(null);

        banksList = (RecyclerView) findViewById(R.id.banksList);
        banksList.setLayoutManager( new LinearLayoutManager(this));
        banksList.setVisibility(View.GONE);

        curencyName = (EditText) findViewById(R.id.curencyName);
        curencyName.setKeyListener(null);
        currencyHolder = (LinearLayout) findViewById(R.id.currencyHolder);

        currenciesList = (RecyclerView) findViewById(R.id.currenciesList);
        currenciesList.setLayoutManager( new LinearLayoutManager(this));
        currenciesList.setVisibility(View.GONE);

        currencyHolder.setVisibility(View.GONE);

        AllBanksListAdapter adapter = new AllBanksListAdapter(this, Arrays.asList(BANKS.values()));

        banksList.setAdapter(adapter);
        adapter.setOnBankChoosenListener(bank -> {
            banksList.setVisibility(View.GONE);
            bankName.setText(getResources().getStringArray(R.array.bankNames)[bank.ordinal()]);
            curencyName.setText("");
            currencyHolder.setVisibility(View.VISIBLE);
            choosenBank = bank;

        });

        bankName.setOnClickListener(new BankClickListener());
        bankName.setOnFocusChangeListener(new BankClickListener());
        curencyName.setOnClickListener(new CurrencyClickListener());
        curencyName.setOnFocusChangeListener(new CurrencyClickListener());

        save.setOnClickListener((v)->{
            presenter.saveWidgetInfo(new WidgetInfo(widgetId, choosenBank, choosenCurrency));
            updateWidget();
            finish();
        });
    }

    public void showBanksViews() {
        banksList.setVisibility(View.VISIBLE);
        currencyHolder.setVisibility(View.GONE);
        save.setVisibility(View.GONE);
        currenciesList.setVisibility(View.GONE);
    }

    private void showCurenciesViews(){
        save.setVisibility(View.GONE);
        presenter.getCurrenciesForBank(choosenBank);
    }

    @Override
    public void showCurrenciesList(List<UnifiedBankResponce> dataList) {
        currenciesList.setVisibility(View.VISIBLE);
        CurrenciesListAdapter adapter = new CurrenciesListAdapter(this, dataList);
        currenciesList.setAdapter(adapter);

        adapter.setOnCurrencyChoosenListener((currency)->{
            this.choosenCurrency = currency;
            currenciesList.setVisibility(View.GONE);
            save.setVisibility(View.VISIBLE);
            curencyName.setText(currency);
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
            showCurenciesViews();
        }

        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (hasFocus) {
                showCurenciesViews();
            }
        }
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
        presenter.setView(this);
    }
    private void updateWidget(){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_layout);
        appWidgetManager.updateAppWidget(widgetId, views);
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        setResult(RESULT_OK, resultValue);
    }

}
