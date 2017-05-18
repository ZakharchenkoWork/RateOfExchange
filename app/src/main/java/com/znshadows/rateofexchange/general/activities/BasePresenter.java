package com.znshadows.rateofexchange.general.activities;

import com.znshadows.rateofexchange.mvp.presenters.IBasePreseter;
import com.znshadows.rateofexchange.mvp.views.IBaseView;

/**
 * Created by kostya on 17.05.2017.
 */

public class BasePresenter<ViewType extends IBaseView> implements IBasePreseter<ViewType>{
    private ViewType view;
   public void setView(ViewType view){
        this.view = view;
    }
    protected ViewType getView(){
        return view;
    }

}
