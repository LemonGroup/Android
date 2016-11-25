package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.data.dto.IsBusyResponse;
import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.presenter.mappers.UserMapper;
import com.slack.geekbrainswork.ai.presenter.vo.User;
import com.slack.geekbrainswork.ai.view.activities.UserView;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;

public class UserAddPresenter extends BasePresenter {

    private static String BUNDLE_USER_KEY = "BUNDLE_USER_KEY";

    private User user;
    private UserView view;
    private UserMapper mapper = new UserMapper();

    public UserAddPresenter(UserView view) {
        this.view = view;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.user = (User) savedInstanceState.getSerializable(BUNDLE_USER_KEY);
        }
    }


    public void onSaveButtonClick() {
        String login = view.getUserNameTextViewText();
        String email = view.getEmailTextViewText();
        String pass1 = view.getPasswordTextViewText();
        String pass2 = view.getPassword2TextViewText();

        if (!isValidData(login, email, pass1, pass2)) {
            return;
        }

        createUser(login, email, pass1);
    }

    private void createUser(final String login, final String email, final String pass) {
        Subscription loginExistSubscription = repository.isLoginExists(login)
                .flatMap(new Func1<IsBusyResponse, Observable<IsBusyResponse>>() {
                    @Override
                    public Observable<IsBusyResponse> call(IsBusyResponse isBusyResponse) {
                        if (!isBusyResponse.getIsBusy()) {
                            return repository.isEmailExists(email);
                        }
                        return Observable.error(new Exception("Пользователь с таким логином уже существует"));
                    }
                })
                .flatMap(new Func1<IsBusyResponse, Observable<UserDTO>>() {
                    @Override
                    public Observable<UserDTO> call(IsBusyResponse isBusyResponse) {
                        if (!isBusyResponse.getIsBusy()) {
                            return repository.createUser(new UserDTO(login, email, pass));
                        }
                        return Observable.error(new Exception("Пользователь с таким e-mail уже существует"));
                    }
                })
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

        addSubscription(loginExistSubscription);
    }


    private boolean isValidData(String login, String email, String pass1, String pass2) {

        if (login == null || login.isEmpty()) {
            view.showError("Логин не должен быть пустым");
            return false;
        }

        if (email == null || email.isEmpty()) {
            view.showError("E-mail не должен быть пустым");
            return false;
        }

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

    }
}
