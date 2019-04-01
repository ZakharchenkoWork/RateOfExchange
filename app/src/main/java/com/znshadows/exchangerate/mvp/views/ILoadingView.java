package com.znshadows.exchangerate.mvp.views;

/**
 * Created by Natali Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.exchangerate.general.activities.loading.LoadingActivity}
 */
public interface ILoadingView extends IBaseView {
    /**
     * Moves to MainActivity
     */
    void onDataLoaded();
}
