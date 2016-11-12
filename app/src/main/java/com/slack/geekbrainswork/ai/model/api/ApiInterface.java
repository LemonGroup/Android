package com.slack.geekbrainswork.ai.model.api;

import com.slack.geekbrainswork.ai.model.dto.SiteDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {
    //ToDo rest methods
    @GET("/sites")
    Observable<List<SiteDTO>> getSites();

    @POST("/sites/{id}")
    Observable<SiteDTO> updateSite(@Path("ID") int siteId, @Field("Name") String name);

    @POST("/sites/new")
    Observable<SiteDTO> createSite(@Body Site site);

    @DELETE("/sites/{id}")
    Observable<Response> deleteSite(@Path("id") int itemId);
}
