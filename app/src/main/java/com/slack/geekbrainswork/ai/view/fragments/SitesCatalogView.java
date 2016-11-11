package com.slack.geekbrainswork.ai.view.fragments;

import com.slack.geekbrainswork.ai.presenter.vo.Site;

public interface SitesCatalogView extends View {
    void navigateToSiteDetails(Site site);
}
