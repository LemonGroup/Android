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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.BasePresenter;
import com.slack.geekbrainswork.ai.presenter.UserListPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Site;
import com.slack.geekbrainswork.ai.presenter.vo.User;
import com.slack.geekbrainswork.ai.view.activities.UserAddActivity;
import com.slack.geekbrainswork.ai.view.activities.UserUpdateActivity;
import com.slack.geekbrainswork.ai.view.adapters.UserListAdepter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class UserListFragment extends BaseFragment implements UserListView {

    @BindView(R.id.layout)
    FrameLayout layout;
    @BindView(R.id.fab)
    FloatingActionButton addButton;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private UserListPresenter presenter = new UserListPresenter(this);
    private UserListAdepter adapter;

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog_sites, container, false);
        ButterKnife.bind(this, view);

        getActivity().setTitle("Пользователи");

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        adapter = new UserListAdepter(new ArrayList<User>(), presenter);
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
    public void showUserList(List<User> userList) {
        adapter.setUserList(userList);
    }

    @Override
    public void navigateToUpdateUserView(User user) {
        Intent intent = new Intent(getActivity(), UserUpdateActivity.class);
        intent.putExtra("user", user);
        startActivityForResult(intent, 10);
    }

    @Override
    public void navigateToAddUserView() {
        Intent intent = new Intent(getActivity(), UserAddActivity.class);
        startActivityForResult(intent, 11);
    }

    @Override
    public void showRemoveUserDialog(final User user) {
        final AlertDialog dialog = createDialog(user);
        dialog.show();
    }

    @NonNull
    private AlertDialog createDialog(final User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(user.getName());
        builder.setCancelable(true);
        builder.setNeutralButton(R.string.action_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onClickRemoveButton(user);
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
