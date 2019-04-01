package com.znshadows.exchangerate.mvp.presenters;

import com.znshadows.exchangerate.mvp.views.IBaseView;

/**
 * Created by Natali Zakharchenko on 17.05.2017.
 */

public interface IBasePresenter<ViewType extends IBaseView> {
    void setView(ViewType view);

    void resolveDaggerDependencies();
}
