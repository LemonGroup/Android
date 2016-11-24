package com.slack.geekbrainswork.ai.view.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.BasePresenter;
import com.slack.geekbrainswork.ai.presenter.SiteListPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.view.activities.AddSiteActvity;
import com.slack.geekbrainswork.ai.view.activities.UpdateSiteActivity;
import com.slack.geekbrainswork.ai.view.adapters.SiteListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class SiteListFragment extends BaseFragment implements SiteListView {

    @BindView(R.id.layout)
    FrameLayout layout;
    @BindView(R.id.fab)
    FloatingActionButton addButton;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    //ToDo Dependency Injection
    private SiteListPresenter presenter = new SiteListPresenter(this);

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
    public void showRemoveSiteDialog(final Site site) {
        final AlertDialog dialog = createDialog(site);
        dialog.show();
    }

    @NonNull
    private AlertDialog createDialog(final Site site) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(site.getName());
        builder.setCancelable(true);
        builder.setNeutralButton(R.string.action_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onClickRemoveButton(site);
            }
        });
        final AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.text_color));
            }
        });
        return dialog;
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
        makeToast(error);
    }

    private void makeToast(String text) {
        Snackbar.make(layout, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}
