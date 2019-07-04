package com.cliniva.enventory.utils.string;

import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StringSpanBuilder {

    private static List<SpanSection> spanSections;
    private static StringBuilder stringBuilder;

    private StringSpanBuilder() {
        // Disable class to not publicly instantiable
    }

    public static StringSpanBuilder getInstance() {

        stringBuilder = new StringBuilder();
        spanSections = new ArrayList<>();

        return new StringSpanBuilder();
    }

    public StringSpanBuilder append(String text) {
        spanSections.add(SpanSection.getInstance(text).setStartIndex(stringBuilder.length()));
        stringBuilder.append(text);
        return this;
    }

    public StringSpanBuilder append(SpanSection spanSection) {

        spanSections.add(spanSection.setStartIndex(stringBuilder.length()));
        stringBuilder.append(spanSection.getText());
        return this;
    }

    public StringSpanBuilder appendNewLine(){
        stringBuilder.append("\n");
        return this;
    }

    public void buildWithTextView(TextView textView) {
        textView.setText(build(), TextView.BufferType.SPANNABLE);
    }

    private SpannableStringBuilder build() {

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(stringBuilder.toString());

        for (SpanSection section : spanSections) {
            section.apply(spannableStringBuilder);
        }

        return spannableStringBuilder;
    }
}