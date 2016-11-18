package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.data.dto.TokenResponse;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

public interface Repository {

    String getTokenFromStorage();

    void updateToken(String token);

    Observable<List<SiteDTO>> getSites();

    Observable<SiteDTO> updateSite(Site site);

    Observable<SiteDTO> createSite(String siteName);

    Observable<List<SiteDTO>> removeSite(Site site);

    Observable<String> auth();

}

