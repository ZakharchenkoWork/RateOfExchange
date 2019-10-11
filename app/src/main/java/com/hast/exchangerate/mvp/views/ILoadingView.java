package com.hast.exchangerate.mvp.views;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.hast.exchangerate.general.activities.loading.LoadingActivity}
 */
public interface ILoadingView extends IBaseView {
    /**
     * Moves to MainActivity
     */
    void onDataLoaded();
}
