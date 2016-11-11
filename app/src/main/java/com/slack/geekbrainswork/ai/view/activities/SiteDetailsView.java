package com.slack.geekbrainswork.ai.view.activities;


import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.view.fragments.View;

public interface SiteDetailsView extends View {

    String getSiteNameTextViewText();

    void onClose(Site site);
}
