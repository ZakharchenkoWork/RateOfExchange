package com.hast.exchangerate.mvp.views;

import com.hast.exchangerate.general.models.ChosenBank;

import java.util.List;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.hast.exchangerate.general.activities.main.MainActivity}
 */
public interface IMainView extends IBaseView {

    void showChosenBanks(List<ChosenBank> banks);

    void showNoBanksMessage();
}
