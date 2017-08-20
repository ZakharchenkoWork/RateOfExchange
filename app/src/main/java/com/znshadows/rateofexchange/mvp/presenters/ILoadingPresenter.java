package com.znshadows.rateofexchange.mvp.presenters;

import com.znshadows.rateofexchange.mvp.views.IBaseView;
import com.znshadows.rateofexchange.mvp.views.ILoadingView;
import com.znshadows.rateofexchange.mvp.views.IWidgetSettingsView;

import java.util.List;

/**
 * Created by kostya on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.rateofexchange.general.activities.loading.LoadingPresenter}
 * @param <ViewType> Activity
 */
public interface ILoadingPresenter<ViewType extends ILoadingView> extends IBasePreseter<ViewType> {
    /**
     * Loads data from database
     * <p>
     * After finish calls {@link ILoadingView#onDataLoaded()}
     */
    void loadData();
}
