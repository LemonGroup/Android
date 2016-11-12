package com.slack.geekbrainswork.ai.model;

import com.slack.geekbrainswork.ai.model.api.ApiClient;
import com.slack.geekbrainswork.ai.model.api.ApiInterface;
import com.slack.geekbrainswork.ai.model.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ModelImpl implements Model {

    // Fake Data
    private ApiMock apiMock = ApiMock.getMock();

    private final Observable.Transformer schedulersTransformer;
    private ApiInterface apiInterface = ApiClient.getApiInterface();

    public ModelImpl() {
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
        //ToDo getSites from Rest
        //return apiInterface.getSites()
        // .compose(this.<List<SiteDTO>>applySchedulers());

        return Observable.just(apiMock.getSiteDTOList())
                .compose(this.<List<SiteDTO>>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> updateSite(Site site) {
        //ToDo updateSite by Rest
        //return apiInterface.updateSite(site.getId(), site.getName())
        // .compose(this.<List<SiteDTO>>applySchedulers());

        return Observable.just(apiMock.updateSiteDTO(site))
                .compose(this.<SiteDTO>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> createSite(String siteName) {
        //ToDo createSite by Rest
        //return apiInterface.createSite(site)
        // .compose(this.<List<SiteDTO>>applySchedulers());

        return Observable.just(apiMock.createSiteDTO(siteName))
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
