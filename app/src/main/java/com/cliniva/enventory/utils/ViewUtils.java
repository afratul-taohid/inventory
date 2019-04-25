package com.cliniva.enventory.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by taohid on 12/31/18
 * Email: taohid32@gmail.com
 */

public class ViewUtils {

    private static int DEFAULT_DURATION = 300; // 300 milli second

    public static Animation expand(final View v, int duration) {
        return expandView(v, duration);
    }

    public static Animation expand(final View v) {
        return expandView(v, DEFAULT_DURATION);
    }

    public static Animation collapse(final View v, int duration) {
        return collapseView(v, duration);
    }

    public static Animation collapse(final View v) {
        return collapseView(v, DEFAULT_DURATION);
    }

    private static Animation expandView(final View v, int duration){

        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        //duration = (int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density);
        a.setDuration(duration);
        v.startAnimation(a);

        return a;
    }

    private static Animation collapseView(final View v, int duration) {

        int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {

                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        //int duration = (int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density);
        a.setDuration(duration);
        v.startAnimation(a);

        return a;
    }

    public static void expandViewHorizontal(final View v, int duration){

        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int targetWidth = v.getMeasuredWidth();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().width = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().width =
                        interpolatedTime == 1 ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int)(targetWidth * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        //duration = (int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density);
        a.setDuration(duration);
        v.startAnimation(a);
    }

    public static void collapseViewHorizontal(final View v, int duration) {

        int initialWidth = v.getMeasuredWidth();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().width = initialWidth - (int)(initialWidth * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        //int duration = (int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density);
        a.setDuration(duration);
        v.startAnimation(a);
    }
}
