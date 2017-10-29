package com.znshadows.rateofexchange.mvp.views;

import com.znshadows.rateofexchange.general.models.ChosenBank;

import java.util.List;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.rateofexchange.general.activities.main.MainActivity}
 */
public interface IMainView extends IBaseView {

    void showChosenBanks(List<ChosenBank> banks);

    void showNoBanksMessage();
}
