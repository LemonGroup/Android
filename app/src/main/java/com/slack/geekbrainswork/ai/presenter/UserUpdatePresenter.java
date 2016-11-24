package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.presenter.mappers.SiteDtoToSiteMapper;
import com.slack.geekbrainswork.ai.presenter.mappers.UserMapper;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.presenter.vo.User;
import com.slack.geekbrainswork.ai.view.activities.SiteView;
import com.slack.geekbrainswork.ai.view.activities.UserDetailsActivity;
import com.slack.geekbrainswork.ai.view.activities.UserDetailsView;
import com.slack.geekbrainswork.ai.view.fragments.BaseFragment;

import rx.Observer;
import rx.Subscription;

public class UserUpdatePresenter extends BasePresenter {

    private static String BUNDLE_USER_KEY = "BUNDLE_USER_KEY";

    private User user;
    private UserDetailsView view;
    private UserMapper mapper = new UserMapper();

    public UserUpdatePresenter(UserDetailsView userDetailsView) {
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
        String login = view.getUserNameTextViewText();
        String email = view.getEmailTextViewText();
        String pass1 = view.getPasswordTextViewText();
        String pass2 = view.getPassword2TextViewText();

        if (!isValidate(login, pass1, pass2, email)) {
            return;
        }

        user.setName(login);

        Subscription subscription = repository.updateUser(user.getId(), pass1)
                .map(mapper)
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        view.onClose(user);
                    }
                });

        addSubscription(subscription);
    }

    private boolean isValidate(String login, String pass1, String pass2, String email) {
        if (login == null || login.isEmpty()) {
            view.showError("Логин не должен быть пустым");
            return false;
        }

        if (email == null || email.isEmpty()) {
            view.showError("Email не должен быть пустым");
            return false;
        }

        if (pass1 == null) {
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
