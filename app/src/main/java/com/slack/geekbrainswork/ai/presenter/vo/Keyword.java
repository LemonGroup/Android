package com.slack.geekbrainswork.ai.presenter.vo;

import java.io.Serializable;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public class Keyword implements Serializable {

    private Integer id;
    private String keyword;

    public Keyword(Integer id, String keyword) {
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
