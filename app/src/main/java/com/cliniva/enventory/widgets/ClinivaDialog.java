package com.cliniva.enventory.widgets;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cliniva.enventory.R;
import com.cliniva.enventory.databinding.DialogClinivaBinding;
import com.cliniva.enventory.utils.MyUtils;

public class ClinivaDialog extends Dialog {

    private String TAG = ClinivaDialog.class.getName();

    private boolean isMarginAdded = false;
    private int contentBottomMargin;

    private DialogClinivaBinding binding;

    private OnPositiveListener onPositiveListener;
    private OnNegativeListener onNegativeListener;

    private int margin = 30;

    public ClinivaDialog(Context context) {
        super(context);
        initDialog(null);
    }

    public ClinivaDialog(@NonNull Context context, View dialogView) {
        super(context);
        initDialog(dialogView);
    }

    public ClinivaDialog(@NonNull Context context, View dialogView, int margin) {
        super(context);
        this.margin = margin;
        initDialog(dialogView);
    }

    @SuppressLint("InflateParams")
    private void initDialog(View dialogView) {

        setCanceledOnTouchOutside(false);
        contentBottomMargin = (int) getContext().getResources().getDimension(R.dimen.cliniva_dialog_content_bottom_margin);

        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout. dialog_cliniva, null, false);
        View dialogCliniva = binding.getRoot();

        setContentView(dialogCliniva);
        setDialogCorner();

        if (dialogView != null){
            binding.content.addView(dialogView);
        }

        binding.tvPositive.setOnClickListener(v -> {

            if (onPositiveListener != null){
                onPositiveListener.onPositive();
            }
        });

        binding.tvNegative.setOnClickListener(v -> {

            if (onNegativeListener != null){
                onNegativeListener.onNegative();
            } else {
                dismiss();
            }
        });
    }

    private void setDialogCorner() {

        Drawable cornerDrawable = getContext().getResources().getDrawable(R.drawable.background_dialog_cliniva);
        int marginPixel = MyUtils.convertDpToPixel(margin, getContext());

        InsetDrawable inset = new InsetDrawable(cornerDrawable, marginPixel);

        if (getWindow() != null){

            getWindow().setBackgroundDrawable(inset);
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    public RelativeLayout getContent() {
        return binding.content;
    }

    public ClinivaDialog setMessage(String message){

        binding.tvMessage.setText(message);
        binding.tvMessage.setVisibility(View.VISIBLE);
        return this;
    }

    public void hideButtonView(){

        binding.rlButtonView.setVisibility(View.GONE);

        isMarginAdded = false;
        addMarginToContentView(0);
    }

    public void showButtonView(){

        binding.rlButtonView.setVisibility(View.VISIBLE);

        isMarginAdded = false;
        addMarginToContentView(contentBottomMargin);
    }

    public ClinivaDialog showRemoveButton(){
        binding.ivRemove.setVisibility(View.VISIBLE);
        binding.ivRemove.setOnClickListener(v -> dismiss());
        return this;
    }

    public ClinivaDialog setPositiveButton(String positiveButtonText, OnPositiveListener onPositiveListener) {

        binding.tvPositive.setText(positiveButtonText);
        binding.tvPositive.setVisibility(View.VISIBLE);

        this.onPositiveListener = onPositiveListener;

        addMarginToContentView(contentBottomMargin);

        return this;
    }

    public ClinivaDialog setNegativeButton(String negativeButtonText) {
        setNegativeButton(negativeButtonText, null);
        return this;
    }

    public ClinivaDialog setNegativeButton(String negativeButtonText, OnNegativeListener onNegativeListener) {

        binding.tvNegative.setText(negativeButtonText);
        binding.tvNegative.setVisibility(View.VISIBLE);

        this.onNegativeListener = onNegativeListener;

        addMarginToContentView(contentBottomMargin);

        return this;
    }

    public interface OnPositiveListener{
        void onPositive();
    }

    public interface OnNegativeListener{
        void onNegative();
    }

    private void addMarginToContentView(int contentBottomMargin) {

        if (!isMarginAdded) {

//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) content.getLayoutParams();
//            layoutParams.setMargins(0, 0, 0, contentBottomMargin);
//            layoutParams.bottomMargin = contentBottomMargin;
            binding.content.setPadding(0, 0, 0, contentBottomMargin);
            binding.content.setClipToPadding(false);
//            content.setLayoutParams(layoutParams);

            isMarginAdded = true;
        }
    }
}
