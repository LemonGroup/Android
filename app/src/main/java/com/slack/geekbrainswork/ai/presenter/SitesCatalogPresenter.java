package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.presenter.mappers.SitesMapper;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.view.fragments.SitesCatalogView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;

public class SitesCatalogPresenter extends BasePresenter {
    private static String BUNDLE_SITE_LIST_KEY = "BUNDLE_SITE_LIST_KEY";

    private SitesCatalogView view;
    private List<Site> siteList;
    private SitesMapper sitesMapper = new SitesMapper();

    public SitesCatalogPresenter(SitesCatalogView sitesCatalogView) {
        view = sitesCatalogView;
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            siteList = (List<Site>) savedInstanceState.getSerializable(BUNDLE_SITE_LIST_KEY);
        }

        if (!isSiteListEmpty()) {
            view.showSiteList(siteList);
        } else {
            loadData();
        }
    }

    private void loadData() {
        Subscription subscription = data.getSites()
                .map(sitesMapper)
                .subscribe(new Observer<List<Site>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Site> sites) {
                        view.showSiteList(sites);
                    }
                });

        addSubscription(subscription);
    }

    public void clickSite(Site site) {
        view.navigateToSiteDetails(site);
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isSiteListEmpty()) {
            outState.putSerializable(BUNDLE_SITE_LIST_KEY, new ArrayList<>(siteList));
        }
    }

    private boolean isSiteListEmpty() {
        return siteList == null || siteList.isEmpty();
    }
}
