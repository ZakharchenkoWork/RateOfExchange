package com.znshadows.exchangerate.mvp.models;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Natali Zakharchenko on 7/5/17.
 */

public class AsyncTransformer<Type> implements ObservableTransformer<Type, Type> {
    @Override
    public ObservableSource<Type> apply(Observable<Type> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
