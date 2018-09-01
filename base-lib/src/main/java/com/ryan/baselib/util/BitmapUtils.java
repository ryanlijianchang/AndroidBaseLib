package com.ryan.baselib.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class BitmapUtils {

    public static Bitmap decodeResToBitmap(int resId) {
        if (resId <= 0) {
            return null;
        }
        return BitmapFactory.decodeResource(AppUtils.getContext().getResources(), resId);
    }

    public static Bitmap decodeResToBitmap(int resId, int newWidth, int newHeight) {
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

    public static Bitmap drawTextToBitmap(Context mContext, Bitmap userLabelBitmap, String mText, String textColor) {
        try {
            Resources resources = mContext.getResources();
            float scale = resources.getDisplayMetrics().density;
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setColor(Color.parseColor(textColor));
            paint.setTextSize((int) (11 * scale));
            paint.setShadowLayer(1f, 0f, 1f, Color.DKGRAY);

            Rect bounds = new Rect();
            paint.getTextBounds(mText, 0, mText.length(), bounds);

            Bitmap bitmap = userLabelBitmap;

            android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();
            if (bitmapConfig == null) {
                bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
            }
            bitmap = bitmap.copy(bitmapConfig, true);

            Canvas canvas = new Canvas(bitmap);

            paint.setTextAlign(Paint.Align.CENTER);
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            //为基线到字体上边框的距离,即上图中的top
            float top = fontMetrics.top;
            //为基线到字体下边框的距离,即上图中的bottom
            float bottom = fontMetrics.bottom;
            //基线中间点的y轴计算公式
            int baseLineY = (int) (canvas.getHeight() / 2 - top / 2 - bottom / 2);

            //+18:是因为减去左边图案18*18
            canvas.drawText(mText, bitmap.getWidth() / 2 + 18, baseLineY, paint);

            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }
}
