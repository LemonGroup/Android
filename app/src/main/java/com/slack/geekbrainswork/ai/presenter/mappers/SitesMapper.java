package com.slack.geekbrainswork.ai.presenter.mappers;

import com.slack.geekbrainswork.ai.model.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;


public class SitesMapper implements Func1<List<SiteDTO>, List<Site>> {

    @Override
    public List<Site> call(List<SiteDTO> siteDTOs) {
        return Observable.from(siteDTOs)
                .map(new Func1<SiteDTO, Site>() {
                    @Override
                    public Site call(SiteDTO siteDTO) {
                        return new Site(siteDTO.getId(), siteDTO.getName());
                    }
                })
                .toList()
                .toBlocking()
                .first();
    }
}
