package com.cliniva.enventory.utils.string;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.cliniva.enventory.R;

public class SpanSection {

    private int startIndex;
    private String text;
    private Integer typeface;
    private Float textSize;
    private Integer textColor;

    private Drawable drawableRes;

    public static final float TEXT_SIZE_SMALL = .9f;
    public static final float TEXT_SIZE_NORMAL = 1f;
    public static final float TEXT_SIZE_MEDIUM = 1.2f;
    public static final float TEXT_SIZE_LARGE = 1.5f;
    public static final float TEXT_SIZE_EXTRA_LARGE = 2f;

    public static final int STYLE_NORMAL = Typeface.NORMAL;
    public static final int STYLE_BOLD = Typeface.BOLD;
    public static final int STYLE_BOLD_ITALIC = Typeface.BOLD_ITALIC;
    public static final int STYLE_ITALIC = Typeface.ITALIC;

    public static SpanSection getInstance(String text){
        return new SpanSection(text);
    }

    private SpanSection(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public SpanSection setStartIndex(int startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    public SpanSection setText(String text) {
        this.text = text;
        return this;
    }

    public SpanSection setTextColor(Integer textColor) {
        this.textColor = textColor;
        return this;
    }

    public SpanSection setTextSize(Float textSize) {
        this.textSize = textSize;
        return this;
    }

    public SpanSection setTypeface(Integer typeface) {
        this.typeface = typeface;
        return this;
    }

    public SpanSection setDrawable(Drawable drawable) {
        this.drawableRes = drawable;
        return this;
    }

    public void apply(SpannableStringBuilder spanStringBuilder) {

        if (spanStringBuilder == null) return;

        // set the text color
        if (textColor != null){
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(textColor);
            spanStringBuilder.setSpan(colorSpan, startIndex, startIndex + text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        // set the text size
        if (textSize != null){
            RelativeSizeSpan sizeSpan = new RelativeSizeSpan(textSize);
            spanStringBuilder.setSpan(sizeSpan, startIndex, startIndex + text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            RelativeSizeSpan sizeSpan = new RelativeSizeSpan(TEXT_SIZE_NORMAL);
            spanStringBuilder.setSpan(sizeSpan, startIndex, startIndex + text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        // set the font style
        if (typeface != null){
            StyleSpan styleSpan = new StyleSpan(typeface);
            spanStringBuilder.setSpan(styleSpan, startIndex, startIndex + text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        if (drawableRes != null){
            drawableRes.setBounds(0, 0, drawableRes.getIntrinsicWidth(), drawableRes.getIntrinsicHeight());
            ImageSpan imageSpan = new ImageSpan(drawableRes, ImageSpan.ALIGN_BASELINE);
            spanStringBuilder.setSpan(imageSpan, startIndex, startIndex + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }
    }
}