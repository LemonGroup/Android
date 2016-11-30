package com.slack.geekbrainswork.ai.view.activities;

import com.slack.geekbrainswork.ai.view.View;

public interface SendPasswordView extends View{
    String getEmailTextViewText();

    void showProgressBar(boolean show);

    void showEmail(String email);

    void close();
}
