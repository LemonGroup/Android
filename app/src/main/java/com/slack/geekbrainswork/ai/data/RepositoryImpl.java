package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.LemonStateAdminApp;
import com.slack.geekbrainswork.ai.data.api.ApiClient;
import com.slack.geekbrainswork.ai.data.api.ApiInterface;
import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.data.dto.TokenResponse;
import com.slack.geekbrainswork.ai.data.local.PrefHelper;
import com.slack.geekbrainswork.ai.data.local.PreferencesHelper;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RepositoryImpl implements Repository {

    private final Observable.Transformer schedulersTransformer;
    private ApiInterface apiInterface;
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
    public String getTokenFromStorage() {
        return helper.getFromPref();
    }

    @Override
    public void updateToken(final String token) {
        helper.writeToPref(token);
    }

    @Override
    public Observable<List<SiteDTO>> getSites() {
        //ToDo getSites from Rest
        return apiInterface.getSites()
                .compose(this.<List<SiteDTO>>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> updateSite(SiteDTO siteDTO) {
        //ToDo updateSite by Rest
        return apiInterface.updateSite(siteDTO.getId(), siteDTO)
                .compose(this.<SiteDTO>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> createSite(SiteDTO siteDTO) {
        //ToDo createSite by Rest
        return apiInterface.createSite(siteDTO)
                .compose(this.<SiteDTO>applySchedulers());
    }

    @Override
    public Observable<List<SiteDTO>> removeSite(Site site) {
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
