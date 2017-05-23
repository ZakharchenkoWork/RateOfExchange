package com.znshadows.rateofexchange.general.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.menu.MenuFragment;

/**
 * Created by kostya on 17.05.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public abstract void resolveDaggerDependencies();

    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resolveDaggerDependencies();
        setContentView(R.layout.place_holder);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_holder_layout);
        RelativeLayout content = (RelativeLayout) findViewById(R.id.main_content);
        content.addView(getLayoutInflater().inflate(layoutResID, null));
        setDrawerState(false);

    }



    /**
     * call this method from onCreate if activity needs a menu inside side drawer.
     * make shure to call setupSideDrawer first
     */
    protected void showMenuFragment() {
        MenuFragment fragment = new MenuFragment();
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
        /*
        if (hasRefreshButton) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        //*/
            return super.onCreateOptionsMenu(menu);


    }

    /**
     * Starts progress bar
     */
    public void onStartLoading() {

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadingProgress);
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * stops progress bar
     */
    public void onFinishLoading() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loadingProgress);
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }


    //TODO:ADD Coment
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        toolbar.setNavigationIcon(navigationDrawableId);
        toolbar.setVisibility(View.VISIBLE);
        return toolbar;
    }

    public Toolbar getToolbarLayout() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    /**
     * call this method from onCreate if activity needs a side drawer
     * make shure to call setupToolbar first
     */
    protected void setupSideDrawer() {
        setDrawerState(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            toolbar.setNavigationOnClickListener((v) -> {
                drawer.openDrawer(Gravity.LEFT);
            });

        }

    }



    /**
     * Called by presenters, when there is an error
     */
    public void onError() {
    //    new OneButtonDialog(this, getString(R.string.connection_error_title), getString(R.string.connection_error_message), OneButtonDialog.NO_ICON, null);
        //Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show();
    }

    /**
     * used for enabling and disabling side drawer, disabled by default
     *
     * @param isEnabled
     */
    private void setDrawerState(boolean isEnabled) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
     * click listener for toolbars left button
     *
     * @param onClickListener
     */
    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    private OnOptionItemSelected onOptionItemSelected = new OnOptionItemSelected() {
        @Override
        public boolean onClick(MenuItem item) {
            return false;
        }
    };



    public interface OnOptionItemSelected {
        boolean onClick(MenuItem item);
    }

}


