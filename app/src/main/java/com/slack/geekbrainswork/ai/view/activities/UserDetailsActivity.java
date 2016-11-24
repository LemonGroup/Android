package com.slack.geekbrainswork.ai.view.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.vo.User;

import butterknife.BindView;

public abstract class UserDetailsActivity extends AppCompatActivity implements UserDetailsView{
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.login_edit_text)
    EditText loginEditText;
    @BindView(R.id.password_edit_text)
    EditText passwordEditText;
    @BindView(R.id.password2_edit_text)
    EditText password2EditText;
    @BindView(R.id.email_edit_text)
    EditText emailEditText;
    @BindView(R.id.cancel_button)
    Button cancelButton;
    @BindView(R.id.save_button)
    Button saveButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public String getUserNameTextViewText() {
        return loginEditText.getText().toString();
    }

    @Override
    public String getPasswordTextViewText() {
        return passwordEditText.getText().toString();
    }

    @Override
    public String getPassword2TextViewText() {
        return password2EditText.getText().toString();
    }

    @Override
    public String getEmailTextViewText() {
        return emailEditText.getText().toString();
    }

    @Override
    public void onClose(User user) {
        Intent intent = new Intent();
        intent.putExtra("user", user);
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
        imm.showSoftInput(loginEditText, InputMethodManager.SHOW_IMPLICIT);
    }

    protected void closeKeyboard() {
        android.view.View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void makeToast(String text) {
        Snackbar.make(layout, text, Snackbar.LENGTH_LONG).show();
    }
}
