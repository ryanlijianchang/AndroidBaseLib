package com.ryan.baselib.util;

import android.content.Context;
import android.view.ContextThemeWrapper;

import androidx.appcompat.app.AppCompatActivity;

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
