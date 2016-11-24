package com.slack.geekbrainswork.ai.view.adapters;

import android.view.View;

import com.slack.geekbrainswork.ai.presenter.UserListPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.User;

import java.util.List;

public class UserListAdepter extends BaseAdapter<User> {

    private UserListPresenter presenter;

    public UserListAdepter(List<User> list, UserListPresenter presenter) {
        super(list);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        final User user = list.get(i);
        viewHolder.text.setText(user.getName());
        viewHolder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickUser(user);
            }
        });

        viewHolder.text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                presenter.onLongClickUser(user);
                return true;
            }
        });
    }

    public void setUserList(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
