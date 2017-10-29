package com.znshadows.rateofexchange.mvp.views;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.rateofexchange.general.activities.loading.LoadingActivity}
 */
public interface ILoadingView extends IBaseView {
    /**
     * Moves to MainActivity
     */
    void onDataLoaded();
}
