package com.amirsons.inventory.utils

import android.os.SystemClock
import android.view.View

abstract class OnSingleClickListener : View.OnClickListener {

    /**
     * last click time
     */
    private var mLastClickTime: Long = 0

    /**
     * click listener
     * @param v The view that was clicked.
     */
    abstract fun onSingleClick(v: View)

    override fun onClick(v: View) {

        val currentClickTime = SystemClock.uptimeMillis()

        val elapsedTime = currentClickTime - mLastClickTime

        mLastClickTime = currentClickTime

        //Log.wtf("clickListener", "onItemClick: "+ currentClickTime+"--"+elapsedTime);

        if (elapsedTime <= MIN_CLICK_INTERVAL) {
            return
        }

        onSingleClick(v)
    }

    companion object {

        /**
         * click interval
         */
        private val MIN_CLICK_INTERVAL: Long = 600
    }

}

