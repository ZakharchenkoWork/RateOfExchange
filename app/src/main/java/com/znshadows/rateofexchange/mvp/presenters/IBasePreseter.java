package com.znshadows.rateofexchange.mvp.presenters;

import com.znshadows.rateofexchange.mvp.views.IBaseView;

/**
 * Created by kostya on 17.05.2017.
 */

public interface IBasePreseter<ViewType extends IBaseView> {
    void setView(ViewType view);
}
