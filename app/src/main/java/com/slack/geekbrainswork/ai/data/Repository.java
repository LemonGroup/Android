package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

public interface Repository {

    Observable<List<SiteDTO>> getSites();

    Observable<SiteDTO> updateSite(Site site);

    Observable<SiteDTO> createSite(String siteName);

    Observable<Response> deleteSite(Integer siteId);
}

