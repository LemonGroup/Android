package com.slack.geekbrainswork.ai.presenter;


import com.slack.geekbrainswork.ai.data.Repository;
import com.slack.geekbrainswork.ai.data.RepositoryDemo;
import com.slack.geekbrainswork.ai.data.RepositoryImpl;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter {

    //ToDo replace to RepositoryImpl
    protected Repository repository = new RepositoryImpl();;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
