package com.slack.geekbrainswork.ai.view.activities;

import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.presenter.vo.User;
import com.slack.geekbrainswork.ai.view.View;

public interface UserDetailsView extends View {
    String getUserNameTextViewText();

    String getPasswordTextViewText();

    String getPassword2TextViewText();

    String getEmailTextViewText();

    void onClose(User user);
}
