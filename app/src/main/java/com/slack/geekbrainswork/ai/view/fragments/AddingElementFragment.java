package com.slack.geekbrainswork.ai.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.AddingElementPresenter;
import com.slack.geekbrainswork.ai.presenter.BasePresenter;

import butterknife.BindView;

public class AddingElementFragment extends BaseFragment implements AddingElementView{

    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private AddingElementPresenter presenter;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog_layout, container, false);

        //ToDo Dependency Injection
        presenter = new AddingElementPresenter();
        presenter.onCreate(savedInstanceState);

        return view;
    }

    @Override
    public void showError(String error) {
        //ToDo error handling
    }
}
