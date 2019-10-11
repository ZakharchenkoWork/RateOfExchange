package com.hast.exchangerate.mvp.views;

import com.hast.exchangerate.general.models.UnifiedBankResponse;
import com.hast.exchangerate.general.models.WidgetInfo;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

public interface IWidgetView extends IBaseView{



    void showResponse(WidgetInfo widgetInfo, UnifiedBankResponse bankResponse);
}
