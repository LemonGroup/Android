package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.data.Repository;
import com.slack.geekbrainswork.ai.data.RepositoryDemo;
import com.slack.geekbrainswork.ai.data.RepositoryImpl;
import com.slack.geekbrainswork.ai.presenter.mappers.UserListMapper;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.presenter.vo.User;
import com.slack.geekbrainswork.ai.view.fragments.UserListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;

public class UserListPresenter extends BasePresenter {
    private static final String BUNDLE_USER_LIST_KEY = "BUNDLE_USER_LIST_KEY";

    protected Repository repository = new RepositoryDemo();

    private UserListView view;
    private List<User> userList;

    private UserListMapper usersMapper = new UserListMapper();

    public UserListPresenter(UserListView userListView) {
        this.view = userListView;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            userList = (List<User>) savedInstanceState.getSerializable(BUNDLE_USER_LIST_KEY);
        }

        if (!isSiteListEmpty()) {
            view.showUserList(userList);
        } else {
            loadData();
        }
    }

    private void loadData() {
        Subscription subscription = repository.getUsers()
                .map(usersMapper)
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<User> users) {
                        view.showUserList(users);
                    }
                });

        addSubscription(subscription);
    }

    public void onLongClickUser(User user) {

    }

    public void onClickUser(User user) {

    }

    public void onAddButtonClick() {
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isSiteListEmpty()) {
            outState.putSerializable(BUNDLE_USER_LIST_KEY, new ArrayList<>(userList));
        }
    }

    private boolean isSiteListEmpty() {
        return userList == null || userList.isEmpty();
    }
}
