package com.slack.geekbrainswork.ai.view.adapters;

import android.view.View;

import com.slack.geekbrainswork.ai.presenter.KeywordListPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Keyword;

import java.util.List;

/**
 * Created by Dell on 26.11.2016.
 */

public class KeywordListAdapter  extends BaseAdapter<Keyword> {

    private KeywordListPresenter presenter;


    public KeywordListAdapter(List<Keyword> list, KeywordListPresenter presenter) {
        super(list);
        this.presenter = presenter;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder viewHolder, int i) {
        final Keyword keyword = list.get(i);
        viewHolder.text.setText(keyword.getKeyword());
        viewHolder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickSite(keyword);
            }
        });

        viewHolder.text.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                presenter.onLongClickSite(keyword);
                return true;
            }
        });
    }

    public void setKeywordList(List<Keyword> list) {
        this.list = list;
        notifyDataSetChanged();
    }

}
