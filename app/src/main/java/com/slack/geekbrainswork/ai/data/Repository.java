package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.data.dto.TokenResponse;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

public interface Repository {

    String getTokenFromStorage();

    Observable<String> updateToken(String token);

    Observable<List<SiteDTO>> getSites();

    Observable<SiteDTO> updateSite(SiteDTO siteDTO);

    Observable<SiteDTO> createSite(SiteDTO siteDTO);

    Observable<List<SiteDTO>> removeSite(Site site);

    Observable<TokenResponse> auth(String login, String password);

}

