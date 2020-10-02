package com.chinalwb.are.styles.toolitems;

import android.widget.ImageView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.R;
import com.chinalwb.are.styles.IARE_Style;
import com.chinalwb.are.styles.toolitems.styles.ARE_Style_Hr;

import androidx.annotation.DrawableRes;

/**
 * Created by wliu on 13/08/2018.
 */

public class ARE_ToolItem_Hr extends ARE_ToolItem_Abstract {

    public ARE_ToolItem_Hr() {
        mIcon = R.drawable.hr;
    }

    public ARE_ToolItem_Hr(@DrawableRes int icon) {
        mIcon = icon;
    }

    @Override
    public IARE_ToolItem_Updater getToolItemUpdater() {
        return null;
    }

    @Override
    public IARE_Style getStyle() {
        if (mStyle == null) {
            AREditText editText = this.getEditText();
            mStyle = new ARE_Style_Hr(editText, (ImageView) mToolItemView);
        }
        return mStyle;
    }

    @Override
    public void onSelectionChanged(int selStart, int selEnd) {
        return;
    }
}
