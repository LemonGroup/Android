package com.slack.geekbrainswork.ai.view.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.BasePresenter;
import com.slack.geekbrainswork.ai.presenter.KeywordListPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Keyword;
import com.slack.geekbrainswork.ai.view.activities.AddKeywordActivity;
import com.slack.geekbrainswork.ai.view.activities.UpdateKeywordActivity;
import com.slack.geekbrainswork.ai.view.adapters.KeywordListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Prilepishev Vadim on 26.11.2016.
 */

public class KeywordListFragment extends BaseFragment implements KeywordListView {

    @BindView(R.id.fab_keywords)
    FloatingActionButton addButton;
    @BindView(R.id.recycler_view_keywords)
    RecyclerView recyclerView;

    //ToDo Dependency Injection
    private KeywordListPresenter presenter = new KeywordListPresenter(this);

    private KeywordListAdapter adapter;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog_keywords, container, false);
        ButterKnife.bind(this, view);

        getActivity().setTitle("Список слов");

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapter = new KeywordListAdapter(new ArrayList<Keyword>(), presenter);
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
    public void navigateToUpdateKeywordView(Keyword keyword) {
        Intent intent = new Intent(getActivity(), UpdateKeywordActivity.class);
        intent.putExtra("keyword", keyword);
        startActivityForResult(intent, 10);
    }

    @Override
    public void navigateToAddKeywordView() {
        Intent intent = new Intent(getActivity(), AddKeywordActivity.class);
        startActivityForResult(intent, 11);
    }

    @Override
    public void showRemoveKeywordDialog(final Keyword keyword) {
        final AlertDialog dialog = createDialog(keyword);
        dialog.show();
    }

    @NonNull
    private AlertDialog createDialog(final Keyword keyword) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(keyword.getKeyword());
        builder.setCancelable(true);
        builder.setNeutralButton(R.string.action_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onClickRemoveButton(keyword);
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
    public void showKeywordList(List<Keyword> keywordList) {
        adapter.setKeywordList(keywordList);
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
