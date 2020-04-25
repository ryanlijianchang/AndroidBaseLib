package com.ryan.baselib.manager;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;

/**
 * 媒体内容观察者(观察媒体数据库的改变)
 */
public class MediaContentObserver extends ContentObserver {
    private static final String TAG = "MediaContentObserver";
    private Context mContext;
    private String previousPath;

    private OnScreenShotTakenListener listener;


    MediaContentObserver(Context context, Handler handler, OnScreenShotTakenListener listener) {
        super(handler);
        this.mContext = context;
        this.listener = listener;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        Cursor cursor = null;
        try {
            cursor = mContext.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA},
                    null, null, null);
            if (cursor != null && cursor.moveToLast()) {
                int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                String path = cursor.getString(dataColumnIndex);
                if (new File(path).lastModified() >= System.currentTimeMillis() - 10000) { //截图时间到检测到时不超过10s
                    if (isScreenshot(path) && !(previousPath != null && previousPath.equals(path))) {
                        if (listener != null) {
                            listener.onScreenshotTaken(path);
                        }
                    }
                    previousPath = path;
                }
            }
        } catch (Throwable t) {
            Log.e(TAG, "catch throwable");
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        super.onChange(selfChange, uri);
    }

    private boolean isScreenshot(String path) {
        return path != null && path.toLowerCase().contains("screenshot");
    }
}