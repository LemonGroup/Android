package com.slack.geekbrainswork.ai.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiteDTO {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("site")
    @Expose
    private String name;

    public SiteDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public SiteDTO(String siteName) {
        name = siteName;
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
