package com.znshadows.rateofexchange.mvp.presenters;

import com.znshadows.rateofexchange.mvp.views.IBaseView;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

public interface IBasePresenter<ViewType extends IBaseView> {
    void setView(ViewType view);

    void resolveDaggerDependencies();
}
