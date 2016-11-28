package com.slack.geekbrainswork.ai.presenter;

import android.text.TextUtils;

import com.slack.geekbrainswork.ai.data.Repository;
import com.slack.geekbrainswork.ai.data.RepositoryDemo;
import com.slack.geekbrainswork.ai.data.RepositoryImpl;
import com.slack.geekbrainswork.ai.presenter.mappers.TokenMapper;
import com.slack.geekbrainswork.ai.view.activities.LoginView;

import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;

public class LoginActivityPresenter extends BasePresenter {

    protected Repository repository = new RepositoryImpl();

    private LoginView view;
    private TokenMapper tokenMapper = new TokenMapper();

    public LoginActivityPresenter(LoginView view) {
        this.view = view;
    }

    public void attemptLoginByToken() {
//        String token = repository.getTokenFromStorage();
//        if (token != null && !token.isEmpty()){
//            view.navigateToMainView();
//        }
    }

    public void onSignInButtonClick(String login, String password) {
        boolean cancel = false;

        view.resetErrors();

        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            view.showPasswordError();
            cancel = true;
        }

        if (TextUtils.isEmpty(login) || !isLoginValid(login)) {
            view.showLoginError();
            cancel = true;
        }

        if (!cancel) {
            view.showProgress(true);
            authorizeByLoginAndPassword(login, password);
        }
    }

    private void authorizeByLoginAndPassword(String login, String password) {
        Subscription subscription = repository.auth(login, password)
                .map(tokenMapper)
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        repository.updateToken(s);
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showProgress(false);
                        view.showAuthorizationError(e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        view.navigateToMainView();
                    }
                });

        addSubscription(subscription);
    }

    private boolean isLoginValid(String login) {
        //TODO: Replace this with your own logic
        return login.contains("");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return true;
//        return password.length() > 4;
    }


    public void onRegButtonClick() {
        view.navigateToRegistrationUserView();
    }
}
