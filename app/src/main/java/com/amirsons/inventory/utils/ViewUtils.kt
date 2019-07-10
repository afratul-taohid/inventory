package com.amirsons.inventory.utils

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 * Created by taohid on 12/31/18
 * Email: taohid32@gmail.com
 */

object ViewUtils {

    private val DEFAULT_DURATION = 300 // 300 milli second

    fun expand(v: View, duration: Int): Animation {
        return expandView(v, duration)
    }

    fun expand(v: View): Animation {
        return expandView(v, DEFAULT_DURATION)
    }

    fun collapse(v: View, duration: Int): Animation {
        return collapseView(v, duration)
    }

    fun collapse(v: View): Animation {
        return collapseView(v, DEFAULT_DURATION)
    }

    private fun expandView(v: View, duration: Int): Animation {

        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val targetHeight = v.measuredHeight

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.layoutParams.height = 0
        v.visibility = View.VISIBLE

        val animation = object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                v.layoutParams.height = if (interpolatedTime == 1f)
                    ViewGroup.LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        //duration = (int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density);
        animation.duration = duration.toLong()
        v.startAnimation(animation)

        return animation
    }

    private fun collapseView(v: View, duration: Int): Animation {

        val initialHeight = v.measuredHeight

        val animation = object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {

                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        //int duration = (int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density);
        animation.duration = duration.toLong()
        v.startAnimation(animation)

        return animation
    }

    fun expandViewHorizontal(v: View, duration: Int) {

        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val targetWidth = v.measuredWidth

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.layoutParams.width = 1
        v.visibility = View.VISIBLE

        val animation = object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                v.layoutParams.width = if (interpolatedTime == 1f)
                    ViewGroup.LayoutParams.WRAP_CONTENT
                else
                    (targetWidth * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        //duration = (int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density);
        animation.duration = duration.toLong()
        v.startAnimation(animation)
    }

    fun collapseViewHorizontal(v: View, duration: Int) {

        val initialWidth = v.measuredWidth

        val animation = object : Animation() {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.width = initialWidth - (initialWidth * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        //int duration = (int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density);
        animation.duration = duration.toLong()
        v.startAnimation(animation)
    }
}
