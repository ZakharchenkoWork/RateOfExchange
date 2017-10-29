package com.znshadows.rateofexchange.general.activities.choose_bank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.activities.BaseActivity;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.presenters.IChooseBankPresenter;
import com.znshadows.rateofexchange.mvp.views.IChooseBankView;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Konstantyn Zakharchenko on 30.05.2017.
 */

public class ChooseBankActivity extends BaseActivity implements IChooseBankView {
    @Inject
    IChooseBankPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_bank);

        setupToolbar(R.drawable.btn_header_back_normal, getString(R.string.choose_bank_activity_title));
        setNavigationOnClickListener(view -> finish());

        setMenuResource(R.menu.ok_menu);

        List<BANKS> choosenBanksList = presenter.getChosenBanks();
        RecyclerView list = findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        ChooseBanksListAdapter chooseBanksListAdapter = new ChooseBanksListAdapter(this, Arrays.asList(BANKS.values()), choosenBanksList);
        list.setAdapter(chooseBanksListAdapter);

        setOnOptionItemSelected((v) -> {
            presenter.saveChosenBanks(chooseBanksListAdapter.getCheckedList());
            finish();
            return true;
        });
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
        presenter.setView(this);
    }

}
