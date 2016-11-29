package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.presenter.mappers.UserMapper;
import com.slack.geekbrainswork.ai.presenter.vo.User;
import com.slack.geekbrainswork.ai.view.activities.UserView;

import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;

public class UserAddPresenter extends BasePresenter {

    private static String BUNDLE_USER_KEY = "BUNDLE_USER_KEY";

    protected User user;
    protected UserView view;
    protected UserMapper mapper = new UserMapper();

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
        Integer privilege = view.getIsAdminValue() ? 2 : 1;

        if (!isValidData(login, email, pass1, pass2)) {
            return;
        }

        tryCreateUser(login, email, pass1, privilege);
    }

    protected void tryCreateUser(final String login, final String email, final String pass, final Integer privilege) {
        view.showProgressBar(true);

        Subscription loginExistSubscription = repository.checkLogin(login)
                .flatMap(new Func1<Response<Void>, Observable<Response<Void>>>() {
                    @Override
                    public Observable<Response<Void>> call(Response<Void> response) {
                        if (response.code() == 409) {
                            return Observable.error(new Exception("Пользователь с таким логином уже существует"));
                        }
                        return repository.checkEmail(email);
                    }
                })
                .flatMap(new Func1<Response<Void>, Observable<UserDTO>>() {
                    @Override
                    public Observable<UserDTO> call(Response<Void> response) {
                        if (response.code() == 409) {
                            return Observable.error(new Exception("Пользователь с таким e-mail уже существует"));
                        }
                        return repository.createUser(new UserDTO(login, email, pass, privilege));
                    }
                })
                .map(mapper)
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showProgressBar(false);
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        view.showProgressBar(false);
                        view.onClose();
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
        if (user != null) {
            outState.putSerializable(BUNDLE_USER_KEY, user);
        }
    }
}
