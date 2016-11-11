package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.view.fragments.SitesCatalogFragment;
import com.slack.geekbrainswork.ai.view.fragments.SitesCatalogView;

public class SitesCatalogPresenter extends BasePresenter {

    private SitesCatalogView view;

    public SitesCatalogPresenter(SitesCatalogView sitesCatalogView) {
        view = sitesCatalogView;
    }

    public void onCreate(Bundle savedInstanceState) {

    }

    public void clickSite(Site site) {
        view.navigateToSiteDetails(site);
    }
}
