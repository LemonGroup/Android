package com.slack.geekbrainswork.ai.presenter;

import android.os.Bundle;

import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.mappers.SiteDtoToSiteMapper;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.view.activities.SiteView;

import rx.Observer;
import rx.Subscription;

public class UpdateSitePresenter extends BasePresenter {
    private static String BUNDLE_SITE_KEY = "BUNDLE_SITE_KEY";

    private Site site;
    private SiteView view;
    private SiteDtoToSiteMapper mapper = new SiteDtoToSiteMapper();

    public UpdateSitePresenter(SiteView view) {
        this.view = view;
    }

    public void onCreate(Bundle savedInstanceState, Site site) {
        if (site != null) {
            this.site = site;
        }

        if (savedInstanceState != null) {
            this.site = (Site) savedInstanceState.getSerializable(BUNDLE_SITE_KEY);
        }
    }

    public void onSaveButtonClick() {
        String siteName = view.getSiteNameTextViewText();
        if (siteName == null || siteName.isEmpty()) {
            view.showError("Название сайта не должно быть пустым");
            return;
        }

        site.setName(siteName);
        SiteDTO siteDTO = new SiteDTO(site.getId(), siteName);
        Subscription subscription = repository.updateSite(siteDTO)
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

    public void onSaveInstanceState(Bundle outState) {
        if (site != null) {
            outState.putSerializable(BUNDLE_SITE_KEY, site);
        }
    }
}
