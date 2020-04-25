package com.ryan.baselib.manager;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.util.Log;

public class ScreenShotListenManager {

    private static final String TAG = "ScreenShotListenManager";

    private HandlerThread mScreenShotThread = null;
    private Handler mScreenShotThreadHandler = null;
    private Context mContext;
    /**
     * 内部存储器内容观察者
     */
    private MediaContentObserver mInternalObserver;
    /**
     * 外部存储器内容观察者
     */
    private MediaContentObserver mExternalObserver;

    private ScreenShotListenManager(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("The context must not be null.");
        }
        mContext = context;
    }

    public static ScreenShotListenManager newInstance(Context context) {
        return new ScreenShotListenManager(context);
    }

    /**
     * 启动监听
     */
    public void startListen(OnScreenShotTakenListener listener) {
        stopListen();
        if (mScreenShotThread == null || !mScreenShotThread.isAlive()) {
            mScreenShotThread = new HandlerThread("ScreenShot-Thread");
            mScreenShotThread.start();
            mScreenShotThreadHandler = new Handler(mScreenShotThread.getLooper());
            mInternalObserver = new MediaContentObserver(mContext, mScreenShotThreadHandler, listener);
            mExternalObserver = new MediaContentObserver(mContext, mScreenShotThreadHandler, listener);
            // 注册内容观察者
            mContext.getContentResolver().registerContentObserver(
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                    true,
                    mInternalObserver
            );
            mContext.getContentResolver().registerContentObserver(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    true,
                    mExternalObserver
            );
        }
    }

    public void stopListen() {
        Log.i(TAG, "stopListen()");
        if (mInternalObserver != null) {
            mContext.getContentResolver().unregisterContentObserver(mInternalObserver);
            mInternalObserver = null;
        }
        if (mExternalObserver != null) {
            mContext.getContentResolver().unregisterContentObserver(mExternalObserver);
            mExternalObserver = null;
        }
        if (mScreenShotThread != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                mScreenShotThread.quitSafely();
            } else {
                mScreenShotThread.quit();
            }
        }
    }

}
