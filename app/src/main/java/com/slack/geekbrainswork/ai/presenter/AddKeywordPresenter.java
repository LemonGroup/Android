package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.data.KeywordRepository;
import com.slack.geekbrainswork.ai.presenter.mappers.KeywordDtoToKeywordMapper;
import com.slack.geekbrainswork.ai.presenter.vo.Keyword;
import com.slack.geekbrainswork.ai.view.activities.KeywordView;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public class AddKeywordPresenter extends BasePresenter {
    private static String BUNDLE_KEYWORD_KEY = "BUNDLE_KEYWORD_KEY";

    private Keyword keyword;
    private KeywordView keywordView;
    private KeywordDtoToKeywordMapper mapper = new KeywordDtoToKeywordMapper();

    public AddKeywordPresenter(KeywordView view) {
        this.keywordView = view;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.keyword = (Keyword) savedInstanceState.getSerializable(BUNDLE_KEYWORD_KEY);
        }
    }

    public void onSaveButtonClick() {

        String keywordName = keywordView.getKeywordNameTextViewText();
        if (keywordName == null || keywordName.isEmpty()) {
            keywordView.showError("Слово не должно быть пустым");
            return;
        }

        Subscription subscription = keywordRepository.createKeyword(keywordName)
                .map(mapper)
                .subscribe(new Observer<Keyword>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        keywordView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Keyword keyword) {
                        keywordView.onClose(keyword);
                    }
                });

        addSubscription(subscription);
    }

    public void onSaveInstanceState(Bundle outState) {
        if (keyword != null) {
            outState.putSerializable(BUNDLE_KEYWORD_KEY, keyword);
        }
    }
}
