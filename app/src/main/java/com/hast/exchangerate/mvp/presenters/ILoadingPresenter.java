package com.hast.exchangerate.mvp.presenters;

import com.hast.exchangerate.mvp.views.ILoadingView;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.hast.exchangerate.general.activities.loading.LoadingPresenter}
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
