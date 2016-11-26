package com.slack.geekbrainswork.ai.view.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.UpdateKeywordPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Keyword;

import butterknife.ButterKnife;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public class UpdateKeywordActivity extends KeywordActivity {

    private UpdateKeywordPresenter presenter = new UpdateKeywordPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword);
        ButterKnife.bind(this);

        Keyword keyword = getKeywordVo();

        setTitle(getTitleFromString());
        keywordNameTextView.setText(keyword.getKeyword());

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

        presenter.onCreate(savedInstanceState, keyword);
    }

    @NonNull
    protected String getTitleFromString() {
        return getResources().getString(R.string.catalog_edit_title);
    }

    private Keyword getKeywordVo() {
        return (Keyword) getIntent().getSerializableExtra("keyword");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

}
