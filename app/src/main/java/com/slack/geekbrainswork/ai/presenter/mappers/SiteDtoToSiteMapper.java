package com.slack.geekbrainswork.ai.presenter.mappers;


import com.slack.geekbrainswork.ai.model.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import rx.functions.Func1;

public class SiteDtoToSiteMapper implements Func1 <SiteDTO, Site> {
    @Override
    public Site call(SiteDTO siteDTO) {
        return new Site(siteDTO.getId(), siteDTO.getName());
    }
}
