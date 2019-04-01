package com.znshadows.exchangerate.general.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Natali Zakharchenko on 2/27/17.
 */

public abstract class BaseFragment extends Fragment {
    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;
    protected View root;
    /**
     * Provide any DI related code here, it will be called before {@link BaseFragment#onCreateView(View)}.
     */
    protected abstract void resolveDaggerDependencies();

    /**
     * Use to set layout resource to be used as base of Fragment, it will be called before {@link BaseFragment#onCreateView(View)}.
     * @return
     */
    protected abstract @LayoutRes int contentView();

    /**
     * Called to do initial creation of a fragment.
     * @param root view with result of {@link BaseFragment#contentView()}
     * @return same root view that was in param.
     */
    protected abstract View onCreateView(View root);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        resolveDaggerDependencies();
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;

        if (root == null) {
            root = inflater.inflate(contentView(), null);
        }
        return onCreateView(root);
    }

    /**
     * use to get acces to BaseActivity instance that produced this fragment
     * @return
     */
    protected BaseActivity getBaseActivity() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            return ((BaseActivity) getActivity());
        } else {
            return null;
        }
    }
}

