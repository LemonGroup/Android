package com.slack.geekbrainswork.ai.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.UpdateSitePresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateSiteActivity extends AppCompatActivity implements SiteDetailsView {

    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.site_name_edit_text)
    EditText siteNameTextView;
    @BindView(R.id.cancel_button)
    Button cancelButton;
    @BindView(R.id.save_button)
    Button saveButton;

    private UpdateSitePresenter presenter = new UpdateSitePresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        ButterKnife.bind(this);

        Site site = getSiteVo();

        setTitle(getTitleFromString());
        siteNameTextView.setText(site.getName());

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
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

    @NonNull
    private String getTitleFromString() {
        return getResources().getString(R.string.catalog_edit_title);
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
        closeKeyboard();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showError(String error) {
        closeKeyboard();
        makeToast(error);
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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
