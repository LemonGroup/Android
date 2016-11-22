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
import com.slack.geekbrainswork.ai.presenter.PersonListPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Person;
import com.slack.geekbrainswork.ai.view.activities.AddPersonActivity;
import com.slack.geekbrainswork.ai.view.activities.UpdatePersonActivity;
import com.slack.geekbrainswork.ai.view.adapters.PersonListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Prilepishev Vadim on 22.11.2016.
 */

public class PersonListFragment extends BaseFragment implements PersonListView {

    @BindView(R.id.fab_persons)
    FloatingActionButton addButton;
    @BindView(R.id.recycler_view_persons)
    RecyclerView recyclerView;

    //ToDo Dependency Injection
    private PersonListPresenter presenter = new PersonListPresenter(this);

    private PersonListAdapter adapter;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog_persons, container, false);
        ButterKnife.bind(this, view);

        getActivity().setTitle("Список личностей");

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapter = new PersonListAdapter(new ArrayList<Person>(), presenter);
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
    public void navigateToUpdatePersonView(Person person) {
        Intent intent = new Intent(getActivity(), UpdatePersonActivity.class);
        intent.putExtra("person", person);
        startActivityForResult(intent, 10);
    }

    @Override
    public void navigateToAddPersonView() {
        Intent intent = new Intent(getActivity(), AddPersonActivity.class);
        startActivityForResult(intent, 11);
    }

    @Override
    public void showRemovePersonDialog(final Person person) {
        final AlertDialog dialog = createDialog(person);
        dialog.show();
    }

    @NonNull
    private AlertDialog createDialog(final Person person) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(person.getName());
        builder.setCancelable(true);
        builder.setNeutralButton(R.string.action_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onClickRemoveButton(person);
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
    public void showPersonList(List<Person> personList) {
        adapter.setSiteList(personList);
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
