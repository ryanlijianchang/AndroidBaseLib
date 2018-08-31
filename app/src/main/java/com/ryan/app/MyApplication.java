package com.ryan.app;

import android.app.Application;

import com.ryan.baselib.util.AppUtils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppUtils.init(this);
    }
}
