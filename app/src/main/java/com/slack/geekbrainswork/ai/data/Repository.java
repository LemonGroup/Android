package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.data.dto.TokenResponse;
import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.presenter.vo.User;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

public interface Repository {

    String getTokenFromStorage();

    void updateToken(String token);

    Observable<List<SiteDTO>> getSites();

    Observable<SiteDTO> updateSite(SiteDTO siteDTO);

    Observable<SiteDTO> createSite(SiteDTO siteDTO);

    Observable<Void> deleteSite(Site site);

    Observable<TokenResponse> auth(String login, String password);

    Observable<List<UserDTO>> getUsers();
}

