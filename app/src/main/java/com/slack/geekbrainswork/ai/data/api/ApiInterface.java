package com.slack.geekbrainswork.ai.data.api;

import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.data.dto.TokenResponse;
import com.slack.geekbrainswork.ai.presenter.vo.Catalog;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

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
    @GET("catalog/catalogs")
    Observable<List<Catalog>> getCatalogs();


    // Sites
    @GET("catalog/sites")
    Observable<List<SiteDTO>> getSites();

    @POST("catalog/sites")
    Observable<SiteDTO> createSite(@Body SiteDTO siteDTO);

    @PUT("catalog/sites")
    Observable<SiteDTO> updateSite(@Body SiteDTO siteDTO);

    @DELETE("catalog/sites/{id}")
    Observable<Void> deleteSite(@Path("id") int siteId);


    //Login
    @POST("user/{user}/{password}")
    Observable<String> registration(@Path("user") String email, @Path("password") String password);

    @GET("user/auth")
    Observable<TokenResponse> auth(@Query("user") String email, @Query("pass") String password);

}
