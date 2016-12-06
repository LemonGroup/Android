package com.slack.geekbrainswork.ai.view.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.SendPasswordPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendPasswordActivity extends AppCompatActivity implements SendPasswordView {
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.email_edit_text)
    EditText emailEditText;
    @BindView(R.id.cancel_button)
    Button cancelButton;
    @BindView(R.id.send_button)
    Button sendButton;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private SendPasswordPresenter presenter = new SendPasswordPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_password);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                finish();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSendButtonClick();
            }
        });

        openKeyboard();
        presenter.onCreate(savedInstanceState);
    }

    @Override
    public void showEmail(String email) {
        emailEditText.setText(email);
    }

    @Override
    public String getEmailTextViewText() {
        return emailEditText.getText().toString();
    }

    @Override
    public void showProgressBar(boolean show) {
        closeKeyboard();
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void openKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(emailEditText, InputMethodManager.SHOW_IMPLICIT);
    }

    private void closeKeyboard() {
        android.view.View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showError(String error) {
        closeKeyboard();
        makeToast(error);
    }

    protected void makeToast(String text) {
        Snackbar.make(layout, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void close() {
        final ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setVisibility(View.VISIBLE);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_image_in);
        imageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setVisibility(View.GONE);
                finish();
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}