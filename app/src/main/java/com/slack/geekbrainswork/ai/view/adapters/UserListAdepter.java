package com.slack.geekbrainswork.ai.view.adapters;

<<<<<<< HEAD
import android.view.View;

=======
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.slack.geekbrainswork.ai.R;
>>>>>>> origin/master
import com.slack.geekbrainswork.ai.presenter.UserListPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.User;

import java.util.List;

<<<<<<< HEAD
public class UserListAdepter extends BaseAdapter<User> {

    private UserListPresenter presenter;

    public UserListAdepter(List<User> list, UserListPresenter presenter) {
        super(list);
=======
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListAdepter extends RecyclerView.Adapter<UserListAdepter.ViewHolder> {

    protected List<User> list;
    private UserListPresenter presenter;

    public UserListAdepter(List<User> list, UserListPresenter presenter) {
        this.list = list;
>>>>>>> origin/master
        this.presenter = presenter;
    }

    @Override
<<<<<<< HEAD
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        final User user = list.get(i);
        viewHolder.text.setText(user.getName());
        viewHolder.text.setOnClickListener(new View.OnClickListener() {
=======
    public void onBindViewHolder(UserListAdepter.ViewHolder viewHolder, int i) {
        final User user = list.get(i);
        viewHolder.text.setText(user.getName());
        viewHolder.isAdminText.setText(user.getPrivilege() == 2 ? "A" : "");

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
>>>>>>> origin/master
            @Override
            public void onClick(View v) {
                presenter.onClickUser(user);
            }
        });

<<<<<<< HEAD
        viewHolder.text.setOnLongClickListener(new View.OnLongClickListener() {
=======
        viewHolder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
>>>>>>> origin/master
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
<<<<<<< HEAD
=======

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card)
        CardView cardView;
        @BindView(R.id.textView)
        TextView text;
        @BindView(R.id.isadmin_text_view)
        TextView isAdminText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
>>>>>>> origin/master
}
