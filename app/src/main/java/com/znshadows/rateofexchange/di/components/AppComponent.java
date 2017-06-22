package com.znshadows.rateofexchange.di.components;


import com.znshadows.rateofexchange.di.modules.ModelModule;
import com.znshadows.rateofexchange.di.modules.PresentersModule;
import com.znshadows.rateofexchange.general.activities.choose_bank.ChooseBankActivity;
import com.znshadows.rateofexchange.general.activities.choose_bank.ChoosseBankPresenter;
import com.znshadows.rateofexchange.general.activities.loading.LoadingActivity;
import com.znshadows.rateofexchange.general.activities.main.MainActivity;
import com.znshadows.rateofexchange.general.activities.main.MainPresenter;
import com.znshadows.rateofexchange.general.activities.rate_list.BankRatesActivity;
import com.znshadows.rateofexchange.general.activities.rate_list.BankRatesPresenter;
import com.znshadows.rateofexchange.general.activities.rate_list.RateListAdapter;
import com.znshadows.rateofexchange.general.widget.WidgetPresenter;
import com.znshadows.rateofexchange.general.widget.WidgetProvider;
import com.znshadows.rateofexchange.mvp.models.UnifiedModel;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Evolution on 2/27/17.
 */

@Singleton
@Component(modules = {ModelModule.class, PresentersModule.class})
public interface AppComponent {

    void inject(LoadingActivity loadingActivity);
    void inject(MainActivity mainActivity);

    void inject(MainPresenter mainPresenter);

    void inject(UnifiedModel unifiedModel);

    void inject(ChooseBankActivity chooseBankActivity);

    void inject(BankRatesActivity bankRatesActivity);

    void inject(BankRatesPresenter bankRatesPresenter);

    void inject(WidgetProvider widgetProvider);

    void inject(WidgetPresenter widgetPresenter);


    void inject(RateListAdapter rateListAdapter);

    void inject(ChoosseBankPresenter choosseBankPresenter);
}
