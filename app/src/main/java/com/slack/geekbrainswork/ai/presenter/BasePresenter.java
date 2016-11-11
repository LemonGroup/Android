package com.slack.geekbrainswork.ai.presenter;


import com.slack.geekbrainswork.ai.model.Model;
import com.slack.geekbrainswork.ai.model.ModelImpl;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter {

    protected Model data = new ModelImpl();;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
