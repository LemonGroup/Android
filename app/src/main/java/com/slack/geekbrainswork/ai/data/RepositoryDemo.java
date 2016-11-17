package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.api.ApiDemo;
import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class RepositoryDemo implements Repository {

    private final Observable.Transformer schedulersTransformer;
    private ApiDemo api = ApiDemo.getApi();

    public RepositoryDemo() {
        schedulersTransformer = new Observable.Transformer() {
            @Override
            public Object call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @Override
    public Observable<List<SiteDTO>> getSites() {
        return Observable.defer(new Func0<Observable<List<SiteDTO>>>() {
            @Override
            public Observable<List<SiteDTO>> call() {
                return Observable.just(api.getSiteDTOList());
            }
        })
                .compose(this.<List<SiteDTO>>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> updateSite(final Site site) {
        return Observable.defer(new Func0<Observable<SiteDTO>>() {
            @Override
            public Observable<SiteDTO> call() {
                return Observable.just(api.updateSiteDTO(site));
            }
        })
                .compose(this.<SiteDTO>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> createSite(final String siteName) {
        return Observable.defer(new Func0<Observable<SiteDTO>>() {
            @Override
            public Observable<SiteDTO> call() {
                return Observable.just(api.createSiteDTO(siteName));
            }
        })
                .compose(this.<SiteDTO>applySchedulers());
    }

    @Override
    public Observable<List<SiteDTO>> removeSite(final Site site) {
        return Observable.defer(new Func0<Observable<List<SiteDTO>>>() {
            @Override
            public Observable<List<SiteDTO>> call() {
                return Observable.just(api.removeSiteDTO(site));
            }
        })
                .compose(this.<List<SiteDTO>>applySchedulers());
    }

    @Override
    public Observable<String> auth() {
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
