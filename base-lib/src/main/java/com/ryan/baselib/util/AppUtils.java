package com.ryan.baselib.util;


import android.app.Application;
import android.content.Context;

public final class AppUtils {
    private static final String TAG = "AppUtils";

    private static Context sBaseContext;
    private static Context sAppContext;
    private static boolean sIsBackground = true;

    //for content
    public static void setBaseContext(Context context) {
        sBaseContext = context;
    }

    public static void init(Application application) {
        if (sAppContext != null) {
            throw new ExceptionInInitializerError("Environment should only init once!");
        }
        sAppContext = application;
    }

    public static final boolean isBackground() {
        return sIsBackground;
    }

    public static final Context getContext() {
        return sAppContext == null ? sBaseContext : sAppContext;
    }

    public static <T> T getSystemService(String serviceName) {
        return (T) getContext().getSystemService(serviceName);
    }


}