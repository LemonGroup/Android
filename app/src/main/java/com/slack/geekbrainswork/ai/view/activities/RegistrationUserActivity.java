package com.slack.geekbrainswork.ai.view.activities;

<<<<<<< HEAD
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.slack.geekbrainswork.ai.R;
import com.slack.geekbrainswork.ai.presenter.UserAddPresenter;
import com.slack.geekbrainswork.ai.presenter.vo.User;

import butterknife.BindView;
import butterknife.ButterKnife;
=======
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.slack.geekbrainswork.ai.presenter.UserRegisterPresenter;
>>>>>>> origin/master

public class RegistrationUserActivity extends UserAddActivity {

    @Override
<<<<<<< HEAD
    public void onClose(final User user) {
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
                RegistrationUserActivity.super.onClose(user);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

//        Snackbar.make(layout, "Пользователь " + user.getName() + " успешно создан!", Snackbar.LENGTH_SHORT)
//                .setCallback(new Snackbar.Callback() {
//                    @Override
//                    public void onDismissed(Snackbar snackbar, int event) {
//                        super.onDismissed(snackbar, event);
//                        imageView.setVisibility(View.GONE);
//                        RegistrationUserActivity.super.onClose(user);
//                        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
//                    }
//                })
//                .show();
    }
=======
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
>>>>>>> origin/master
}
