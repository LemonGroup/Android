package com.slack.geekbrainswork.ai.presenter.mappers;

import com.slack.geekbrainswork.ai.model.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;


public class SitesDtoToSitesMapper implements Func1<List<SiteDTO>, List<Site>>{

    @Override
    public List<Site> call(List<SiteDTO> siteDTOs) {
        return Observable.from(siteDTOs)
                .map(new SiteDtoToSiteMapper())
                .toList()
                .toBlocking()
                .first();
    }
}
