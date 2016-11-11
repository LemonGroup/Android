package com.slack.geekbrainswork.ai.view.adapters;

import android.view.View;

import com.slack.geekbrainswork.ai.presenter.SitesCatalogPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import java.util.List;

public class SiteListAdapter extends BaseAdapter<Site> {
    private SitesCatalogPresenter presenter;


    public SiteListAdapter(List<Site> list, SitesCatalogPresenter presenter) {
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
                presenter.clickSite(site);
            }
        });
    }

    public void setSiteList(List<Site> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
