package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.data.dto.KeywordDTO;
import com.slack.geekbrainswork.ai.presenter.mappers.KeywordsDtoToKeywordsMapper;
import com.slack.geekbrainswork.ai.presenter.vo.Keyword;
import com.slack.geekbrainswork.ai.view.fragments.KeywordListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public class KeywordListPresenter extends BasePresenter {

    private static String BUNDLE_KEYWORD_LIST_KEY = "BUNDLE_KEYWORD_LIST_KEY";

    private KeywordListView keywordView;
    private List<Keyword> keywordList;
    private KeywordsDtoToKeywordsMapper keywordListMapper = new KeywordsDtoToKeywordsMapper();

    public KeywordListPresenter(KeywordListView keywordListView) {
        keywordView = keywordListView;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            keywordList = (List<Keyword>) savedInstanceState.getSerializable(BUNDLE_KEYWORD_LIST_KEY);
        }

        if (!isKeywordListEmpty()) {
            keywordView.showKeywordList(keywordList);
        } else {
            loadData();
        }
    }

    private void loadData() {
        Subscription subscription = keywordRepository.getKeywords()
                .map(keywordListMapper)
                .subscribe(new Observer<List<Keyword>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        keywordView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Keyword> keywords) {
                        keywordView.showKeywordList(keywords);
                    }
                });

        addSubscription(subscription);
    }

    public void onClickSite(Keyword keyword) {
        keywordView.navigateToUpdateKeywordView(keyword);
    }

    public void onAddButtonClick() {
        keywordView.navigateToAddKeywordView();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isKeywordListEmpty()) {
            outState.putSerializable(BUNDLE_KEYWORD_LIST_KEY, new ArrayList<>(keywordList));
        }
    }

    private boolean isKeywordListEmpty() {
        return keywordList == null || keywordList.isEmpty();
    }

    public void onActivityResult() {
        loadData();
    }

    public void onLongClickSite(Keyword keyword) {
        keywordView.showRemoveKeywordDialog(keyword);
    }

    public void onClickRemoveButton(Keyword keyword) {
        Subscription subscription = keywordRepository.removeKeyword(keyword)
                .flatMap(new Func1<Void, Observable<List<KeywordDTO>>>() {
                    @Override
                    public Observable<List<KeywordDTO>> call(Void aVoid) {
                        return keywordRepository.getKeywords();
                    }
                })
                .map(keywordListMapper)
                .subscribe(new Observer<List<Keyword>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Keyword> keywords) {
                        keywordView.showKeywordList(keywords);
                    }
                });

        addSubscription(subscription);
    }

}

