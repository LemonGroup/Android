package com.slack.geekbrainswork.ai.view.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.LoginActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.email)
    AutoCompleteTextView emailView;
    @BindView(R.id.password)
    EditText passwordView;
    @BindView(R.id.login_progress)
    View progressView;
    @BindView(R.id.login_form)
    View loginFormView;
    @BindView(R.id.sign_in_button)
    Button signInButton;
    @BindView(R.id.registration_button)
    Button regButton;
    @BindView(R.id.forgot_pass_text_view)
    TextView forgotPassTextView;

    private LoginActivityPresenter presenter = new LoginActivityPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        forgotPassTextView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        presenter.attemptLoginByToken();
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    presenter.onSignInButtonClick(emailView.getText().toString(), passwordView.getText().toString());
                    return true;
                }
                return false;
            }
        });

        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignInButtonClick(emailView.getText().toString(), passwordView.getText().toString());
            }
        });

        regButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRegButtonClick();
            }
        });
    }

    @Override
    public void resetErrors() {
        emailView.setError(null);
        passwordView.setError(null);
    }

    @Override
    public void showAuthorizationError(String message) {
        Snackbar.make(loginFormView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showPasswordError() {
        passwordView.setError(getString(R.string.error_invalid_password));
        passwordView.requestFocus();
    }

    @Override
    public void showLoginError() {
        emailView.setError(getString(R.string.error_invalid_email));
        emailView.requestFocus();
    }

    @Override
    public void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        hideKeyboard();

        loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        loginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        progressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void navigateToMainView() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToRegistrationUserView() {
        Intent intent = new Intent(this, RegistrationUserActivity.class);
        startActivity(intent);
    }
}

