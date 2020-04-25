package com.ryan.baselib.util;

import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

public class ResourceUtils {

    public static int getCorlor(int colorId) {
        return ContextCompat.getColor(AppUtils.getContext(), colorId);
    }

    public static Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(AppUtils.getContext(), resId);
    }

    public static String getString(int resId) {
        return AppUtils.getContext().getResources().getString(resId);
    }

    public static String getString(int resId, Object... formatArgs) {
        return AppUtils.getContext().getResources().getString(resId, formatArgs);
    }

    public static TypedArray obtainTypedArray(int arrayId) {
        return AppUtils.getContext().getResources().obtainTypedArray(arrayId);
    }

    public static String[] getStringArray(int arrayId) {
        return AppUtils.getContext().getResources().getStringArray(arrayId);
    }

    public static int[] getIntegerArray(int arrayId) {
        return AppUtils.getContext().getResources().getIntArray(arrayId);
    }

    public static AssetManager getAsset() {
        return AppUtils.getContext().getResources().getAssets();
    }
}
