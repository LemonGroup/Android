package com.slack.geekbrainswork.ai.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.view.fragments.AddingElementFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "TAG";

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
        if (fragment == null) replaceFragment(new AddingElementFragment(), false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_adding:
                startAddingElementFragment();
                return true;
            case R.id.action_updating:
                startUpdatingElementFragment();
                return true;
            case R.id.action_removing:
                startRemovingElementFragment();
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

    public void startAddingElementFragment() {
        //replaceFragment(AddingElementFragment, true);
    }

    public void startUpdatingElementFragment() {
        //ToDo updating
        //replaceFragment(UpdatingElementFragment, true);
    }

    public void startRemovingElementFragment() {
        //ToDo removing
        //replaceFragment(RemovingElementFragment, true);
    }
}
