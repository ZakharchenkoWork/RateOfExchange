package com.znshadows.exchangerate.mvp.views;

import com.znshadows.exchangerate.general.models.ChosenBank;

import java.util.List;

/**
 * Created by Natali Zakharchenko on 17.05.2017.
 */

/**
 * Used in {@link com.znshadows.exchangerate.general.activities.main.MainActivity}
 */
public interface IMainView extends IBaseView {

    void showChosenBanks(List<ChosenBank> banks);

    void showNoBanksMessage();
}
