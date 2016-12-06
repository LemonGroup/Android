package com.slack.geekbrainswork.ai.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public class KeywordDTO {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("personId")
    @Expose
    private Integer personId;
    @SerializedName("keyword")
    @Expose
    private String keyword;

    public KeywordDTO(Integer id, Integer personId, String keyword) {
        this.id = id;
        this.personId = personId;
        this.keyword = keyword;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
