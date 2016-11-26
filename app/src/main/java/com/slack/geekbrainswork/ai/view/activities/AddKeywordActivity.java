package com.slack.geekbrainswork.ai.view.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.AddKeywordPresenter;

import butterknife.ButterKnife;

/**
 * Created by Prilepishev Vadim  on 26.11.2016.
 */

public class AddKeywordActivity  extends KeywordActivity {

    private AddKeywordPresenter presenter = new AddKeywordPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword);
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
    @Override
    protected String getTitleFromString() {
        return getResources().getString(R.string.catalog_add_title);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}
