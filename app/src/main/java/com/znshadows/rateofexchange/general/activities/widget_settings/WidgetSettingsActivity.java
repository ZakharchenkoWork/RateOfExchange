package com.znshadows.rateofexchange.general.activities.widget_settings;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.activities.BaseActivity;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.views.IWidgetSettingsView;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by kostya on 22.06.2017.
 */

public class WidgetSettingsActivity extends BaseActivity implements IWidgetSettingsView {
    private RecyclerView banksList;
    private RecyclerView currenciesList;
    private EditText curencyName;
    private LinearLayout currencyHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_settings);
        //LinearLayout banksHolder = (LinearLayout) findViewById(R.id.banksHolder);
        EditText bankName = (EditText) findViewById(R.id.bankName);
        curencyName = (EditText) findViewById(R.id.curencyName);
        currencyHolder = (LinearLayout)findViewById(R.id.currencyHolder);

        banksList = (RecyclerView) findViewById(R.id.banksList);
        currenciesList = (RecyclerView) findViewById(R.id.currenciesList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        banksList.setLayoutManager(layoutManager);
        banksList.setVisibility(View.GONE);
currencyHolder.setVisibility(View.GONE);
        AllBanksListAdapter adapter = new AllBanksListAdapter(this, Arrays.asList(BANKS.values()));

        banksList.setAdapter(adapter);
        adapter.setOnBankChoosenListener(bank -> {
            banksList.setVisibility(View.GONE);
            bankName.setText(getResources().getStringArray(R.array.bankNames)[bank.ordinal()]);
            currencyHolder.setVisibility(View.VISIBLE);
        });

        bankName.setOnClickListener(new BankClickListener());
        bankName.setOnFocusChangeListener(new BankClickListener());
        curencyName.setOnClickListener((v)->{
            showCurrenciesList();
        });
    }

    public void showBanksList() {
        banksList.setVisibility(View.VISIBLE);
        currencyHolder.setVisibility(View.GONE);
    }
    public void showCurrenciesList() {
        currenciesList.setVisibility(View.VISIBLE);
    }

    class BankClickListener implements View.OnClickListener, View.OnFocusChangeListener {

        @Override
        public void onClick(View view) {
            showBanksList();
        }

        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (hasFocus) {
                showBanksList();
            }
        }
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }
}
