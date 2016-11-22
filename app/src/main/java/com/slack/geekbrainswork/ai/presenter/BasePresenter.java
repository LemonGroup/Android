package com.slack.geekbrainswork.ai.presenter;


import com.slack.geekbrainswork.ai.data.PersonRepository;
import com.slack.geekbrainswork.ai.data.Repository;
import com.slack.geekbrainswork.ai.data.RepositoryDemo;
import com.slack.geekbrainswork.ai.data.RepositoryImpl;
import com.slack.geekbrainswork.ai.data.RepositoryPersonDemo;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter {

    //ToDo replace to RepositoryImpl
    protected Repository repository = new RepositoryDemo();
    protected PersonRepository personRepository = new RepositoryPersonDemo();

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}
