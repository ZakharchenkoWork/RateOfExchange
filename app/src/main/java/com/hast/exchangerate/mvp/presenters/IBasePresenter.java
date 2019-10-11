package com.hast.exchangerate.mvp.presenters;

import com.hast.exchangerate.mvp.views.IBaseView;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

public interface IBasePresenter<ViewType extends IBaseView> {
    void setView(ViewType view);

    void resolveDaggerDependencies();
}
