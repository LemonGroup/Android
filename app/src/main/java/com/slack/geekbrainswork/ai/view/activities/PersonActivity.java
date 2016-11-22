package com.slack.geekbrainswork.ai.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.vo.Person;

import butterknife.BindView;

public abstract class PersonActivity extends AppCompatActivity implements PersonView {
    @BindView(R.id.person_activity_layout)
    LinearLayout personActivityLayout;
    @BindView(R.id.person_name_edit_text)
    EditText personNameTextView;
    @BindView(R.id.cancel_button_person)
    Button cancelButton;
    @BindView(R.id.save_button_person)
    Button saveButton;

    @NonNull
    protected abstract String getTitleFromString() ;

    @Override
    public String getPersonNameTextViewText() {
        return personNameTextView.getText().toString();
    }

    @Override
    public void onClose(Person person) {
        Intent intent = new Intent();
        intent.putExtra("person", person);
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
        imm.showSoftInput(personNameTextView, InputMethodManager.SHOW_IMPLICIT);
    }

    protected void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void makeToast(String text) {
        Snackbar.make(personActivityLayout, text, Snackbar.LENGTH_LONG).show();
    }

}
