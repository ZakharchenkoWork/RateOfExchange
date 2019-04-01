package com.znshadows.exchangerate.mvp.views;

/**
 * Created by Natali Zakharchenko on 17.05.2017.
 */

public interface IBaseView {
    void onError();

    void onStartLoading();

    void onFinishLoading();
}
