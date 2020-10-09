package com.base.library.picture;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author reber
 * on 2020/10/9 18:40
 */
public class AppGlideUtil {

    public static void loadImage(Activity activity, ImageView imageView) {
        Glide.with(activity).load("").into(imageView);
    }
}
