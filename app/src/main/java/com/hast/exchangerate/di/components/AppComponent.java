package com.hast.exchangerate.di.components;


import com.hast.exchangerate.App;
import com.hast.exchangerate.di.modules.ModelModule;
import com.hast.exchangerate.di.modules.PresentersModule;
import com.hast.exchangerate.general.activities.choose_bank.ChooseBankActivity;
import com.hast.exchangerate.general.activities.choose_bank.ChooseBankPresenter;
import com.hast.exchangerate.general.activities.loading.LoadingActivity;
import com.hast.exchangerate.general.activities.loading.LoadingPresenter;
import com.hast.exchangerate.general.activities.main.ChosenBanksListAdapter;
import com.hast.exchangerate.general.activities.main.MainActivity;
import com.hast.exchangerate.general.activities.main.MainPresenter;
import com.hast.exchangerate.general.activities.rate_list.BankRatesActivity;
import com.hast.exchangerate.general.activities.rate_list.BankRatesPresenter;
import com.hast.exchangerate.general.activities.rate_list.RateListAdapter;
import com.hast.exchangerate.general.activities.widget_settings.WidgetSettingsActivity;
import com.hast.exchangerate.general.activities.widget_settings.WidgetSettingsPresenter;
import com.hast.exchangerate.general.models.UserData;
import com.hast.exchangerate.general.widget.WidgetPresenter;
import com.hast.exchangerate.general.widget.WidgetProvider;
import com.hast.exchangerate.mvp.models.UnifiedModel;

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
