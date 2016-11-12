package com.slack.geekbrainswork.ai.model;

import com.slack.geekbrainswork.ai.model.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

public interface Model {

    Observable<List<SiteDTO>> getSites();

    Observable<SiteDTO> updateSite(Site site);

    Observable<SiteDTO> createSite(String siteName);

    Observable<Response> deleteSite(Integer siteId);
}

