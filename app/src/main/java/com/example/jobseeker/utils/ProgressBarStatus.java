package com.example.jobseeker.utils;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.jobseeker.R;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class ProgressBarStatus {
    public static void errorFlash(LinearProgressIndicator linearProgressIndicator, Context context){
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), ColorEx.RED, ColorEx.WHITE,
                ColorEx.RED, ColorEx.WHITE);

        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(animation -> {
            linearProgressIndicator.setTrackColor((int)animation.getAnimatedValue());
            linearProgressIndicator.setIndicatorColor((int)animation.getAnimatedValue());
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                linearProgressIndicator.setIndicatorColor(ColorEx.JOB_SEEKER_GREEN);
                linearProgressIndicator.setTrackColor(ContextCompat.getColor(context, R.color.backgroundColor));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        valueAnimator.start();
    }

    public static void successFlash(LinearProgressIndicator linearProgressIndicator, Context context){
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), ColorEx.JOB_SEEKER_GREEN, ColorEx.WHITE,
                ColorEx.JOB_SEEKER_GREEN, ColorEx.WHITE);

        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(animation -> {

            linearProgressIndicator.setTrackColor((int)animation.getAnimatedValue());
            linearProgressIndicator.setIndicatorColor((int)animation.getAnimatedValue());

        });

        valueAnimator.start();

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                linearProgressIndicator.setIndicatorColor(ColorEx.JOB_SEEKER_GREEN);
                linearProgressIndicator.setTrackColor(ContextCompat.getColor(context, R.color.backgroundColor));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
