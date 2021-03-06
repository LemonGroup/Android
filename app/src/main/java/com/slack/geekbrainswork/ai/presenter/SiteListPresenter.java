package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.mappers.SitesDtoToSitesMapper;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.view.fragments.SiteListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;

public class SiteListPresenter extends BasePresenter {
    private static String BUNDLE_SITE_LIST_KEY = "BUNDLE_SITE_LIST_KEY";

    private SiteListView view;
    private List<Site> siteList;
    private SitesDtoToSitesMapper siteListMapper = new SitesDtoToSitesMapper();

    public SiteListPresenter(SiteListView siteListView) {
        view = siteListView;
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
        Subscription subscription = repository.getSites()
                .map(siteListMapper)
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

    public void onClickSite(Site site) {
        view.navigateToUpdateSiteView(site);
    }

    public void onAddButtonClick() {
        view.navigateToAddSiteView();
    }

    private boolean isSiteListEmpty() {
        return siteList == null || siteList.isEmpty();
    }

    public void onActivityResult() {
        loadData();
    }

    public void onLongClickSite(Site site) {
        view.showRemoveSiteDialog(site);
    }

    public void onClickRemoveButton(Site site) {
        Subscription subscription = repository.deleteSite(site.getId())
                .flatMap(new Func1<Void, Observable<List<SiteDTO>>>() {
                    @Override
                    public Observable<List<SiteDTO>> call(Void aVoid) {
                        return repository.getSites();
                    }
                })
                .map(siteListMapper)
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

    public void onSaveInstanceState(Bundle outState) {
        if (!isSiteListEmpty()) {
            outState.putSerializable(BUNDLE_SITE_LIST_KEY, new ArrayList<>(siteList));
        }
    }
}
