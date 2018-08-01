package com.ryan.baselib.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

public class ResourceUtils {

    public static int getCorlor(Context context, int colorId) {
        return ContextCompat.getColor(context, colorId);
    }

    public static Drawable getDrawable(Context context, int resId) {
        return ContextCompat.getDrawable(context, resId);
    }

    public static String getString(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    public static String getString(Context context, int resId, Object... formatArgs) {
        return context.getResources().getString(resId, formatArgs);
    }

    public static TypedArray obtainTypedArray(Context context, int arrayId) {
        return context.getResources().obtainTypedArray(arrayId);
    }

    public static String[] getStringArray(Context context, int arrayId) {
        return context.getResources().getStringArray(arrayId);
    }

    public static int[] getIntegerArray(Context context, int arrayId) {
        return context.getResources().getIntArray(arrayId);
    }

    public static AssetManager getAsset(Context context) {
        return context.getResources().getAssets();
    }
}
