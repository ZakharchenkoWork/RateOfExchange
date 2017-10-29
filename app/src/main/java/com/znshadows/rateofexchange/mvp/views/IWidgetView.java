package com.znshadows.rateofexchange.mvp.views;

import com.znshadows.rateofexchange.general.models.UnifiedBankResponse;
import com.znshadows.rateofexchange.general.models.WidgetInfo;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

public interface IWidgetView extends IBaseView{



    void showResponse(WidgetInfo widgetInfo, UnifiedBankResponse bankResponse);
}
