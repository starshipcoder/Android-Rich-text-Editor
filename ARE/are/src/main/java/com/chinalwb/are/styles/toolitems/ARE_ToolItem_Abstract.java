package com.chinalwb.are.styles.toolitems;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.R;
import com.chinalwb.are.Util;
import com.chinalwb.are.styles.IARE_Style;
import com.chinalwb.are.styles.toolbar.IARE_Toolbar;

/**
 * Created by wliu on 13/08/2018.
 */

public abstract class ARE_ToolItem_Abstract implements IARE_ToolItem {

    protected IARE_Style mStyle;

    protected View mToolItemView;

    protected IARE_ToolItem_Updater mToolItemUpdater;

    private IARE_Toolbar mToolbar;

    @Override
    public IARE_Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public void setToolbar(IARE_Toolbar toolbar) {
        mToolbar = toolbar;
    }

    @Override
    public void setToolItemUpdater(IARE_ToolItem_Updater toolItemUpdater) {
        mToolItemUpdater = toolItemUpdater;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Default do nothing
        // Children classes can override if necessary
        return;
    }

    public AREditText getEditText() {
        return mToolbar.getEditText();
    }

    protected void setIconStyle(Context context, View view) {
        int size = context.getResources().getDimensionPixelSize(R.dimen.iconSize);
        int margin = context.getResources().getDimensionPixelSize(R.dimen.iconMargin);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
        params.setMarginEnd(margin);
        params.setMarginStart(margin);
        view.setLayoutParams(params);
        view.setBackgroundResource(R.drawable.bg_icon);
    }


    protected <T> void printSpans(Class<T> clazz) {
        EditText editText = getEditText();
        Editable editable = editText.getEditableText();
        T[] spans = editable.getSpans(0, editable.length(), clazz);
        for (T span : spans) {
            int start = editable.getSpanStart(span);
            int end = editable.getSpanEnd(span);
            Util.log("Span -- " + clazz + ", start = " + start + ", end == " + end);
        }
    }
}
