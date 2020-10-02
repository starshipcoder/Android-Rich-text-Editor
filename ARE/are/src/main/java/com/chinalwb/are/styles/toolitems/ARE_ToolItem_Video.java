package com.chinalwb.are.styles.toolitems;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.R;
import com.chinalwb.are.activities.Are_VideoPlayerActivity;
import com.chinalwb.are.strategies.VideoStrategy;
import com.chinalwb.are.styles.IARE_Style;
import com.chinalwb.are.styles.toolitems.styles.ARE_Style_Video;

import androidx.annotation.DrawableRes;

/**
 * Created by wliu on 13/08/2018.
 */

public class ARE_ToolItem_Video extends ARE_ToolItem_Abstract {

    public ARE_ToolItem_Video() {
        mIcon = R.drawable.video;
    }

    public ARE_ToolItem_Video(@DrawableRes int icon) {
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
            mStyle = new ARE_Style_Video(editText, (ImageView) mToolItemView);
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
            if (ARE_Style_Video.REQUEST_CODE_CHOOSER == requestCode) {
                Uri uri = data.getData();
                openVideoPlayer(uri);
            } else if (ARE_Style_Video.REQUEST_CODE_CHOOSE_RESULT == requestCode) {
                String videoUrl = data.getStringExtra(Are_VideoPlayerActivity.VIDEO_URL);
                Uri uri = data.getData();
                ((ARE_Style_Video) getStyle()).insertVideo(uri, videoUrl);
            }
        }
    }


    /**
     * Open Video player page
     */
    public void openVideoPlayer(Uri uri) {
        Activity context = (Activity) getEditText().getContext();
        VideoStrategy videoStrategy = getEditText().getVideoStrategy();
        Are_VideoPlayerActivity.sVideoStrategy = videoStrategy;

        Intent intent = new Intent();
        intent.setClass(context, Are_VideoPlayerActivity.class);
        intent.setData(uri);
        context.startActivityForResult(intent, ARE_Style_Video.REQUEST_CODE_CHOOSE_RESULT);
    }
}
