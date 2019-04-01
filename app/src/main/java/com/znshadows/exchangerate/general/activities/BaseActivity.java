package com.znshadows.exchangerate.general.activities;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.znshadows.exchangerate.R;
import com.znshadows.exchangerate.auxilary.OneButtonDialog;
import com.znshadows.exchangerate.general.menu.MenuFragment;

/**
 * Created by Natali Zakharchenko on 17.05.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static final int NO_MENU = -1;

    @MenuRes
    private int menuResource = NO_MENU;
    private Menu menu;
    private OnOptionItemSelected onOptionItemSelected = item -> false;


    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resolveDaggerDependencies();
        setContentView(R.layout.place_holder);
    }

    /**
     * Use when you need to set a content view without loosing of basic app functionality as toolbar
     *
     * @param layoutResID
     */
    @CallSuper
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_holder_layout);
        RelativeLayout content = findViewById(R.id.main_content);
        content.addView(getLayoutInflater().inflate(layoutResID, content, false));
        setDrawerState(false);

    }

    /**
     * call this method from onCreate if activity needs a menu inside side drawer.
     * make sure to call setupSideDrawer first.
     *
     * @param fragment to be added.
     */
    protected void showMenuFragment(MenuFragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.nav_view, fragment).commitAllowingStateLoss();
    }

    /**
     * method which gets automatically from activity
     * for puting info button. could be prevented by calling disableInfoButton.
     * do not call manually.
     *
     * @param menu
     * @return
     */
    @Deprecated
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;

        if (menuResource != NO_MENU) {
            getMenuInflater().inflate(menuResource, menu);
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Starts progress bar
     */
    public void onStartLoading() {

        ProgressBar progressBar = findViewById(R.id.loadingProgress);
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Stops progress bar
     */
    public void onFinishLoading() {
        ProgressBar progressBar = findViewById(R.id.loadingProgress);
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    /**
     * @return menu object that was used by this activity
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * call this method from onCreate if activity needs a toolbar
     *
     * @param navigationDrawableId R.drawable id of left icon
     * @param title                for the toolbar
     * @return created toolbar
     */
    public Toolbar setupToolbar(int navigationDrawableId, String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        toolbar.setNavigationIcon(navigationDrawableId);
        toolbar.setVisibility(View.VISIBLE);
        return toolbar;
    }

    /**
     * Method to retrieve toolbar. That was added to activity automaticaly.
     *
     * @return Toolbar view
     */
    public Toolbar getToolbarLayout() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * call this method from onCreate if activity needs a side drawer
     * make shure to call setupToolbar first
     */
    protected void setupSideDrawer() {
        setDrawerState(true);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        if (toolbar != null) {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            toolbar.setNavigationOnClickListener((v) -> {
                drawer.openDrawer(Gravity.LEFT);
            });

        }

    }

    /**
     * Provide any DI related code here, it will be called with the constructor.
     */
    public abstract void resolveDaggerDependencies();

    /**
     * Called by presenters, when there is an error
     */
    public void onError() {
        new OneButtonDialog(this, OneButtonDialog.DIALOG_TYPE.MESSAGE_ONLY).setTitle("Oops.").setMessage("Sorry, there was an error, please try again later.").build();
        //Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
    }

    /**
     * used for enabling and disabling side drawer, disabled by default
     *
     * @param isEnabled
     */
    private void setDrawerState(boolean isEnabled) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (isEnabled) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    /**
     * redirects the owerrided method to click listener
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return onOptionItemSelected.onClick(item);
    }

    /**
     * Used in case we need some special menu resource, before onCreate. use NO_MENU if menu isn't needed
     *
     * @param menuResource
     */
    public void setMenuResource(int menuResource) {
        this.menuResource = menuResource;
    }

    /**
     * click listener for toolbars left button
     *
     * @param onClickListener
     */
    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(onClickListener);

    }

    /**
     * click listener for toolbars right button
     *
     * @param onOptionItemSelected
     */
    public void setOnOptionItemSelected(OnOptionItemSelected onOptionItemSelected) {
        this.onOptionItemSelected = onOptionItemSelected;
    }


    public interface OnOptionItemSelected {
        boolean onClick(MenuItem item);
    }


}


