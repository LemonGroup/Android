package com.slack.geekbrainswork.ai.model.api;

import com.slack.geekbrainswork.ai.model.dto.SiteDTO;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiInterface {
    //ToDo rest methods
    @GET("")
    Observable<List<SiteDTO>> getSites();
}
