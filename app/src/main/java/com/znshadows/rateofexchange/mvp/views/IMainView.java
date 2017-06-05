package com.znshadows.rateofexchange.mvp.views;

import com.znshadows.rateofexchange.general.models.BANKS;

import java.util.List;

/**
 * Created by kostya on 17.05.2017.
 */

public interface IMainView extends IBaseView{
    void showChoosenBanks(List<BANKS> banks);
}
