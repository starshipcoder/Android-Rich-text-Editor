package com.chinalwb.are.styles.toolitems;

import android.text.Editable;
import android.text.style.BackgroundColorSpan;
import android.widget.ImageView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.Constants;
import com.chinalwb.are.R;
import com.chinalwb.are.styles.IARE_Style;
import com.chinalwb.are.styles.toolitems.styles.ARE_Style_BackgroundColor;

import androidx.annotation.DrawableRes;

public class ARE_ToolItem_BackgroundColor extends ARE_ToolItem_Abstract {

    public ARE_ToolItem_BackgroundColor() {
        mIcon = R.drawable.background;
    }

    public ARE_ToolItem_BackgroundColor(@DrawableRes int icon) {
        mIcon = icon;
    }

    @Override
    public IARE_ToolItem_Updater getToolItemUpdater() {
        if (mToolItemUpdater == null) {
            mToolItemUpdater = new ARE_ToolItem_UpdaterDefault(this, mIconBackground, mIconSize);
            setToolItemUpdater(mToolItemUpdater);
        }
        return mToolItemUpdater;
    }


    @Override
    public IARE_Style getStyle() {
        if (mStyle == null) {
            AREditText editText = this.getEditText();
            mStyle = new ARE_Style_BackgroundColor(editText, (ImageView) mToolItemView, getToolItemUpdater(), Constants.COLOR_BACKGROUND_DEFAULT);
        }
        return mStyle;
    }

    @Override
    public void onSelectionChanged(int selStart, int selEnd) {
        boolean backgroundColorExists = false;

        AREditText editText = this.getEditText();
        Editable editable = editText.getEditableText();
        if (selStart > 0 && selStart == selEnd) {
            BackgroundColorSpan[] backgroundColorSpans = editable.getSpans(selStart - 1, selStart, BackgroundColorSpan.class);
            if (backgroundColorSpans != null && backgroundColorSpans.length > 0) {
                backgroundColorExists = true;
            }
        } else {
            BackgroundColorSpan[] backgroundColorSpans = editable.getSpans(selStart, selEnd, BackgroundColorSpan.class);
            if (backgroundColorSpans != null && backgroundColorSpans.length > 0) {
                if (editable.getSpanStart(backgroundColorSpans[0]) <= selStart
                        && editable.getSpanEnd(backgroundColorSpans[0]) >= selEnd) {
                    backgroundColorExists = true;
                }
            }
        }

        mToolItemUpdater.onCheckStatusUpdate(backgroundColorExists);
    }

}
