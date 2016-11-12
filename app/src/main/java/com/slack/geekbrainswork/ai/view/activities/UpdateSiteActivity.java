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
import com.slack.geekbrainswork.ai.presenter.UpdateSitePresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateSiteActivity extends SiteActivity {

    private UpdateSitePresenter presenter = new UpdateSitePresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        ButterKnife.bind(this);

        Site site = getSiteVo();

        setTitle(getTitleFromString());
        siteNameTextView.setText(site.getName());

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

        presenter.onCreate(savedInstanceState, site);
    }

    @NonNull
    protected String getTitleFromString() {
        return getResources().getString(R.string.catalog_edit_title);
    }

    private Site getSiteVo() {
        return (Site) getIntent().getSerializableExtra("site");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}
