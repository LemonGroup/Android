package com.slack.geekbrainswork.ai.model;

import com.slack.geekbrainswork.ai.model.api.ApiClient;
import com.slack.geekbrainswork.ai.model.api.ApiInterface;
import com.slack.geekbrainswork.ai.model.dto.SiteDTO;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ModelImpl implements Model {

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
        //ToDo getSites rom Rest
//        return apiInterface.getSites()
//                .compose(this.<List<SiteDTO>>applySchedulers());


        List<SiteDTO> siteDTOList = new ArrayList<>();
        siteDTOList.add(new SiteDTO(1, "lenta.ru"));
        siteDTOList.add(new SiteDTO(2, "echo.msk.ru"));
        return Observable.just(siteDTOList)
                .compose(this.<List<SiteDTO>>applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
