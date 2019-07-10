package com.amirsons.inventory.ui.widgets

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.InsetDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.amirsons.inventory.R
import com.amirsons.inventory.utils.MyUtils
import kotlinx.android.synthetic.main.dialog_cliniva.view.*

class MyDialog : Dialog {

    private val TAG = MyDialog::class.java.name

    private var isMarginAdded = false
    private var contentBottomMargin: Int = 0

    private var onPositiveListener: OnPositiveListener? = null
    private var onNegativeListener: OnNegativeListener? = null

    private var margin = 30

    var rootView: View? = null

    constructor(context: Context) : super(context) {
        initDialog(null)
    }

    constructor(context: Context, dialogView: View) : super(context) {
        initDialog(dialogView)
    }

    constructor(context: Context, dialogView: View, margin: Int) : super(context) {
        this.margin = margin
        initDialog(dialogView)
    }

    @SuppressLint("InflateParams")
    private fun initDialog(dialogView: View?) {

        setCanceledOnTouchOutside(false)
        contentBottomMargin = context.resources.getDimension(R.dimen.cliniva_dialog_content_bottom_margin).toInt()

        rootView = LayoutInflater.from(context).inflate(R.layout.dialog_cliniva, null, false)
        setContentView(rootView!!)
        setDialogCorner()

        if (dialogView != null) {
            rootView?.content?.addView(dialogView)
        }

        rootView?.tv_positive?.setOnClickListener {
            onPositiveListener?.onPositive()
        }

        rootView?.tv_negative?.setOnClickListener {

            if (onNegativeListener != null) {
                onNegativeListener?.onNegative()
            } else {
                dismiss()
            }
        }
    }

    private fun setDialogCorner() {

        val cornerDrawable = context.resources.getDrawable(R.drawable.background_dialog_cliniva)
        val marginPixel = MyUtils.convertDpToPixel(margin.toFloat(), context)

        val inset = InsetDrawable(cornerDrawable, marginPixel)

        if (window != null) {

            window!!.setBackgroundDrawable(inset)
            window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    fun setMessage(message: String): MyDialog {

        rootView?.tv_message?.text = message
        rootView?.tv_message?.visibility = View.VISIBLE
        return this
    }

    fun hideButtonView() {

        rootView?.rl_button_view?.visibility = View.GONE

        isMarginAdded = false
        addMarginToContentView(0)
    }

    fun showButtonView() {

        rootView?.rl_button_view?.visibility = View.VISIBLE

        isMarginAdded = false
        addMarginToContentView(contentBottomMargin)
    }

    fun showRemoveButton(): MyDialog {
        rootView?.iv_remove?.visibility = View.VISIBLE
        rootView?.iv_remove?.setOnClickListener { dismiss() }
        return this
    }

    fun setPositiveButton(positiveButtonText: String, onPositiveListener: OnPositiveListener): MyDialog {

        rootView?.tv_positive?.text = positiveButtonText
        rootView?.tv_positive?.visibility = View.VISIBLE

        this.onPositiveListener = onPositiveListener

        addMarginToContentView(contentBottomMargin)

        return this
    }

    fun setNegativeButton(negativeButtonText: String): MyDialog {
        setNegativeButton(negativeButtonText, null)
        return this
    }

    fun setNegativeButton(negativeButtonText: String, onNegativeListener: OnNegativeListener?): MyDialog {

        rootView?.tv_negative?.text = negativeButtonText
        rootView?.tv_negative?.visibility = View.VISIBLE

        this.onNegativeListener = onNegativeListener

        addMarginToContentView(contentBottomMargin)

        return this
    }

    interface OnPositiveListener {
        fun onPositive()
    }

    interface OnNegativeListener {
        fun onNegative()
    }

    private fun addMarginToContentView(contentBottomMargin: Int) {

        if (!isMarginAdded) {

            //            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) content.getLayoutParams();
            //            layoutParams.setMargins(0, 0, 0, contentBottomMargin);
            //            layoutParams.bottomMargin = contentBottomMargin;
            rootView?.content?.setPadding(0, 0, 0, contentBottomMargin)
            rootView?.content?.clipToPadding = false
            //            content.setLayoutParams(layoutParams);

            isMarginAdded = true
        }
    }
}
