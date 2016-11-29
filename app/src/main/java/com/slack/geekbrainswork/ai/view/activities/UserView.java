package com.slack.geekbrainswork.ai.view.activities;

import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.presenter.vo.User;
import com.slack.geekbrainswork.ai.view.View;

public interface UserView extends View {
    String getUserNameTextViewText();

    String getPasswordTextViewText();

    String getPassword2TextViewText();

    String getEmailTextViewText();

    boolean getIsAdminValue();

//    void onClose(User user);

    void onClose();
}
