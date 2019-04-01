package com.znshadows.exchangerate.di.components;


import com.znshadows.exchangerate.App;
import com.znshadows.exchangerate.di.modules.ModelModule;
import com.znshadows.exchangerate.di.modules.PresentersModule;
import com.znshadows.exchangerate.general.activities.choose_bank.ChooseBankActivity;
import com.znshadows.exchangerate.general.activities.choose_bank.ChooseBankPresenter;
import com.znshadows.exchangerate.general.activities.loading.LoadingActivity;
import com.znshadows.exchangerate.general.activities.loading.LoadingPresenter;
import com.znshadows.exchangerate.general.activities.main.ChosenBanksListAdapter;
import com.znshadows.exchangerate.general.activities.main.MainActivity;
import com.znshadows.exchangerate.general.activities.main.MainPresenter;
import com.znshadows.exchangerate.general.activities.rate_list.BankRatesActivity;
import com.znshadows.exchangerate.general.activities.rate_list.BankRatesPresenter;
import com.znshadows.exchangerate.general.activities.rate_list.RateListAdapter;
import com.znshadows.exchangerate.general.activities.widget_settings.WidgetSettingsActivity;
import com.znshadows.exchangerate.general.activities.widget_settings.WidgetSettingsPresenter;
import com.znshadows.exchangerate.general.models.UserData;
import com.znshadows.exchangerate.general.widget.WidgetPresenter;
import com.znshadows.exchangerate.general.widget.WidgetProvider;
import com.znshadows.exchangerate.mvp.models.UnifiedModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Natali Zakharchenko on 2/27/17.
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
