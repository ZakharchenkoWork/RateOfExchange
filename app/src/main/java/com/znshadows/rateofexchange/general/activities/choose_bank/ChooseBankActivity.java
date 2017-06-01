package com.znshadows.rateofexchange.general.activities.choose_bank;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.activities.BaseActivity;
import com.znshadows.rateofexchange.general.activities.main.ChoosenBanksListAdapter;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.presenters.IChoosseBankPresenter;
import com.znshadows.rateofexchange.mvp.views.IChooseBankView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by kostya on 30.05.2017.
 */

public class ChooseBankActivity extends BaseActivity implements IChooseBankView {
    @Inject
    IChoosseBankPresenter presenter;

    RecyclerView list;
    private static OnOkListener onOkListener;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_bank);
        setupToolbar(R.drawable.nav_drawer_icon, getString(R.string.choose_bank_activity_title));
        setMenuResourse(R.menu.ok_menu);

        List<BANKS> choosenBanksList = presenter.getChoosenBanks();
        list = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        ChooseBanksListAdapter chooseBanksListAdapter = new ChooseBanksListAdapter(this, Arrays.asList(BANKS.values()), choosenBanksList);
        list.setAdapter(chooseBanksListAdapter);

        setOnOptionItemSelected((v) -> {

            onOkListener.onOkClicked(chooseBanksListAdapter.getCheckedList());
            finish();
            return true;
        });
    }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
        presenter.setView(this);
    }

    public static void setOnOkListener(OnOkListener onOkListener) {
        ChooseBankActivity.onOkListener = onOkListener;
    }

    public interface OnOkListener{
    void onOkClicked(List<BANKS> choosenBanksList);
}
}
