package com.slack.geekbrainswork.ai.view.activities;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.MainPresenter;
import com.slack.geekbrainswork.ai.view.MainActivityCallback;
import com.slack.geekbrainswork.ai.view.fragments.CatalogsFragment;
import com.slack.geekbrainswork.ai.view.fragments.SiteListFragment;
import com.slack.geekbrainswork.ai.view.fragments.UserListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, MainActivityCallback {

    private static String TAG_CATALOGS = "TAG_CATALOGS";
    private static String TAG_SITE_CATALOG = "TAG_SITE_CATALOG";
    private static String TAG_USERS = "TAG_USERS";

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MainPresenter presenter = new MainPresenter(this);
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
                startUserListFragment();
                return true;
            case R.id.action_logout:
                presenter.onActionLogOutClick();
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

    public void startUserListFragment() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        replaceFragment(new UserListFragment(), false, TAG_USERS);
    }

    @Override
    public void navigateToLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(String error) {

    }
}
