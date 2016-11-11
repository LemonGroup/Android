package com.slack.geekbrainswork.ai.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SiteDetailsActivity extends AppCompatActivity {

    @BindView(R.id.site_name_edit_text)
    EditText siteNameTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_details);
        ButterKnife.bind(this);

        String siteName = getSiteVo().getName();

        setTitle("Редактирование...");
        siteNameTextView.setText(siteName);
    }

    private Site getSiteVo() {
        return (Site) getIntent().getSerializableExtra("site");
    }
}
