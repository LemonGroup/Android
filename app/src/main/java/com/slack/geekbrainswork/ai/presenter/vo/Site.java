package com.slack.geekbrainswork.ai.presenter.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Site {

    private Integer id;
    private String name;

    public Site(Integer id, String name) {
        this.id = id;
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
}
