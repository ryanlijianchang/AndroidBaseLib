package com.ryan.baselib.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

public class ResourceUtils {

    public static int getCorlor(Context context, int colorId) {
        return ContextCompat.getColor(context, colorId);
    }

    public static Drawable getDrawable(Context context, int resId) {
        return ContextCompat.getDrawable(context, resId);
    }
}
