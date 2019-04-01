package com.znshadows.exchangerate.general.activities.rate_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.znshadows.exchangerate.App;
import com.znshadows.exchangerate.R;
import com.znshadows.exchangerate.general.activities.BaseActivity;
import com.znshadows.exchangerate.general.models.BANKS;
import com.znshadows.exchangerate.general.models.UnifiedBankResponse;
import com.znshadows.exchangerate.mvp.presenters.IBankRatesPresenter;
import com.znshadows.exchangerate.mvp.views.IBankRatesView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Natali Zakharchenko on 01.06.2017.
 */

public class BankRatesActivity extends BaseActivity implements IBankRatesView {
    public static final String EXTRA_BANK_INDEX = "bank index";
    @Inject
    IBankRatesPresenter presenter;
    private RecyclerView list;
    private int bankIndex = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!getIntent().hasExtra(EXTRA_BANK_INDEX) || (bankIndex = getIntent().getIntExtra(EXTRA_BANK_INDEX, -1)) == -1) {
            finish();
        }
        setContentView(R.layout.activity_choose_bank);

        setupToolbar(R.drawable.btn_header_back_normal, getResources().getStringArray(R.array.bankNames)[bankIndex]);
        setNavigationOnClickListener(v -> finish());

        list = findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        presenter.getBankRates(BANKS.values()[bankIndex]);
    }

    /**
     * Called from {@link BankRatesPresenter#getBankRates(BANKS)}
     *
     * @param nbuResponse response from NBU server
     */
    @Override
    public void showResponse(List<UnifiedBankResponse> nbuResponse) {
        list.setAdapter(new RateListAdapter(this, BANKS.values()[bankIndex], nbuResponse));
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
        presenter.setView(this);
    }
}
