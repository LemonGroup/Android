package com.slack.geekbrainswork.ai.view.fragments;

import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

public interface SitesCatalogView extends View {
    void navigateToSiteDetails(Site site);

    void showSiteList(List<Site> siteList);
}
