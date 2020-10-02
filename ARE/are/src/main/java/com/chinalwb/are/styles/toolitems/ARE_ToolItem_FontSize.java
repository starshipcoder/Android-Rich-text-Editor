package com.chinalwb.are.styles.toolitems;

import android.widget.ImageView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.R;
import com.chinalwb.are.styles.IARE_Style;
import com.chinalwb.are.styles.toolitems.styles.ARE_Style_FontSize;

import androidx.annotation.DrawableRes;

public class ARE_ToolItem_FontSize extends ARE_ToolItem_Abstract {

    public ARE_ToolItem_FontSize() {
        mIcon = R.drawable.fontsize;
    }

    public ARE_ToolItem_FontSize(@DrawableRes int icon) {
        mIcon = icon;
    }

    @Override
    public IARE_Style getStyle() {
        if (mStyle == null) {
            AREditText editText = this.getEditText();
            mStyle = new ARE_Style_FontSize(editText, (ImageView) mToolItemView);
        }
        return mStyle;
    }

    @Override
    public void onSelectionChanged(int selStart, int selEnd) {
        return;
    }

    @Override
    public IARE_ToolItem_Updater getToolItemUpdater() {
        return null;
    }
}
