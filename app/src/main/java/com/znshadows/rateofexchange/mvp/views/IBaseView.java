package com.znshadows.rateofexchange.mvp.views;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

public interface IBaseView {
    void onError();

    void onStartLoading();

    void onFinishLoading();
}
