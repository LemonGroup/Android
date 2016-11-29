package com.slack.geekbrainswork.ai.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.slack.geekbrainswork.ai.presenter.UserRegisterPresenter;

public class RegistrationUserActivity extends UserAddActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = new UserRegisterPresenter(this);
        super.onCreate(savedInstanceState);
        isAdminSwitch.setVisibility(View.INVISIBLE);
    }
}
