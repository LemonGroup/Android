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
import com.slack.geekbrainswork.ai.view.fragments.PersonListFragment;
import com.slack.geekbrainswork.ai.view.fragments.SiteListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityCallback {

    private static String TAG_CATALOGS = "TAG_CATALOGS";
    private static String TAG_SITE_CATALOG = "TAG_SITE_CATALOG";
    private static String TAG_USERS = "TAG_USERS";

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
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_CATALOGS);
        if (fragment == null) replaceFragment(new CatalogsFragment(), false, TAG_CATALOGS);
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

    private void replaceFragment(Fragment fragment, boolean addBackStack, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, tag);
        if (addBackStack && fragmentManager.findFragmentByTag(tag) == null) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    public void startCatalogsFragment() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        replaceFragment(new CatalogsFragment(), false, TAG_CATALOGS);
    }

    @Override
    public void startSitesCatalogFragment() {
        replaceFragment(new SiteListFragment(), true, TAG_SITE_CATALOG);
    }

    @Override
    public void startPersonsCatalogFragment() {
        replaceFragment(new PersonListFragment(), true);
    }

    public void startUsersFragment() {
        //ToDo updating
        //replaceFragment(UsersFragment, true);
        //fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Snackbar.make(coordinatorLayout, "Функционал не реализован", Snackbar.LENGTH_LONG).show();
    }
}
