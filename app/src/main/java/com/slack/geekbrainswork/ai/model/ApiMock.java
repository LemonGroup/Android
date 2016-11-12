package com.slack.geekbrainswork.ai.model;


import com.slack.geekbrainswork.ai.model.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.ArrayList;
import java.util.List;

public class ApiMock {

    private static ApiMock mock;
    private static List<SiteDTO> siteDTOList = new ArrayList<>();

    private ApiMock() {
        siteDTOList.add(new SiteDTO(1, "lenta.ru"));
        siteDTOList.add(new SiteDTO(2, "echo.msk.ru"));
    }

    public static ApiMock getMock() {

        if (mock == null) {
            mock = new ApiMock();
        }
        return mock;
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
}
