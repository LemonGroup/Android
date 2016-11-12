package com.slack.geekbrainswork.ai.view.activities;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.vo.Site;

import butterknife.BindView;

public abstract class SiteActivity extends AppCompatActivity implements SiteView {
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.site_name_edit_text)
    EditText siteNameTextView;
    @BindView(R.id.cancel_button)
    Button cancelButton;
    @BindView(R.id.save_button)
    Button saveButton;

    @NonNull
    protected abstract String getTitleFromString() ;

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

    protected void openKeyboard() {
        InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(siteNameTextView, InputMethodManager.SHOW_IMPLICIT);
    }

    protected void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void makeToast(String text) {
        Snackbar.make(layout, text, Snackbar.LENGTH_LONG).show();
    }
}
