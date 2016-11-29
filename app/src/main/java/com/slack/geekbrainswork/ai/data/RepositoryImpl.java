package com.slack.geekbrainswork.ai.data;

import com.slack.geekbrainswork.ai.data.api.ApiClient;
import com.slack.geekbrainswork.ai.data.api.ApiDemo;
import com.slack.geekbrainswork.ai.data.api.ApiInterface;
import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RepositoryImpl implements Repository {

    private final Observable.Transformer schedulersTransformer;
    private ApiInterface apiInterface = ApiClient.getApiInterface();

    public RepositoryImpl() {
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
        return apiInterface.getSites()
         .compose(this.<List<SiteDTO>>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> updateSite(Site site) {
        //ToDo updateSite by Rest
        return apiInterface.updateSite(site.getId(), site.getName())
         .compose(this.<SiteDTO>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> createSite(String siteName) {
        //ToDo createSite by Rest
        return apiInterface.createSite(siteName)
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
