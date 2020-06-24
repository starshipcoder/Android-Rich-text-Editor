package com.chinalwb.are.styles;

import com.chinalwb.are.Constants;
import com.chinalwb.are.R;

import android.view.View;
import android.widget.ImageView;

public class ARE_Helper {

  /**
   * Updates the check status.
   * 
   * @param areStyle
   * @param checked
   */
  public static void updateCheckStatus(IARE_Style areStyle, boolean checked) {
	areStyle.setChecked(checked);
	View imageView = areStyle.getImageView();
      imageView.setBackgroundResource(R.drawable.bg_icon);
//    int color = checked ? Constants.CHECKED_COLOR : Constants.UNCHECKED_COLOR;
//    imageView.setBackgroundColor(color);
      imageView.setSelected(checked);
  }
  
  
}
