package com.slack.geekbrainswork.ai.data.api;

import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import rx.Observable;

public class ApiDemo {

    private static ApiDemo api;
    private static List<SiteDTO> siteDTOList = new ArrayList<>();

    private ApiDemo() {
        siteDTOList.add(new SiteDTO(1, "lenta.ru"));
        siteDTOList.add(new SiteDTO(2, "echo.msk.ru"));
    }

    public static ApiDemo getApi() {
        if (api == null) {
            api = new ApiDemo();
        }
        return api;
    }

    public List<SiteDTO> getSiteDTOList() {
        return siteDTOList;
    }

    public SiteDTO updateSiteDTO(Site site) {
        for (int i = 0; i < siteDTOList.size(); i++) {
            if (siteDTOList.get(i).getId().intValue() == site.getId().intValue()) {
                siteDTOList.get(i).setName(site.getName());
                return siteDTOList.get(i);
            }
        }
        return null;
    }

    public SiteDTO createSiteDTO(String siteName) {
        SiteDTO siteDTO = new SiteDTO(getMaxId() + 1, siteName);
        siteDTOList.add(siteDTO);
        return siteDTO;
    }

    private Integer getMaxId() {
        Integer maxId = 0;
        Integer curId;
        for (SiteDTO siteDTO : siteDTOList) {
            curId = siteDTO.getId();
            if (curId > maxId) {
                maxId = curId;
            }
        }

        return maxId;
    }

    public Observable<List<SiteDTO>> removeSiteDTO(Site site) {
        for (Iterator<SiteDTO> iterator = siteDTOList.iterator(); iterator.hasNext();) {
            SiteDTO siteDTO = iterator.next();
            if (siteDTO.getId().intValue() == site.getId().intValue()) {
                iterator.remove();
                return Observable.just(siteDTOList);
            }
        }
        return Observable.just(siteDTOList);
    }
}
