package com.slack.geekbrainswork.ai.presenter;

import com.slack.geekbrainswork.ai.view.activities.MainView;

public class MainPresenter extends BasePresenter {

    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void auth() {
        view.showLoginView();
        //view.showContent();
    }
}
