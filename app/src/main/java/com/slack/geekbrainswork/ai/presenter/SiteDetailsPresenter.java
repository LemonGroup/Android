package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.presenter.mappers.SiteDtoToSiteMapper;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.view.activities.SiteDetailsView;

import rx.Observer;
import rx.Subscription;

public class SiteDetailsPresenter extends BasePresenter {
    private static String BUNDLE_SITE_KEY = "BUNDLE_SITE_KEY";

    private Site site;
    private SiteDetailsView view;
    private SiteDtoToSiteMapper mapper = new SiteDtoToSiteMapper();

    public SiteDetailsPresenter(SiteDetailsView view) {
        this.view = view;
    }

    public void onCreate(Bundle savedInstanceState, Site site) {
        if (site != null){
            this.site = site;
        }

        if (savedInstanceState != null) {
            this.site = (Site) savedInstanceState.getSerializable(BUNDLE_SITE_KEY);
        }
    }

    public void onSaveButtonClick() {
        site.setName(view.getSiteNameTextViewText());

        Subscription subscription = data.updateSite(site)
                .map(mapper)
                .subscribe(new Observer<Site>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Site site) {
                        view.onClose(site);
                    }
                });

        addSubscription(subscription);
    }
}
