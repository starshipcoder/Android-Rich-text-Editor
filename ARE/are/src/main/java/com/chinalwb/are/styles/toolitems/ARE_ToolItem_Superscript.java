package com.chinalwb.are.styles.toolitems;

import android.text.Editable;
import android.widget.ImageView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.R;
import com.chinalwb.are.spans.AreSuperscriptSpan;
import com.chinalwb.are.styles.IARE_Style;
import com.chinalwb.are.styles.toolitems.styles.ARE_Style_Superscript;

import androidx.annotation.DrawableRes;

/**
 * Created by wliu on 13/08/2018.
 */

public class ARE_ToolItem_Superscript extends ARE_ToolItem_Abstract {

    public ARE_ToolItem_Superscript() {
        mIcon = R.drawable.superscript;
    }

    public ARE_ToolItem_Superscript(@DrawableRes int icon) {
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
            IARE_ToolItem_Updater toolItemUpdater = getToolItemUpdater();
            mStyle = new ARE_Style_Superscript(editText, (ImageView) mToolItemView, toolItemUpdater);
        }
        return mStyle;
    }

    @Override
    public void onSelectionChanged(int selStart, int selEnd) {

		boolean superscriptExists = false;

		//
		// Two cases:
		// 1. Selection is just a pure cursor
		// 2. Selection is a range
		Editable editable = this.getEditText().getEditableText();
		if (selStart > 0 && selStart == selEnd) {
			AreSuperscriptSpan[] superscriptSpans = editable.getSpans(selStart - 1, selStart, AreSuperscriptSpan.class);
			if (superscriptSpans != null && superscriptSpans.length > 0) {
				superscriptExists = true;
			}
		} else {
            AreSuperscriptSpan[] superscriptSpans = editable.getSpans(selStart, selEnd, AreSuperscriptSpan.class);
            if (superscriptSpans != null && superscriptSpans.length > 0) {
                if (editable.getSpanStart(superscriptSpans[0]) <= selStart
                        && editable.getSpanEnd(superscriptSpans[0]) >= selEnd) {
                    superscriptExists = true;
                }
            }
        }

        mToolItemUpdater.onCheckStatusUpdate(superscriptExists);
    }
}
