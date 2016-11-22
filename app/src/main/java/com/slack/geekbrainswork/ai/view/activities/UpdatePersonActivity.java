package com.slack.geekbrainswork.ai.view.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.UpdatePersonPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.Person;

import butterknife.ButterKnife;

/**
 * Created by Prilepishev Vadim on 22.11.2016.
 */

public class UpdatePersonActivity extends PersonActivity {

    private UpdatePersonPresenter presenter = new UpdatePersonPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        Person person = getPersonVo();

        setTitle(getTitleFromString());
        personNameTextView.setText(person.getName());

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

        presenter.onCreate(savedInstanceState, person);
    }

    @NonNull
    protected String getTitleFromString() {
        return getResources().getString(R.string.catalog_edit_title);
    }

    private Person getPersonVo() {
        return (Person) getIntent().getSerializableExtra("person");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

}
