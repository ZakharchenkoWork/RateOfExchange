package com.hast.exchangerate.mvp.presenters;

import androidx.annotation.NonNull;

import com.hast.exchangerate.general.models.BANKS;
import com.hast.exchangerate.mvp.views.IChooseBankView;

import java.util.List;

/**
 * Created by Konstantyn Zakharchenko on 17.05.2017.
 */

/**
 * {@link com.hast.exchangerate.general.activities.choose_bank.ChooseBankPresenter}
 *
 * @param <ViewType> Activity
 */
public interface IChooseBankPresenter<ViewType extends IChooseBankView> extends IBasePresenter<ViewType> {

    /**
     * Retrievs data about old banks from database
     *
     * @return list of banks from Database
     */
    @NonNull
    List<BANKS> getChosenBanks();

    /**
     * Saves list of banks in database.
     * <p>
     * If bank is already exists, it's data will not be lost.
     *
     * @param banks list of banks to save
     */
    void saveChosenBanks(List<BANKS> banks);
}
