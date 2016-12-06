package com.slack.geekbrainswork.ai.presenter.vo;

import java.io.Serializable;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public class Keyword implements Serializable {

    private Integer id;
    private Integer personId;
    private String keyword;

    public Keyword(Integer id, Integer personId, String keyword) {
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
