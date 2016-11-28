package com.slack.geekbrainswork.ai.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.UserUpdatePresenter;
import com.slack.geekbrainswork.ai.presenter.vo.User;

import butterknife.ButterKnife;

public class UserUpdateActivity extends UserActivity {

    private UserUpdatePresenter presenter = new UserUpdatePresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        User user = getUserVo();

        setTitle(user.getName());
        loginEditText.setText(user.getName());
        emailEditText.setText(user.getEmail());

        isAdminSwitch.setEnabled(user.getPrivilege() == 2);

        loginEditText.setEnabled(false);
        emailEditText.setEnabled(false);

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

        presenter.onCreate(savedInstanceState, user);
    }

    private User getUserVo() {
        return (User) getIntent().getSerializableExtra("user");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

}
