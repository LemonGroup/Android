package com.slack.geekbrainswork.ai.presenter;

import android.text.TextUtils;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.view.activities.LoginView;

public class LoginActivityPresenter extends BasePresenter{

    private LoginView view;

    public LoginActivityPresenter(LoginView view) {
        this.view = view;
    }

    public void attemptLogin(String login, String password) {
        boolean cancel = false;

        view.resetErrors();

        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            view.showPasswordError();
            cancel = true;
        }

        if (TextUtils.isEmpty(login) || !isLoginValid(login)) {
            view.showLoginError();
            cancel = true;
        }

        if (!cancel) {
            view.showProgress(true);
            authorize();
        }
    }

    private void authorize() {

    }

    private boolean isLoginValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
