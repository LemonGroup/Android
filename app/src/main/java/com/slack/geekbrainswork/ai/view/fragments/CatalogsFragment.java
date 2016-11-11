package com.slack.geekbrainswork.ai.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.CatalogsPresenter;
import com.slack.geekbrainswork.ai.presenter.BasePresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Catalog;
import com.slack.geekbrainswork.ai.view.ActivityCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogsFragment extends BaseFragment implements CatalogsView {

    @BindView(R.id.list_view)
    ListView catalogsListView;

    private CatalogsPresenter presenter;
    private ActivityCallback activityCallback;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            activityCallback = (ActivityCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement activityCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog_list, container, false);
        ButterKnife.bind(this, view);

        //ToDo Dependency Injection
        presenter = new CatalogsPresenter(this);
        presenter.onCreate();

        return view;
    }

    @Override
    public void showCatalogList(List<Catalog> catalogList) {
        final ArrayAdapter<Catalog> adapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, catalogList);

        catalogsListView.setAdapter(adapter);
        catalogsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onItemClick(adapter.getItem(position));
            }
        });
    }

    @Override
    public void navigateToSitesCatalogView() {
        activityCallback.startSitesCatalogFragment();
    }

    @Override
    public void navigateToPersonsCatalogView() {
        //ToDo navigateToPersonsCatalogView
    }

    @Override
    public void navigateToKeywordsCatalogView() {
        //ToDo navigateToKeywordsCatalogView
    }

    @Override
    public void showError(String error) {
        //ToDo error handling
    }
}
