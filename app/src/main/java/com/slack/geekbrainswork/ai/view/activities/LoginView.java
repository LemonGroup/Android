package com.slack.geekbrainswork.ai.view.activities;

import android.annotation.TargetApi;
import android.os.Build;

import com.slack.geekbrainswork.ai.view.View;

public interface LoginView {
    void showPasswordError();

    void showLoginError();

    void showProgress(boolean show);

    void resetErrors();

    void showAuthorizationError(String message);

    void navigateToMainView();
}
