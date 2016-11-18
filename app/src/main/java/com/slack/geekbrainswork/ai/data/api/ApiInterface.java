package com.slack.geekbrainswork.ai.data.api;

import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.data.dto.TokenResponse;
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
    @GET("sites")
    Observable<List<SiteDTO>> getSites();

    @PUT("sites/{id}")
    Observable<SiteDTO> updateSite(@Path("ID") int siteId, @Field("Name") String name);

    @POST("sites/new")
    Observable<SiteDTO> createSite(@Body String site);

    @DELETE("sites/{id}")
    void deleteSite(@Path("id") int itemId);

    //Login methods
    @POST("auth")
    Observable<TokenResponse> auth(@Query("email") String email, @Query("password") String password);

    @POST("api/{email}/{password}")
    Observable<String> registration(@Path("email") String email, @Path("password") String password);
}
