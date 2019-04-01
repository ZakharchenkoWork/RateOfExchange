package com.znshadows.exchangerate.general.activities;

import android.util.Log;

import com.znshadows.exchangerate.mvp.presenters.IBasePresenter;
import com.znshadows.exchangerate.mvp.views.IBaseView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Natali Zakharchenko on 17.05.2017.
 */

public abstract class BasePresenter<ViewType extends IBaseView> implements IBasePresenter<ViewType> {
    private ViewType view;
    protected List<Disposable> disposables = new ArrayList<>();

    public void setView(ViewType view) {
        this.view = view;
    }

    protected ViewType getView() {
        return view;
    }

    public BasePresenter() {
        resolveDaggerDependencies();
    }

    /**
     * Provide any DI related code here, it will be called with the constructor.
     */
    public abstract void resolveDaggerDependencies();


    /**
     * Use if you need a lifecicle safe Observable, with loading options.
     * @param isShowLoading flag to show standard loadin progress bar
     * @param action callback when data recieved
     * @param <Type> of the elemen that was recieved.
     * @return
     */
    protected <Type> Observer<Type> getObserver(boolean isShowLoading, OnNext<Type> action) {
        return new Observer<Type>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);
                if (isShowLoading) {
                    getView().onStartLoading();
                }
            }

            @Override
            public void onNext(Type t) {
                action.onNext(t);
                if (isShowLoading) {
                    getView().onFinishLoading();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isShowLoading) {
                    onCallError(e.getMessage());
                } else {
                    Log.e("Presenter", e.getMessage());
                }
            }

            @Override
            public void onComplete() {
                Log.v("Presenter", "completed");
            }
        };
    }

    public void onCallError(String message) {
        Log.e("Presenter", message);
        view.onError();
    }

    protected interface OnNext<Type> {
        void onNext(Type data);
    }
}
