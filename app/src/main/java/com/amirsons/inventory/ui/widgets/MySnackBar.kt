package com.amirsons.inventory.ui.widgets

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.amirsons.inventory.R
import com.google.android.material.snackbar.Snackbar

class MySnackBar {

    private var message: String = ""
    private var TIME_OUT = Snackbar.LENGTH_LONG
    private var color = Color.GREEN
    private var actionTextColor = Color.GREEN
    private var actionText = "Dismiss"

    private var onClickListener: View.OnClickListener? = null

    fun setMessage(message: String): MySnackBar {
        this.message = message
        return this
    }

    fun setTextColor(color: Int): MySnackBar {
        this.color = color
        return this
    }

    fun setTimeout(timeout: Int): MySnackBar {
        this.TIME_OUT = timeout
        return this
    }

    fun setActionText(actionText: String): MySnackBar {
        this.actionText = actionText
        return this
    }

    fun setActionTextColor(actionTextColor: Int): MySnackBar {
        this.actionTextColor = actionTextColor
        return this
    }

    fun setActionClickListener(onClickListener: View.OnClickListener): MySnackBar {
        this.onClickListener = onClickListener
        return this
    }

    fun show(activity: AppCompatActivity) {
        val snackView = activity.window.decorView.findViewById<View>(android.R.id.content)
        show(snackView)
    }

    fun show(snackView: View) {

        val snackBar = Snackbar.make(snackView, message, TIME_OUT)
                .setActionTextColor(actionTextColor)
                .setAction(actionText) { v ->
                    onClickListener?.onClick(v)
                }

        val sbView = snackBar.view
        val textView = sbView.findViewById<TextView>(R.id.snackbar_text)
        textView.setTextColor(color)

        snackBar.show()
    }

    companion object {
        @JvmStatic val instance = MySnackBar()
    }
}
