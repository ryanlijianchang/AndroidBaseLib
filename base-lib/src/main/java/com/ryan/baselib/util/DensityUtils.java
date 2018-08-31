package com.ryan.baselib.util;

public class DensityUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = AppUtils.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(float pxValue) {
        final float scale = AppUtils.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取当前页面的宽度
     */
    public static int getScreenWidth() {
        return AppUtils.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 当前页面的高度
     * 需要传递Activity的context才能保证准确
     *
     * @return
     */
    public static int getScreenHeight() {
        return AppUtils.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取手机屏幕密度（像素比例：0.75/1.0/1.5/2.0）
     * @return float
     */
    public static float getDensity() {
        return AppUtils.getContext().getResources().getDisplayMetrics().density;
    }

    /**
     * 获取手机屏幕密度（每寸像素：120/160/240/320）
     * @return int
     */
    public static int getDensityDPI() {
        return AppUtils.getContext().getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * 获取手机屏幕密度（和系统字体大小有关）
     * @return float
     */
    public static float getScaledDensity() {
        return AppUtils.getContext().getResources().getDisplayMetrics().scaledDensity;
    }
}
