package com.slack.geekbrainswork.ai.view.fragments;

import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.view.View;

import java.util.List;

public interface SiteListView extends View {
    void navigateToUpdateSiteView(Site site);

    void showSiteList(List<Site> siteList);

    void navigateToAddSiteView();
}
