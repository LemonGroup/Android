package com.slack.geekbrainswork.ai.view.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.AddSitePresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddSiteActvity extends SiteActivity{

    private AddSitePresenter presenter = new AddSitePresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        ButterKnife.bind(this);

        setTitle(getTitleFromString());

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSaveButtonClick();
            }
        });

        openKeyboard();
        presenter.onCreate(savedInstanceState);
    }

    @NonNull
    protected String getTitleFromString() {
        return getResources().getString(R.string.catalog_add_title);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}
