package com.hast.exchangerate.di.modules;


import com.hast.exchangerate.general.activities.choose_bank.ChooseBankPresenter;
import com.hast.exchangerate.general.activities.loading.LoadingPresenter;
import com.hast.exchangerate.general.activities.main.MainPresenter;
import com.hast.exchangerate.general.activities.rate_list.BankRatesPresenter;
import com.hast.exchangerate.general.activities.widget_settings.WidgetSettingsPresenter;
import com.hast.exchangerate.general.widget.WidgetPresenter;
import com.hast.exchangerate.mvp.presenters.IBankRatesPresenter;
import com.hast.exchangerate.mvp.presenters.IChooseBankPresenter;
import com.hast.exchangerate.mvp.presenters.ILoadingPresenter;
import com.hast.exchangerate.mvp.presenters.IMainPresenter;
import com.hast.exchangerate.mvp.presenters.IWidgetPresenter;
import com.hast.exchangerate.mvp.presenters.IWidgetSettingsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Konstantyn Zakharchenko on 2/27/17.
 */

@Module
public class PresentersModule {

    @Singleton
    @Provides
    ILoadingPresenter provideLoadingPresenter() {
        return new LoadingPresenter();
    }

    @Singleton
    @Provides
    IMainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Singleton
    @Provides
    IChooseBankPresenter provideChooseBankPresenter() {
        return new ChooseBankPresenter();
    }

    @Singleton
    @Provides
    IBankRatesPresenter provideBankRatesPresenter() {
        return new BankRatesPresenter();
    }

    @Singleton
    @Provides
    IWidgetSettingsPresenter provideWidgetSettingsPresenter() {
        return new WidgetSettingsPresenter();
    }

    @Singleton
    @Provides
    IWidgetPresenter provideWidgetPresenter() {
        return new WidgetPresenter();
    }
}
