package com.slack.geekbrainswork.ai.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.view.View;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.BasePresenter;
import com.slack.geekbrainswork.ai.presenter.SitesCatalogPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.view.activities.SiteDetailsActivity;
import com.slack.geekbrainswork.ai.view.adapters.SiteListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class SitesCatalogFragment extends BaseFragment implements SitesCatalogView {

    @BindView(R.id.fab)
    FloatingActionButton addButton;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    //ToDo Dependency Injection
    private SitesCatalogPresenter presenter = new SitesCatalogPresenter(this);

    private SiteListAdapter adapter;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog_sites, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapter = new SiteListAdapter(new ArrayList<Site>(), presenter);
        recyclerView.setAdapter(adapter);

        presenter.onCreate(savedInstanceState);

        return view;
    }

    @Override
    public void navigateToSiteDetails(Site site) {
        Intent intent = new Intent(getActivity(), SiteDetailsActivity.class);
        startActivityForResult(intent, 10);
    }

    @Override
    public void showSiteList(List<Site> siteList) {
        adapter.setSiteList(siteList);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                //ToDo on result action
            }
        }
    }

    @Override
    public void showError(String error) {

    }
}
