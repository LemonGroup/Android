package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.LemonStateAdminApp;
import com.slack.geekbrainswork.ai.data.api.ApiClient;
import com.slack.geekbrainswork.ai.data.api.ApiInterface;
import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.data.dto.TokenResponse;
import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.data.local.PrefHelper;
import com.slack.geekbrainswork.ai.data.local.PreferencesHelper;

import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RepositoryImpl implements Repository {

    private final Observable.Transformer schedulersTransformer;
    private ApiInterface apiInterface = ApiClient.getApiInterface();;
    private ApiInterface loginApiInterface;
    private PrefHelper helper = new PreferencesHelper(LemonStateAdminApp.getContext());

    public RepositoryImpl() {
        schedulersTransformer = new Observable.Transformer() {
            @Override
            public Object call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

        loginApiInterface = ApiClient.getApiInterface();
        apiInterface = ApiClient.getApiInterface(getTokenFromStorage());
    }

    @Override
    public Observable<TokenResponse> auth(String login, String password) {
        return loginApiInterface.auth(login, password)
                .compose(this.<TokenResponse>applySchedulers());
    }

    @Override
    public Observable<List<UserDTO>> getUsers() {
        return apiInterface.getUsers()
                .compose(this.<List<UserDTO>>applySchedulers());
    }

    @Override
    public Observable<Response<Void>> changePassword(UserDTO userDTO) {
        return apiInterface.changePassword(userDTO)
                .compose(this.<Response<Void>>applySchedulers());
    }

    @Override
    public Observable<Void> deleteUser(int id) {
        return apiInterface.deleteUser(id)
                .compose(this.<Void>applySchedulers());
    }

    @Override
    public Observable<Response<Void>> checkLogin(String login) {
        return loginApiInterface.checkLogin(login)
                .compose(this.<Response<Void>>applySchedulers());
    }

    @Override
    public Observable<Response<Void>> checkEmail(String email) {
        return loginApiInterface.checkEmail(email)
                .compose(this.<Response<Void>>applySchedulers());
    }

    @Override
    public Observable<UserDTO> createUser(UserDTO userDTO) {
        return apiInterface.createUser(userDTO)
                .compose(this.<UserDTO>applySchedulers());
    }

    @Override
    public Observable<UserDTO> regUser(UserDTO userDTO) {
        return loginApiInterface.regUser(userDTO)
                .compose(this.<UserDTO>applySchedulers());
    }

    @Override
    public Observable<Response<Void>> resetPassword(UserDTO userDTO) {
        return loginApiInterface.resetPassword(userDTO)
                .compose(this.<Response<Void>>applySchedulers());
    }

    @Override
    public String getTokenFromStorage() {
        return helper.getFromPref();
    }

    @Override
    public void updateToken(final String token) {
        helper.writeToPref(token);
    }

    @Override
    public Observable<List<SiteDTO>> getSites() {
        return apiInterface.getSites()
                .compose(this.<List<SiteDTO>>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> updateSite(SiteDTO siteDTO) {
        return apiInterface.updateSite(siteDTO)
                .compose(this.<SiteDTO>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> createSite(SiteDTO siteDTO) {
        return apiInterface.createSite(siteDTO)
                .compose(this.<SiteDTO>applySchedulers());
    }

    @Override
    public Observable<Void> deleteSite(int id) {
        return apiInterface.deleteSite(id)
                .compose(this.<Void>applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}