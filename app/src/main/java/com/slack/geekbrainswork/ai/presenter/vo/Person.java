package com.slack.geekbrainswork.ai.presenter.vo;

import java.io.Serializable;

/**
 * Created by Dell on 19.11.2016.
 */

public class Person implements Serializable {
    private Integer id;
    private String name;

    public Person(Integer id, String name) {
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

    public void setName(String name) {
        this.name = name;
    }


}
