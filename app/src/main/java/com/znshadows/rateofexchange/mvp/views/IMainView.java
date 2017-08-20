package com.znshadows.rateofexchange.mvp.views;

import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.ChoosenBank;

import java.util.List;

/**
 * Created by kostya on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.rateofexchange.general.activities.main.MainActivity}
 */
public interface IMainView extends IBaseView{

    void showChoosenBanks(List<ChoosenBank> banks);

    void showNoBanksMessage();
}
