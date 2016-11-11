package com.slack.geekbrainswork.ai.model;

import com.slack.geekbrainswork.ai.model.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

import rx.Observable;

public interface Model {

    Observable<List<SiteDTO>> getSites();

    Observable<SiteDTO> updateSite(Site site);
}

