package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.api.ApiClient;
import com.slack.geekbrainswork.ai.data.api.ApiInterface;
import com.slack.geekbrainswork.ai.data.dto.KeywordDTO;
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

    public KeywordRepositoryImpl() {
        schedulersTransformer = new Observable.Transformer() {
            @Override
            public Object call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @Override
    public Observable<List<KeywordDTO>> getKeywords() {
        //ToDo getKeywords from Rest
        return apiInterface.getKeywords()
                .compose(this.<List<KeywordDTO>>applySchedulers());
    }

    @Override
    public Observable<KeywordDTO> updateKeyword(final Keyword keyword) {
        //ToDo updateKeyword by Rest
        return apiInterface.updateKeyword(keyword.getId(), keyword.getKeyword())
                .compose(this.<KeywordDTO>applySchedulers());
    }

    @Override
    public Observable<KeywordDTO> createKeyword(final String keywordName) {
        //ToDo createKeyword by Rest
        return apiInterface.createKeyword(keywordName)
                .compose(this.<KeywordDTO>applySchedulers());
    }

    @Override
    public Observable<List<KeywordDTO>> removeKeyword(final Keyword keyword) {
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
