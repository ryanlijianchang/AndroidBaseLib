package com.ryan.baselib.util;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;

public class AppCompatUtils {

    public static AppCompatActivity getAppCompatActivity(Context context) {
        if (context instanceof AppCompatActivity) {
            return (AppCompatActivity) context;
        } else if (context instanceof ContextThemeWrapper){
            ContextThemeWrapper wrapper = (ContextThemeWrapper) context;
            Context baseContext = wrapper.getBaseContext();
            return (AppCompatActivity) baseContext;
        }
        return null;
    }
}
