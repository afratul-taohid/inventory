package com.amirsons.inventory.ui.base

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.amirsons.inventory.R
import com.amirsons.inventory.event.OnActivityResultListener
import com.amirsons.inventory.utils.MyUtils
import com.amirsons.inventory.ui.widgets.MySnackBar
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.action_bar.view.*
import java.util.concurrent.atomic.AtomicInteger


/**
 * Created by Taohid on 06, July, 2019
 * Email: taohid32@gmail.com
 */

abstract class BaseFragment : Fragment() {

    @get:LayoutRes
    abstract val contentLayout: Int

    private val activityResultListenerSparseArray = SparseArray<OnActivityResultListener>()
    private val counter = AtomicInteger(0)

    private lateinit var progressDialog: ProgressDialog

    @get:JvmName("context") protected lateinit var context: Context
        private set

    lateinit var TAG: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(contentLayout, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set the local class name as TAG for use log
        TAG = javaClass.name

        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Please wait...")
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
    }

    fun showProgressDialog() {
        hideProgressDialog()
        progressDialog.show()
    }

    fun showProgressDialog(message: String) {
        hideProgressDialog()
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    fun hideProgressDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    open fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showSnackMessage(message: String, view: View) {
        MySnackBar.instance.setMessage(message).show(view)
    }

    fun showSnackMessage(message: String, color: Int, view: View) {
        MySnackBar.instance.setMessage(message).setTextColor(color).show(view)
    }

    protected fun printObject(`object`: Any) {
        Log.wtf(TAG, MyUtils.objectPrettyPrint(`object`))
    }

    override fun onResume() {
        super.onResume()
        hideProgressDialog()
    }

    override fun onPause() {
        hideProgressDialog()
        super.onPause()
    }

    fun startActivityForResult(intent: Intent, resultListener: OnActivityResultListener) {
        val requestCode = counter.incrementAndGet()
        activityResultListenerSparseArray.put(requestCode, resultListener)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val resultListener = activityResultListenerSparseArray.get(requestCode)

        if (resultListener != null) {

            if (resultCode == RESULT_OK) {
                resultListener.onResultSuccess(resultCode, data)
            } else {
                resultListener.onResultError(resultCode, data)
            }
        }
    }

    protected fun getStringFromResources(@StringRes id: Int): String {
        return resources.getString(id)
    }

    fun setToolbar(view: View, title: String, isElevation: Boolean) {

        view.toolbar.tv_action_title.text = title
    }
}
