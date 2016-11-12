package com.slack.geekbrainswork.ai.view.activities;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.view.MainActivityCallback;
import com.slack.geekbrainswork.ai.view.fragments.CatalogsFragment;
import com.slack.geekbrainswork.ai.view.fragments.SitelistFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityCallback {

    private static String TAG = "TAG";

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) replaceFragment(new CatalogsFragment(), false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_catalogs:
                startCatalogsFragment();
                return true;
            case R.id.action_users:
                startUsersFragment();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    public void startCatalogsFragment() {
        replaceFragment(new CatalogsFragment(), true);
    }

    @Override
    public void startSitesCatalogFragment() {
        replaceFragment(new SitelistFragment(), true);
    }

    public void startUsersFragment() {
        //ToDo updating
        //replaceFragment(UsersFragment, true);
        Snackbar.make(coordinatorLayout,"Функционал не реализован",Snackbar.LENGTH_LONG).show();
    }
}
