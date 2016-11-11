package com.slack.geekbrainswork.ai.presenter.vo;

public class Catalog {
    private String name;

    public Catalog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
