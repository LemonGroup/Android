package com.slack.geekbrainswork.ai.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public class KeywordDTO {
    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("Keyword")
    @Expose
    private String keyword;

    public KeywordDTO(Integer id, String keyword) {
        this.id = id;
        this.keyword = keyword;
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
