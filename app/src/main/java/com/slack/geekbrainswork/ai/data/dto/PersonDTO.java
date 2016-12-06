package com.slack.geekbrainswork.ai.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prilepishev Vadim on 19.11.2016.
 */

public class PersonDTO {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("personName")
    @Expose
    private String name;

    public PersonDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public PersonDTO(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
