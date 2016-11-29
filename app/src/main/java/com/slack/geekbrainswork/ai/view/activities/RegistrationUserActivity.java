package com.slack.geekbrainswork.ai.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.slack.geekbrainswork.ai.presenter.UserRegisterPresenter;

public class RegistrationUserActivity extends UserAddActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = new UserRegisterPresenter(this);
        super.onCreate(savedInstanceState);
        isAdminSwitch.setVisibility(View.INVISIBLE);
    }

//    @Override
//    public void onClose() {
//        final ImageView imageView = (ImageView) findViewById(R.id.image);
//        imageView.setVisibility(View.VISIBLE);
//
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_image_in);
//        imageView.startAnimation(animation);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                imageView.setVisibility(View.GONE);
//                RegistrationUserActivity.super.onClose();
//                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//    }
}
