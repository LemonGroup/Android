package com.slack.geekbrainswork.ai.view.adapters;

import android.view.View;

import com.slack.geekbrainswork.ai.presenter.SiteListPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

public class SiteListAdapter extends BaseAdapter<Site> {
    private SiteListPresenter presenter;


    public SiteListAdapter(List<Site> list, SiteListPresenter presenter) {
        super(list);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        final Site site = list.get(i);
        viewHolder.text.setText(site.getName());
        viewHolder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickSite(site);
            }
        });

        viewHolder.text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                presenter.onLongClickSite(site);
                return true;
            }
        });
    }

    public void setSiteList(List<Site> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
