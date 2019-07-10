package com.amirsons.inventory.ui.widgets

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.amirsons.inventory.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

abstract class MyBottomSheetDialog(context: Context) : BottomSheetDialog(context, R.style.BottomSheetDialog){

    @get:LayoutRes
    abstract val getContentLayout : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentLayout)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}
