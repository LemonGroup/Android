package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.dto.IsBusyResponse;
import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.data.dto.TokenResponse;
import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.presenter.vo.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;
import rx.Subscription;

public interface Repository {

    String getTokenFromStorage();

    void updateToken(String token);

    Observable<List<SiteDTO>> getSites();

    Observable<SiteDTO> updateSite(SiteDTO siteDTO);

    Observable<SiteDTO> createSite(SiteDTO siteDTO);

    Observable<Void> deleteSite(int id);

    Observable<TokenResponse> auth(String login, String password);

    Observable<List<UserDTO>> getUsers();

    Observable<Response<Void>> changePassword(UserDTO userDTO);

    Observable<Void> deleteUser(int id);

    Observable<Response<Void>> checkLogin(String login);

    Observable<Response<Void>> checkEmail(String email);

    Observable<UserDTO> createUser(UserDTO userDTO);

    Observable<UserDTO> regUser(UserDTO userDTO);

    Observable<Response<Void>> resetPassword(UserDTO userDTO);
}