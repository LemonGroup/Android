package com.slack.geekbrainswork.ai.presenter;


import com.slack.geekbrainswork.ai.data.KeywordRepository;
import com.slack.geekbrainswork.ai.data.KeywordRepositoryImpl;
import com.slack.geekbrainswork.ai.data.PersonRepository;
import com.slack.geekbrainswork.ai.data.PersonRepositoryImpl;
import com.slack.geekbrainswork.ai.data.Repository;
import com.slack.geekbrainswork.ai.data.RepositoryImpl;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter {

    protected Repository repository = new RepositoryImpl();
    protected PersonRepository personRepository = new PersonRepositoryImpl();
    protected KeywordRepository keywordRepository = new KeywordRepositoryImpl();

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }
}