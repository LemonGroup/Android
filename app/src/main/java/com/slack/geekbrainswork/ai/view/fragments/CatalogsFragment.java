package com.slack.geekbrainswork.ai.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.CatalogListPresenter;
import com.slack.geekbrainswork.ai.presenter.BasePresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Catalog;
import com.slack.geekbrainswork.ai.view.MainActivityCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogsFragment extends BaseFragment implements CatalogsView {

    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.list_view)
    ListView catalogsListView;

    private CatalogListPresenter presenter;
    private MainActivityCallback callback;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (MainActivityCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement MainActivityCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog_list, container, false);
        ButterKnife.bind(this, view);

        getActivity().setTitle("Справочники");

        //ToDo Dependency Injection
        presenter = new CatalogListPresenter(this);
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
        callback.startSitesCatalogFragment();
    }

    @Override
    public void navigateToPersonsCatalogView() {
        callback.startPersonsCatalogFragment();
    }

    @Override
    public void navigateToKeywordsCatalogView() {
        //ToDo navigateToKeywordsCatalogView
        makeToast("Функционал не реализован");
    }

    @Override
    public void showError(String error) {
        //ToDo error handling
        makeToast(error);
    }

    private void makeToast(String text) {
        Snackbar.make(layout, text, Snackbar.LENGTH_LONG).show();
    }
}
