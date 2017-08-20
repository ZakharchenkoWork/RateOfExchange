package com.znshadows.rateofexchange.mvp.presenters;

import android.support.annotation.NonNull;

import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.mvp.views.IBaseView;
import com.znshadows.rateofexchange.mvp.views.IChooseBankView;
import com.znshadows.rateofexchange.mvp.views.IMainView;

import java.util.List;

/**
 * Created by kostya on 17.05.2017.
 */

/**
 * {@link com.znshadows.rateofexchange.general.activities.choose_bank.ChoosseBankPresenter}
 * @param <ViewType> Activity
 */
public interface IChoosseBankPresenter<ViewType extends IChooseBankView> extends IBasePreseter<ViewType> {

    /**
     * Retrievs data about old banks from database
     * @return list of banks from Database
     */
    @NonNull List<BANKS> getChoosenBanks();

    /**
     * Saves list of banks in database.
     * <p>
     * If bank is already exists, it's data will not be lost.
     * @param banks list of banks to save
     */
    void saveChoosenBanks(List<BANKS> banks);
}
