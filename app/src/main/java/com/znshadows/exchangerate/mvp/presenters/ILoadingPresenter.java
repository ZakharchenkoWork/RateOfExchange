package com.znshadows.exchangerate.mvp.presenters;

import com.znshadows.exchangerate.mvp.views.ILoadingView;

/**
 * Created by Natali Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.exchangerate.general.activities.loading.LoadingPresenter}
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
