package com.znshadows.exchangerate.mvp.views;

import com.znshadows.exchangerate.general.models.UnifiedBankResponse;
import com.znshadows.exchangerate.general.models.WidgetInfo;

/**
 * Created by Natali Zakharchenko on 17.05.2017.
 */

public interface IWidgetView extends IBaseView{



    void showResponse(WidgetInfo widgetInfo, UnifiedBankResponse bankResponse);
}
