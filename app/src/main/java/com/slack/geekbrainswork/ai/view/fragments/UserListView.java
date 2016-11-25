package com.slack.geekbrainswork.ai.view.fragments;

import com.slack.geekbrainswork.ai.presenter.vo.User;
import com.slack.geekbrainswork.ai.view.View;

import java.util.List;

public interface UserListView extends View{
    void showUserList(List<User> userList);


    void navigateToUpdateUserView(User user);

    void showRemoveUserDialog(User user);

    void navigateToAddUserView();
}
