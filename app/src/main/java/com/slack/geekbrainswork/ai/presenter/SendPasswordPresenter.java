package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.presenter.vo.User;
import com.slack.geekbrainswork.ai.view.activities.SendPasswordActivity;
import com.slack.geekbrainswork.ai.view.activities.SendPasswordView;

import java.util.ArrayList;

import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.Subscription;

public class SendPasswordPresenter extends BasePresenter {
    private static final String BUNDLE_EMAIL_KEY = "BUNDLE_EMAIL_KEY";

    private SendPasswordView view;
    private String email;

    public SendPasswordPresenter(SendPasswordView sendPasswordView) {
        view = sendPasswordView;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            email = savedInstanceState.getString(BUNDLE_EMAIL_KEY);
        }

        if (email != null) {
            view.showEmail(email);
        }
    }

    public void onSendButtonClick() {
        email = view.getEmailTextViewText();

        if (TextUtils.isEmpty(email)) {
            view.showError("E-mail не должен быть пустым");
            return;
        }

        trySendPassword(email);
    }

    private void trySendPassword(String email) {
        view.showProgressBar(true);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);

        Subscription subscription = repository.resetPassword(userDTO)
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
                        handleResponse(response);
                    }
                });
        addSubscription(subscription);
    }

    private void handleResponse(Response<Void> response) {
        String message = null;

        if (response.isSuccessful()){
            view.close();
            return;
        }

        if (response.code() == 500) {
            message = "Пользователь с таким e-mail не существует";
        } else {
            message = response.message();
        }

        view.showError(message);
    }

    public void onSaveInstanceState(Bundle outState) {
        if (email != null) {
            outState.putString(BUNDLE_EMAIL_KEY, email);
        }
    }
}