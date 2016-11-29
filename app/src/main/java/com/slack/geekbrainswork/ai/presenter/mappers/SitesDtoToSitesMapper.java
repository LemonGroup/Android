package com.slack.geekbrainswork.ai.presenter.mappers;

import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;


public class SitesDtoToSitesMapper implements Func1<List<SiteDTO>, List<Site>> {


    @Override
    public List<Site> call(List<SiteDTO> siteDTOs) {
        if (siteDTOs == null) {
            return new ArrayList<>();
        }
        return Observable.from(siteDTOs)
                .map(new SiteDtoToSiteMapper())
                .toList()
                .toBlocking()
                .first();

    }
}

