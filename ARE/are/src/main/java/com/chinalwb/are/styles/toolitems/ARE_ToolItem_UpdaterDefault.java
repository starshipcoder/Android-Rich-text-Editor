package com.chinalwb.are.styles.toolitems;

import android.view.View;

import com.chinalwb.are.R;
import com.chinalwb.are.styles.IARE_Style;

/**
 * The default tool item check status updater.
 */
public class ARE_ToolItem_UpdaterDefault implements IARE_ToolItem_Updater {

    private IARE_ToolItem mToolItem;

    public ARE_ToolItem_UpdaterDefault(IARE_ToolItem toolItem, int checkedColor, int uncheckedColor) {
        mToolItem = toolItem;
    }

    @Override
    public void onCheckStatusUpdate(boolean checked) {
        IARE_Style areStyle = mToolItem.getStyle();
        areStyle.setChecked(checked);
        View view = mToolItem.getView(null);
        view.setBackgroundResource(R.drawable.bg_icon);

        view.setSelected(checked);
    }
}
