package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.presenter.mappers.UserListMapper;
import com.slack.geekbrainswork.ai.presenter.vo.User;
import com.slack.geekbrainswork.ai.view.fragments.UserListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;

public class UserListPresenter extends BasePresenter {
    private static final String BUNDLE_USER_LIST_KEY = "BUNDLE_USER_LIST_KEY";

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

    public void onClickUser(User user) {
        view.navigateToUpdateUserView(user);
    }

    public void onAddButtonClick() {
        view.navigateToAddUserView();
    }

    public void onLongClickUser(User user) {
        view.showRemoveUserDialog(user);
    }

    public void onClickRemoveButton(User user) {
        Subscription subscription = repository.deleteUser(user.getId())
                .flatMap(new Func1<Void, Observable<List<UserDTO>>>() {
                    @Override
                    public Observable<List<UserDTO>> call(Void aVoid) {
                        return repository.getUsers();
                    }
                })
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

    public void onActivityResult() {
        loadData();
    }

    private boolean isSiteListEmpty() {
        return userList == null || userList.isEmpty();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isSiteListEmpty()) {
            outState.putSerializable(BUNDLE_USER_LIST_KEY, new ArrayList<>(userList));
        }
    }
}
