package com.slack.geekbrainswork.ai.view.activities;

import com.slack.geekbrainswork.ai.presenter.vo.Keyword;
import com.slack.geekbrainswork.ai.view.View;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public interface KeywordView extends View {

    String getKeywordNameTextViewText();

    void onClose(Keyword keyword);
}
