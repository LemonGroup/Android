package com.slack.geekbrainswork.ai.view.fragments;

import com.slack.geekbrainswork.ai.presenter.vo.Catalog;
import com.slack.geekbrainswork.ai.view.View;

import java.util.List;

public interface CatalogsView extends View {
    void showCatalogList(List<Catalog> catalogList);

    void navigateToSitesCatalogView();

    void navigateToPersonsCatalogView();

    void navigateToKeywordsCatalogView();
}
