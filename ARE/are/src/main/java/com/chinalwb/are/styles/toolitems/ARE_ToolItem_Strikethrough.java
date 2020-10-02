package com.chinalwb.are.styles.toolitems;

import android.text.Editable;
import android.text.style.CharacterStyle;
import android.text.style.StrikethroughSpan;
import android.widget.ImageView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.R;
import com.chinalwb.are.styles.IARE_Style;
import com.chinalwb.are.styles.toolitems.styles.ARE_Style_Strikethrough;

import androidx.annotation.DrawableRes;

/**
 * Created by wliu on 13/08/2018.
 */

public class ARE_ToolItem_Strikethrough extends ARE_ToolItem_Abstract {

    public ARE_ToolItem_Strikethrough() {
        mIcon = R.drawable.strikethrough;
    }

    public ARE_ToolItem_Strikethrough(@DrawableRes int icon) {
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
            mStyle = new ARE_Style_Strikethrough(editText, (ImageView) mToolItemView, toolItemUpdater);
        }
        return mStyle;
    }

    @Override
    public void onSelectionChanged(int selStart, int selEnd) {
        boolean strikethroughExists = false;

        AREditText editText = this.getEditText();
        Editable editable = editText.getEditableText();
        if (selStart > 0 && selStart == selEnd) {
            CharacterStyle[] styleSpans = editable.getSpans(selStart - 1, selStart, CharacterStyle.class);

            for (int i = 0; i < styleSpans.length; i++) {
                if (styleSpans[i] instanceof StrikethroughSpan) {
					strikethroughExists = true;
				}
            }
        } else {
			//
			// Selection is a range
			CharacterStyle[] styleSpans = editable.getSpans(selStart, selEnd, CharacterStyle.class);

			for (int i = 0; i < styleSpans.length; i++) {
				if (styleSpans[i] instanceof StrikethroughSpan) {
					if (editable.getSpanStart(styleSpans[i]) <= selStart
							&& editable.getSpanEnd(styleSpans[i]) >= selEnd) {
						strikethroughExists = true;
					}
				}
			}
		}

        mToolItemUpdater.onCheckStatusUpdate(strikethroughExists);
    }
}
