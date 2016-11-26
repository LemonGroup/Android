package com.slack.geekbrainswork.ai.view.fragments;

import com.slack.geekbrainswork.ai.presenter.vo.Keyword;
import com.slack.geekbrainswork.ai.view.View;

import java.util.List;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public interface KeywordListView extends View {

    void navigateToUpdateKeywordView(Keyword keyword);

    void showKeywordList(List<Keyword> keywordList);

    void navigateToAddKeywordView();

    void showRemoveKeywordDialog(Keyword keyword);

}
