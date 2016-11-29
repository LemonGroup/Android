package com.slack.geekbrainswork.ai.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
<<<<<<< HEAD

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.UserAddPresenter;
import com.slack.geekbrainswork.ai.presenter.UserUpdatePresenter;
import com.slack.geekbrainswork.ai.presenter.vo.User;

import butterknife.ButterKnife;

public class UserAddActivity extends UserActivity{

    private UserAddPresenter presenter = new UserAddPresenter(this);
=======
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.UserAddPresenter;

import butterknife.ButterKnife;

public class UserAddActivity extends UserActivity {

    protected UserAddPresenter presenter = new UserAddPresenter(this);
>>>>>>> origin/master

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

<<<<<<< HEAD

        setTitle(getResources().getString(R.string.user_add_title));

=======
        setTitle(getResources().getString(R.string.user_add_title));

        isAdminSwitch.setEnabled(true);
>>>>>>> origin/master
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

        openKeyboard();
        presenter.onCreate(savedInstanceState);
    }

<<<<<<< HEAD
=======

    @Override
    public void onClose() {
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
                UserAddActivity.super.onClose();
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

>>>>>>> origin/master
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}
