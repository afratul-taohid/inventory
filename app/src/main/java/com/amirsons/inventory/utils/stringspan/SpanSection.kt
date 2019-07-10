package com.amirsons.inventory.utils.stringspan

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan

class SpanSection private constructor(var spanText: String) {

    companion object {

        @JvmStatic val TEXT_SIZE_SMALL = .9f
        @JvmStatic val TEXT_SIZE_NORMAL = 1f
        @JvmStatic val TEXT_SIZE_MEDIUM = 1.2f
        @JvmStatic val TEXT_SIZE_LARGE = 1.7f
        @JvmStatic val TEXT_SIZE_EXTRA_LARGE = 2f

        @JvmStatic val STYLE_NORMAL = Typeface.NORMAL
        @JvmStatic val STYLE_BOLD = Typeface.BOLD
        @JvmStatic val STYLE_BOLD_ITALIC = Typeface.BOLD_ITALIC
        @JvmStatic val STYLE_ITALIC = Typeface.ITALIC

        @JvmStatic fun getInstance(text: String): SpanSection {
            return SpanSection(text)
        }
    }

    private var startIndex: Int = 0
    private var typeface: Int? = null
    private var textSize: Float? = null
    private var textColor: Int? = null

    private var drawableRes: Drawable? = null

    fun setStartIndex(startIndex: Int): SpanSection {
        this.startIndex = startIndex
        return this
    }

    fun setText(text: String): SpanSection {
        this.spanText = text
        return this
    }

    fun setTextColor(textColor: Int?): SpanSection {
        this.textColor = textColor
        return this
    }

    fun setTextSize(textSize: Float?): SpanSection {
        this.textSize = textSize
        return this
    }

    fun setTypeface(typeface: Int?): SpanSection {
        this.typeface = typeface
        return this
    }

    fun setDrawable(drawable: Drawable): SpanSection {
        this.drawableRes = drawable
        return this
    }

    fun apply(spanStringBuilder: SpannableStringBuilder?) {

        if (spanStringBuilder == null) return

        // set the text color
        if (textColor != null) {
            val colorSpan = ForegroundColorSpan(textColor!!)
            spanStringBuilder.setSpan(colorSpan, startIndex, startIndex + spanText.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }

        // set the text size
        if (textSize != null) {
            val sizeSpan = RelativeSizeSpan(textSize!!)
            spanStringBuilder.setSpan(sizeSpan, startIndex, startIndex + spanText.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        } else {
            val sizeSpan = RelativeSizeSpan(TEXT_SIZE_NORMAL)
            spanStringBuilder.setSpan(sizeSpan, startIndex, startIndex + spanText.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }

        // set the font style
        if (typeface != null) {
            val styleSpan = StyleSpan(typeface!!)
            spanStringBuilder.setSpan(styleSpan, startIndex, startIndex + spanText.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }

        if (drawableRes != null) {
            drawableRes!!.setBounds(0, 0, drawableRes!!.intrinsicWidth, drawableRes!!.intrinsicHeight)
            val imageSpan = ImageSpan(drawableRes!!, ImageSpan.ALIGN_BASELINE)
            spanStringBuilder.setSpan(imageSpan, startIndex, startIndex + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
    }
}