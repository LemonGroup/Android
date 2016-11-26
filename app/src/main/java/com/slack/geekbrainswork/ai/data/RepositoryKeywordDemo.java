package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.api.ApiKeywordDemo;
import com.slack.geekbrainswork.ai.data.dto.KeywordDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Keyword;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public class RepositoryKeywordDemo implements KeywordRepository {
    private final Observable.Transformer schedulersTransformer;
    private ApiKeywordDemo keywordApi = ApiKeywordDemo.getkeywordApi();

    public RepositoryKeywordDemo() {
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
        return Observable.defer(new Func0<Observable<List<KeywordDTO>>>() {
            @Override
            public Observable<List<KeywordDTO>> call() {
                return Observable.just(keywordApi.getKeywordDTOList());
            }
        })
                .compose(this.<List<KeywordDTO>>applySchedulers());
    }

    @Override
    public Observable<KeywordDTO> updateKeyword(final Keyword keyword) {
        return Observable.defer(new Func0<Observable<KeywordDTO>>() {
            @Override
            public Observable<KeywordDTO> call() {
                return Observable.just(keywordApi.updateKeywordDTO(keyword));
            }
        })
                .compose(this.<KeywordDTO>applySchedulers());
    }

    @Override
    public Observable<KeywordDTO> createKeyword(final String keywordName) {
        return Observable.defer(new Func0<Observable<KeywordDTO>>() {
            @Override
            public Observable<KeywordDTO> call() {
                return Observable.just(keywordApi.createKeywordDTO(keywordName));
            }
        })
                .compose(this.<KeywordDTO>applySchedulers());
    }

    @Override
    public Observable<List<KeywordDTO>> removeKeyword(final Keyword keyword) {
        return Observable.defer(new Func0<Observable<List<KeywordDTO>>>() {
            @Override
            public Observable<List<KeywordDTO>> call() {
                return Observable.just(keywordApi.removeKeywordDTO(keyword));
            }
        })
                .compose(this.<List<KeywordDTO>>applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
