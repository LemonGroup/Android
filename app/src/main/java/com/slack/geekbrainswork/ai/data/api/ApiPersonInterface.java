package com.slack.geekbrainswork.ai.data.api;

import com.slack.geekbrainswork.ai.data.dto.PersonDTO;
import com.slack.geekbrainswork.ai.data.dto.SiteDTO;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Prilepishev Vadim on 19.11.2016.
 */

public interface ApiPersonInterface {
    //ToDo rest methods
    @GET("/persons")
    Observable<List<PersonDTO>> getPersons();

    @PUT("/persons/{id}")
    Observable<PersonDTO> updatePerson(@Path("ID") int personId, @Field("Name") String name);

    @POST("/person/new")
    Observable<PersonDTO> createPerson(@Body String person);

    @DELETE("/person/{id}")
    void deletePerson(@Path("id") int personId);

}
