package com.cliniva.enventory.widgets;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cliniva.enventory.R;

public class ClinivaDialog extends Dialog {

    private String TAG = ClinivaDialog.class.getName();

    private boolean isMarginAdded = false;
    private int contentBottomMargin;

    private TextView tvPositive;
    private TextView tvNegative;
    private TextView tvMessage;
//    private ImageView ivRemove;

    private RelativeLayout content;
//    @BindView(R.id.dialog_content) RelativeLayout dialog_content;

    private OnPositiveListener onPositiveListener;
    private OnNegativeListener onNegativeListener;

    public interface OnPositiveListener{
        void onPositive();
    }

    public interface OnNegativeListener{
        void onNegative();
    }

    public ClinivaDialog(Context context) {
        super(context);
        initDialog(context, null);
    }

    public ClinivaDialog(@NonNull Context context, View dialogView) {
        super(context);
        initDialog(context, dialogView);
    }

    private void initDialog(Context context, View dialogView) {

        setCanceledOnTouchOutside(false);

        contentBottomMargin = (int) context.getResources().getDimension(R.dimen.cliniva_dialog_content_bottom_margin);

        View dialogCliniva = LayoutInflater.from(context).inflate(R.layout.dialog_cliniva, null, false);

        tvPositive = dialogCliniva.findViewById(R.id.tv_positive);
        tvNegative = dialogCliniva.findViewById(R.id.tv_negative);
        tvMessage = dialogCliniva.findViewById(R.id.tv_message);
        content = dialogCliniva.findViewById(R.id.rl_content);

        if (dialogView != null){
            content.addView(dialogView);
        }

        setContentView(dialogCliniva);

        getWindow().getDecorView().setBackgroundColor(content.getResources().getColor(android.R.color.transparent));
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        tvPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onPositiveListener != null){
                    onPositiveListener.onPositive();
                }
            }
        });

        tvNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onNegativeListener != null){
                    Log.wtf(TAG, "initDialog: listener");
                    onNegativeListener.onNegative();
                } else {
                    Log.wtf(TAG, "initDialog: dismiss");
                    dismiss();
                }
            }
        });
//        addMarginToContentView();
    }

    public RelativeLayout getContent() {
        return content;
    }

    public ClinivaDialog setMessage(String message){

        tvMessage.setText(message);
        tvMessage.setVisibility(View.VISIBLE);
        return this;
    }

//    public ClinivaDialog showRemoveButton(){
//
//        ivRemove.setVisibility(View.VISIBLE);
//        ivRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//        return this;
//    }

    public ClinivaDialog setPositiveButton(String positiveButtonText, OnPositiveListener onPositiveListener) {

        tvPositive.setText(positiveButtonText);
        tvPositive.setVisibility(View.VISIBLE);

        this.onPositiveListener = onPositiveListener;

        addMarginToContentView();

        return this;
    }

    public ClinivaDialog setNegativeButton(String negativeButtonText) {

        setNegativeButton(negativeButtonText, null);
        return this;
    }

    public ClinivaDialog setNegativeButton(String negativeButtonText, OnNegativeListener onNegativeListener) {

        tvNegative.setText(negativeButtonText);
        tvNegative.setVisibility(View.VISIBLE);

        this.onNegativeListener = onNegativeListener;

        addMarginToContentView();

        return this;
    }

    private void addMarginToContentView() {

        if (!isMarginAdded) {

//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) content.getLayoutParams();
//            layoutParams.setMargins(0, 0, 0, contentBottomMargin);
//            layoutParams.bottomMargin = contentBottomMargin;
            content.setPadding(0, 0, 0, contentBottomMargin);
//            content.setLayoutParams(layoutParams);

            isMarginAdded = true;
        }
    }
}
