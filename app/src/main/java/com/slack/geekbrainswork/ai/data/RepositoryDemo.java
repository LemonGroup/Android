package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.api.ApiDemo;
import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RepositoryDemo implements Repository{

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
        return Observable.just(api.getSiteDTOList())
                .compose(this.<List<SiteDTO>>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> updateSite(Site site) {
        return Observable.just(api.updateSiteDTO(site))
                .compose(this.<SiteDTO>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> createSite(String siteName) {
        return Observable.just(api.createSiteDTO(siteName))
                .compose(this.<SiteDTO>applySchedulers());
    }

    @Override
    public Observable<Response> deleteSite(Integer siteId) {
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
