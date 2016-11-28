package com.slack.geekbrainswork.ai.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.UserAddPresenter;
import com.slack.geekbrainswork.ai.presenter.UserUpdatePresenter;
import com.slack.geekbrainswork.ai.presenter.vo.User;

import butterknife.ButterKnife;

public class UserAddActivity extends UserActivity{

    protected UserAddPresenter presenter = new UserAddPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setTitle(getResources().getString(R.string.user_add_title));

        isAdminSwitch.setEnabled(true);
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

   @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}
