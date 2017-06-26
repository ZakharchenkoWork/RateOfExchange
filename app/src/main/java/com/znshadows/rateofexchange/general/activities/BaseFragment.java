package com.znshadows.rateofexchange.general.activities;

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
 * Created by Evolution on 2/27/17.
 */

public abstract class BaseFragment extends Fragment {
    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;

    protected abstract void resolveDaggerDependencies();

    protected abstract int contentView();

    protected abstract View onCreateView(View root);

    protected View root;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        if (root == null) {
            root = inflater.inflate(contentView(), null);
        }
        resolveDaggerDependencies();

        return onCreateView(root);
    }


    protected BaseActivity getBaseActivity() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            return ((BaseActivity) getActivity());
        } else {
            return null;
        }
    }
}

