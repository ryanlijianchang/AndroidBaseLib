package com.ryan.baselib.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class BitmapUtils {

    public Bitmap decodeResToBitmap(int resId) {
        if (resId <= 0) {
            return null;
        }
        return BitmapFactory.decodeResource(AppUtils.getContext().getResources(), resId);
    }

    public Bitmap decodeResToBitmap(int resId, int newWidth, int newHeight) {
        if (resId <= 0) {
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(AppUtils.getContext().getResources(), resId);
        // 原图宽高
        int oldWidth = bitmap.getWidth();
        int oldHeight = bitmap.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / oldWidth;
        float scaleHeight = ((float) newHeight) / oldHeight;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        return Bitmap.createBitmap(bitmap, 0, 0, oldWidth, oldHeight, matrix, true);
    }
}
