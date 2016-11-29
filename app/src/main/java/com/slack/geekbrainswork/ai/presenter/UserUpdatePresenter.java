package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.presenter.mappers.UserMapper;
import com.slack.geekbrainswork.ai.presenter.vo.User;
import com.slack.geekbrainswork.ai.view.activities.UserView;

import retrofit2.Response;
import rx.Observer;
import rx.Subscription;

public class UserUpdatePresenter extends BasePresenter {

    private static String BUNDLE_USER_KEY = "BUNDLE_USER_KEY";

    private User user;
    private UserView view;
    private UserMapper mapper = new UserMapper();

    public UserUpdatePresenter(UserView userDetailsView) {
        view = userDetailsView;
    }

    public void onCreate(Bundle savedInstanceState, User user) {
        if (user != null) {
            this.user = user;
        }

        if (savedInstanceState != null) {
            this.user = (User) savedInstanceState.getSerializable(BUNDLE_USER_KEY);
        }
    }

    public void onSaveButtonClick() {
        String pass1 = view.getPasswordTextViewText();
        String pass2 = view.getPassword2TextViewText();

        if (!isValidPassword(pass1, pass2)) {
            return;
        }

        updateUser(pass1);
    }

    private void updateUser(String pass1) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setPassword(pass1);

        view.showProgressBar(true);

        Subscription subscription = repository.changePassword(userDTO)
                .subscribe(new Observer<Response<Void>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showProgressBar(false);
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Response<Void> response) {
                        view.showProgressBar(false);
                        view.onClose();
                    }
                });

        addSubscription(subscription);
    }

    private boolean isValidPassword(String pass1, String pass2) {
        if (pass1 == null || pass1.isEmpty()) {
            view.showError("Пароль не должен быть пустым");
            return false;
        }

        if (!pass1.equals(pass2)) {
            view.showError("Пароли должны совпадать");
            return false;
        }
        return true;
    }

    public void onSaveInstanceState(Bundle outState) {
        if (user != null) {
            outState.putSerializable(BUNDLE_USER_KEY, user);
        }
    }

}
