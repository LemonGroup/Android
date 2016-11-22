package com.slack.geekbrainswork.ai.view.adapters;

import android.view.View;

import com.slack.geekbrainswork.ai.presenter.PersonListPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Person;

import java.util.List;

/**
 * Created by Prilepishev Vadim on 22.11.2016.
 */

public class PersonListAdapter extends BaseAdapter<Person> {

    private PersonListPresenter presenter;


    public PersonListAdapter(List<Person> list, PersonListPresenter presenter) {
        super(list);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        final Person person = list.get(i);
        viewHolder.text.setText(person.getName());
        viewHolder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickSite(person);
            }
        });

        viewHolder.text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                presenter.onLongClickSite(person);
                return true;
            }
        });
    }

    public void setSiteList(List<Person> list) {
        this.list = list;
        notifyDataSetChanged();
    }

}
