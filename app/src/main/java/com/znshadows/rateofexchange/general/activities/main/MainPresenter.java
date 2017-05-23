package com.znshadows.rateofexchange.general.activities.main;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.general.activities.BasePresenter;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.general.models.nbu.NBUResponse;
import com.znshadows.rateofexchange.mvp.models.NBUApi;
import com.znshadows.rateofexchange.mvp.presenters.IMainPresenter;
import com.znshadows.rateofexchange.mvp.views.IMainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by kostya on 17.05.2017.
 */

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter<IMainView> {
    @Inject
    NBUApi model;



    public void getNBU(){
        model.getTodaysList()
                .map((List<NBUResponse> nbuResponses)->{
                        List<UnifiedBankResponce> unifiedBankResponce = new ArrayList<>();
                        for (NBUResponse nbuResponse : nbuResponses) {
                            unifiedBankResponce.add(new UnifiedBankResponce(nbuResponse.getName(),nbuResponse.getCode(),nbuResponse.getRate()));
                        }
                        return unifiedBankResponce;

                }).subscribe( getObservable(true, (bankResponse)->{getView().showResponce(bankResponse);} ));
        }

    @Override
    public void resolveDaggerDependencies() {
        App.getAppComponent().inject(this);
    }
}
