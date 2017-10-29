package com.znshadows.rateofexchange.di.components;


import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.di.modules.ModelModule;
import com.znshadows.rateofexchange.di.modules.PresentersModule;
import com.znshadows.rateofexchange.general.activities.choose_bank.ChooseBankActivity;
import com.znshadows.rateofexchange.general.activities.choose_bank.ChooseBankPresenter;
import com.znshadows.rateofexchange.general.activities.loading.LoadingActivity;
import com.znshadows.rateofexchange.general.activities.loading.LoadingPresenter;
import com.znshadows.rateofexchange.general.activities.main.ChosenBanksListAdapter;
import com.znshadows.rateofexchange.general.activities.main.MainActivity;
import com.znshadows.rateofexchange.general.activities.main.MainPresenter;
import com.znshadows.rateofexchange.general.activities.rate_list.BankRatesActivity;
import com.znshadows.rateofexchange.general.activities.rate_list.BankRatesPresenter;
import com.znshadows.rateofexchange.general.activities.rate_list.RateListAdapter;
import com.znshadows.rateofexchange.general.activities.widget_settings.WidgetSettingsActivity;
import com.znshadows.rateofexchange.general.activities.widget_settings.WidgetSettingsPresenter;
import com.znshadows.rateofexchange.general.models.UserData;
import com.znshadows.rateofexchange.general.widget.WidgetPresenter;
import com.znshadows.rateofexchange.general.widget.WidgetProvider;
import com.znshadows.rateofexchange.mvp.models.UnifiedModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Konstantyn Zakharchenko on 2/27/17.
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

    void inject(ChooseBankPresenter chooseBankPresenter);

    void inject(ChosenBanksListAdapter chosenBanksListAdapter);

    void inject(WidgetSettingsActivity widgetSettingsActivity);

    void inject(WidgetSettingsPresenter widgetSettingsPresenter);

    void inject(App app);

    void inject(LoadingPresenter loadingPresenter);

    void inject(UserData userData);


}
