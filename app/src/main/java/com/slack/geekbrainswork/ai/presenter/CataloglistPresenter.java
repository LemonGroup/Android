package com.slack.geekbrainswork.ai.presenter;

import com.slack.geekbrainswork.ai.presenter.vo.Catalog;
import com.slack.geekbrainswork.ai.view.fragments.CatalogsView;

import java.util.ArrayList;
import java.util.List;

public class CatalogListPresenter extends BasePresenter {
    //ToDo remove hardcoded data
    private final String keywordsCatalogName = "Ключевые слова";
    private final String personsCatalogName = "Личности";
    private final String sitesCatalogName = "Сайты";

    private CatalogsView view;

    public CatalogListPresenter(CatalogsView view) {
        this.view = view;
    }

    public void onCreate() {
        List<Catalog> catalogList = new ArrayList<>();
        catalogList.add(new Catalog(keywordsCatalogName));
        catalogList.add(new Catalog(personsCatalogName));
        catalogList.add(new Catalog(sitesCatalogName));
        view.showCatalogList(catalogList);
    }

    public void onItemClick(Catalog catalog) {
        switch (catalog.getName()) {
            case keywordsCatalogName:
                view.navigateToKeywordsCatalogView();
                break;
            case personsCatalogName:
                view.navigateToPersonsCatalogView();
                break;
            case sitesCatalogName:
                view.navigateToSitesCatalogView();
                break;
        }
    }
}
