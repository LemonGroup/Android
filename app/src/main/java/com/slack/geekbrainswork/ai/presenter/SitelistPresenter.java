package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.presenter.mappers.SitesDtoToSitesMapper;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.view.fragments.SitelistView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;

public class SitelistPresenter extends BasePresenter {
    private static String BUNDLE_SITE_LIST_KEY = "BUNDLE_SITE_LIST_KEY";

    private SitelistView view;
    private List<Site> siteList;
    private SitesDtoToSitesMapper mapper = new SitesDtoToSitesMapper();

    public SitelistPresenter(SitelistView sitelistView) {
        view = sitelistView;
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
                .map(mapper)
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
        view.navigateToUpdateSiteView(site);
    }

    public void onAddButtonClick() {
        view.navigateToAddSiteView();
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isSiteListEmpty()) {
            outState.putSerializable(BUNDLE_SITE_LIST_KEY, new ArrayList<>(siteList));
        }
    }

    private boolean isSiteListEmpty() {
        return siteList == null || siteList.isEmpty();
    }

    public void onActivityResult() {
        loadData();
    }

}
