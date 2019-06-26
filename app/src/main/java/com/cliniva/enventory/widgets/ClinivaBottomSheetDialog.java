package com.cliniva.enventory.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;

import com.cliniva.enventory.R;

public class ClinivaBottomSheetDialog extends BottomSheetDialog {
    public ClinivaBottomSheetDialog(@NonNull Context context) {
        super(context, R.style.BottomSheetDialog);
    }
}
