package com.amirsons.inventory.utils

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Taohid on 10, July, 2019
 * Email: taohid32@gmail.com
 */
object NestedRecyclerViewTouchListener{

    val mScrollTouchListener = object : RecyclerView.OnItemTouchListener {

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            val action = e.action
//            if (action == MotionEvent.ACTION_MOVE) {
                rv.parent.requestDisallowInterceptTouchEvent(true)
//            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }
}