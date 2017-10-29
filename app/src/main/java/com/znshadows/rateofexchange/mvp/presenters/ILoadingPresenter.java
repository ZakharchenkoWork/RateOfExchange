package com.znshadows.rateofexchange.mvp.presenters;

import com.znshadows.rateofexchange.mvp.views.ILoadingView;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.rateofexchange.general.activities.loading.LoadingPresenter}
 *
 * @param <ViewType> Activity
 */
public interface ILoadingPresenter<ViewType extends ILoadingView> extends IBasePresenter<ViewType> {
    /**
     * Loads data from database
     * <p>
     * After finish calls {@link ILoadingView#onDataLoaded()}
     */
    void loadData();
}
