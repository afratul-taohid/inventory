package com.amirsons.inventory.utils.stringspan

import android.text.SpannableStringBuilder
import android.widget.TextView

// Disable class to not publicly instantiable
class StringSpanBuilder private constructor() {

    private var spanSections: ArrayList<SpanSection> = ArrayList()
    private var stringBuilder: StringBuilder = StringBuilder()

    companion object {
        @JvmStatic
        val instance: StringSpanBuilder
            get() = StringSpanBuilder()
    }

    fun append(text: String): StringSpanBuilder {
        spanSections.add(SpanSection.getInstance(text).setStartIndex(stringBuilder.length))
        stringBuilder.append(text)
        return this
    }

    fun append(spanSection: SpanSection): StringSpanBuilder {
        spanSections.add(spanSection.setStartIndex(stringBuilder.length))
        stringBuilder.append(spanSection.spanText)
        return this
    }

    fun appendNewLine(): StringSpanBuilder {
        stringBuilder.append("\n")
        return this
    }

    fun buildWithTextView(textView: TextView) {
        textView.setText(build(), TextView.BufferType.SPANNABLE)
    }

    private fun build(): SpannableStringBuilder {

        val spannableStringBuilder = SpannableStringBuilder(stringBuilder.toString())

        for (section in spanSections) {
            section.apply(spannableStringBuilder)
        }

        return spannableStringBuilder
    }
}