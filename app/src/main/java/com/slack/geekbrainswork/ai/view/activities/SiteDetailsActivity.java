package com.slack.geekbrainswork.ai.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.SiteDetailsPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SiteDetailsActivity extends AppCompatActivity implements SiteDetailsView {

    @BindView(R.id.site_name_edit_text)
    EditText siteNameTextView;
    @BindView(R.id.cancel_button)
    Button cancelButton;
    @BindView(R.id.save_button)
    Button saveButton;

    private SiteDetailsPresenter presenter = new SiteDetailsPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_details);
        ButterKnife.bind(this);

        Site site = getSiteVo();

        setTitle(getResources().getString(R.string.catalog_edit_title));
        siteNameTextView.setText(site.getName());

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSaveButtonClick();
            }
        });

        presenter.onCreate(savedInstanceState, site);
    }

    private Site getSiteVo() {
        return (Site) getIntent().getSerializableExtra("site");
    }

    @Override
    public String getSiteNameTextViewText() {
        return siteNameTextView.getText().toString();
    }

    @Override
    public void onClose(Site site) {
        Intent intent = new Intent();
        intent.putExtra("site", site);
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public void showError(String error) {
        //ToDo error handling
    }
}
