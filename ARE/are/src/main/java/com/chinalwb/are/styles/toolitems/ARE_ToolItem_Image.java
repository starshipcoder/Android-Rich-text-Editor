package com.chinalwb.are.styles.toolitems;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.R;
import com.chinalwb.are.spans.AreImageSpan;
import com.chinalwb.are.strategies.ImageStrategy;
import com.chinalwb.are.styles.IARE_Style;
import com.chinalwb.are.styles.toolitems.styles.ARE_Style_Image;

import androidx.annotation.DrawableRes;

/**
 * Created by wliu on 13/08/2018.
 */

public class ARE_ToolItem_Image extends ARE_ToolItem_Abstract {

    public ARE_ToolItem_Image() {
        mIcon = R.drawable.image;
    }

    public ARE_ToolItem_Image(@DrawableRes int icon) {
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
            mStyle = new ARE_Style_Image(editText, (ImageView) mToolItemView);
        }
        return mStyle;
    }

    @Override
    public void onSelectionChanged(int selStart, int selEnd) {
        return;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (ARE_Style_Image.REQUEST_CODE == requestCode) {
                ARE_Style_Image imageStyle = (ARE_Style_Image) getStyle();
                Uri uri = data.getData();
                ImageStrategy imageStrategy = this.getEditText().getImageStrategy();
                if (imageStrategy != null) {
                    imageStrategy.uploadAndInsertImage(uri, imageStyle);
                    return;
                }
                imageStyle.insertImage(uri, AreImageSpan.ImageType.URI);
            }
        }
    }
}
