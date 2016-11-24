package com.slack.geekbrainswork.ai.presenter;

import com.slack.geekbrainswork.ai.view.activities.MainActivity;
import com.slack.geekbrainswork.ai.view.activities.MainView;

public class MainPresenter extends BasePresenter {

    private MainView view;

    public MainPresenter(MainView mainView) {
        this.view = mainView;
    }

    public void onActionLogOutClick() {
        view.navigateToLoginView();
    }
}
