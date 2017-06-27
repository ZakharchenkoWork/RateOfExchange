package com.znshadows.rateofexchange.mvp.views;

import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.general.models.WidgetInfo;

import java.util.List;

/**
 * Created by kostya on 17.05.2017.
 */

public interface IWidgetView extends IBaseView{



    void showResponce(WidgetInfo widgetInfo, UnifiedBankResponce bankResponse);
}
