package com.slack.geekbrainswork.ai.data.api;

import com.slack.geekbrainswork.ai.data.dto.IsBusyResponse;
import com.slack.geekbrainswork.ai.data.dto.KeywordDTO;
import com.slack.geekbrainswork.ai.data.dto.PersonDTO;
import com.slack.geekbrainswork.ai.data.dto.SiteDTO;
import com.slack.geekbrainswork.ai.data.dto.TokenResponse;
import com.slack.geekbrainswork.ai.data.dto.UserDTO;
import com.slack.geekbrainswork.ai.presenter.vo.Catalog;
import com.slack.geekbrainswork.ai.presenter.vo.Person;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.presenter.vo.User;

import java.util.List;

import okhttp3.ResponseBody;
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
    // Login
    @POST("user/{user}/{password}")
    Observable<String> registration(@Path("user") String email, @Path("password") String password);

    @GET("user/auth")
    Observable<TokenResponse> auth(@Query("user") String email, @Query("pass") String password);


    // Sites
    @GET("catalog/sites")
    Observable<List<SiteDTO>> getSites();

    @POST("catalog/sites")
    Observable<SiteDTO> createSite(@Body SiteDTO siteDTO);

    @PUT("catalog/sites")
    Observable<SiteDTO> updateSite(@Body SiteDTO siteDTO);

    @DELETE("catalog/sites/{id}")
    Observable<Void> deleteSite(@Path("id") int siteId);


    // Users
    @GET("catalog/accounts")
    Observable<List<UserDTO>> getUsers();

    @PUT("catalog/accounts/password")
    Observable<Response<Void>> changePassword(@Body UserDTO userDTO);

    @DELETE("catalog/accounts/{id}")
    Observable<Void> deleteUser(@Path("id") int id);

    @POST("catalog/accounts")
    Observable<UserDTO> createUser(@Body UserDTO userDTO);

    @POST("user/reguser")
    Observable<UserDTO> regUser(@Body UserDTO userDTO);

    @POST("user/reguser/check_user")
    Observable<Response<Void>> checkLogin(@Query("username") String userName);

    @POST("user/reguser/check_email")
    Observable<Response<Void>> checkEmail(@Query("email") String email);

    @POST("catalog/accounts/reset_password")
    Observable<Response<Void>> resetPassword(@Body UserDTO userDTO);


    //Persons
    @GET("/catalog/persons")
    Observable<List<PersonDTO>> getPersons();

    @PUT("/catalog/persons")
    Observable<PersonDTO> updatePerson(@Body PersonDTO personDTO);

    @POST("/catalog/persons")
    Observable<PersonDTO> createPerson(@Body PersonDTO person);

    @DELETE("/catalog/persons/{id}")
    Observable<Void> removePerson(@Path("id") int personId);


    //Keywords
    @GET("/catalog/keywords")
    Observable<List<KeywordDTO>> getKeywords();

    @PUT("/catalog/keywords")
    Observable<KeywordDTO> updateKeyword(@Path("id") int keywordID, @Field("keyword") String keyword);

    @POST("/catalog/keywords")
    Observable<KeywordDTO> createKeyword(@Body String keyword);

    @DELETE("/catalog/keywords/{id}")
    Observable<Void>  removeKeyword(@Path("id") int keywordId);

}