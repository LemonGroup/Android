package com.slack.geekbrainswork.ai.model;

import com.slack.geekbrainswork.ai.model.api.ApiClient;
import com.slack.geekbrainswork.ai.model.api.ApiInterface;
import com.slack.geekbrainswork.ai.model.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ModelImpl implements Model {

    // Fake Data
    private List<SiteDTO> siteDTOList = new ArrayList<>();

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

        siteDTOList.add(new SiteDTO(1, "lenta.ru"));
        siteDTOList.add(new SiteDTO(2, "echo.msk.ru"));
    }

    @Override
    public Observable<List<SiteDTO>> getSites() {
        //ToDo getSites from Rest
        //return apiInterface.getSites()
        //         .compose(this.<List<SiteDTO>>applySchedulers());

        return Observable.just(siteDTOList)
                .compose(this.<List<SiteDTO>>applySchedulers());
    }

    @Override
    public Observable<SiteDTO> updateSite(Site site) {
        //ToDo updateSite by Rest
        //return apiInterface.updateSite(site.getId(), site.getName());
        SiteDTO updatedSiteDTO = new SiteDTO(site.getId(), site.getName());
//        for (SiteDTO siteDTO : siteDTOList) {
//            if (siteDTO.getId() == site.getId()) {
//                siteDTO.setName(site.getName());
//            }
//        }

        for (int i = 0; i < siteDTOList.size(); i++) {
            if (siteDTOList.get(i).getId() == site.getId()){
                siteDTOList.get(i).setName(site.getName());
            }
        }

        return Observable.just(updatedSiteDTO)
                .compose(this.<SiteDTO>applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
