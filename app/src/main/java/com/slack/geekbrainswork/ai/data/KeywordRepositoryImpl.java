package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.LemonStateAdminApp;
import com.slack.geekbrainswork.ai.data.api.ApiClient;
import com.slack.geekbrainswork.ai.data.api.ApiInterface;
import com.slack.geekbrainswork.ai.data.dto.KeywordDTO;
import com.slack.geekbrainswork.ai.data.dto.TokenResponse;
import com.slack.geekbrainswork.ai.data.local.PrefHelper;
import com.slack.geekbrainswork.ai.data.local.PreferencesHelper;
import com.slack.geekbrainswork.ai.presenter.vo.Keyword;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public class KeywordRepositoryImpl implements KeywordRepository {

    private final Observable.Transformer schedulersTransformer;
    private ApiInterface apiInterface = ApiClient.getApiInterface();
    private ApiInterface loginApiInterface;
    private PrefHelper helper = new PreferencesHelper(LemonStateAdminApp.getContext());

    public KeywordRepositoryImpl() {
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
    public Observable<List<KeywordDTO>> getKeywords() {
        return apiInterface.getKeywords()
                .compose(this.<List<KeywordDTO>>applySchedulers());
    }

    @Override
    public Observable<KeywordDTO> updateKeyword(final Keyword keyword) {
        return apiInterface.updateKeyword(keyword.getId(), keyword.getKeyword())
                .compose(this.<KeywordDTO>applySchedulers());
    }

    @Override
    public Observable<KeywordDTO> createKeyword(final String keywordName) {
        return apiInterface.createKeyword(keywordName)
                .compose(this.<KeywordDTO>applySchedulers());
    }

    @Override
    public Observable<Void> removeKeyword(final Keyword keyword) {
        return apiInterface.removeKeyword(keyword.getId())
                .compose(this.<Void>applySchedulers());
    }


    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
