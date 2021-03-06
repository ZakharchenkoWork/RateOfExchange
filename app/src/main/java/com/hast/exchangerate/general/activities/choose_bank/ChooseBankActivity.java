package com.hast.exchangerate.general.activities.choose_bank;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hast.exchangerate.App;
import com.hast.rateofexchange.R;
import com.hast.exchangerate.general.activities.BaseActivity;
import com.hast.exchangerate.general.models.BANKS;
import com.hast.exchangerate.mvp.presenters.IChooseBankPresenter;
import com.hast.exchangerate.mvp.views.IChooseBankView;

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
