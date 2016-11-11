package com.slack.geekbrainswork.ai.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.view.View;
import android.widget.ImageButton;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.BasePresenter;
import com.slack.geekbrainswork.ai.presenter.CatalogsPresenter;
import com.slack.geekbrainswork.ai.presenter.SitesCatalogPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SitesCatalogFragment extends BaseFragment implements SitesCatalogView {

    @BindView(R.id.fab)
    FloatingActionButton addButton;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private SitesCatalogPresenter presenter;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog_sites, container, false);
        ButterKnife.bind(this, view);

        //ToDo Dependency Injection
        presenter = new SitesCatalogPresenter(this);
        presenter.onCreate(savedInstanceState);

        return view;
    }

    @Override
    public void showError(String error) {

    }
}
