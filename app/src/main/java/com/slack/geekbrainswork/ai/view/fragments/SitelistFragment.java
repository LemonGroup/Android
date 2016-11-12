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
import com.slack.geekbrainswork.ai.presenter.SitelistPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.view.activities.AddSiteActvity;
import com.slack.geekbrainswork.ai.view.activities.UpdateSiteActivity;
import com.slack.geekbrainswork.ai.view.adapters.SiteListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class SitelistFragment extends BaseFragment implements SitelistView {

    @BindView(R.id.fab)
    FloatingActionButton addButton;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    //ToDo Dependency Injection
    private SitelistPresenter presenter = new SitelistPresenter(this);

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

        getActivity().setTitle("Справочник сайтов");

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapter = new SiteListAdapter(new ArrayList<Site>(), presenter);
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAddButtonClick();
            }
        });

        presenter.onCreate(savedInstanceState);

        return view;
    }

    @Override
    public void navigateToUpdateSiteView(Site site) {
        Intent intent = new Intent(getActivity(), UpdateSiteActivity.class);
        intent.putExtra("site", site);
        startActivityForResult(intent, 10);
    }

    @Override
    public void navigateToAddSiteView() {
        Intent intent = new Intent(getActivity(), AddSiteActvity.class);
        startActivityForResult(intent, 11);
    }

    @Override
    public void showSiteList(List<Site> siteList) {
        adapter.setSiteList(siteList);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10 || requestCode == 11) {
            if (resultCode == RESULT_OK) {
                presenter.onActivityResult();
            }
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}
