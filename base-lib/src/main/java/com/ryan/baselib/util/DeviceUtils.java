package com.ryan.baselib.util;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class DeviceUtils {
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";

    /**
     * 华为系统
     */
    public static final String SYS_EMUI = "sys_emui";
    /**
     * 小米系统
     */
    public static final String SYS_MIUI = "sys_miui";
    /**
     * 魅族系统
     */
    public static final String SYS_FLYME = "sys_flyme";

    /**
     * 总内存，MB
     */
    private static int sTotalMem = 0;

    private static boolean mIsMemInfoInit = false;


    /**
     * 获得总内存大小
     *
     * @return 内存大小 单位：MB
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static int getTotalMemory() {
        if (mIsMemInfoInit) {
            return sTotalMem;
        }

        // memInfo.totalMem not supported in pre-Jelly Bean APIs.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
            ActivityManager am = AppUtils.getSystemService(Context.ACTIVITY_SERVICE);
            am.getMemoryInfo(memInfo);
            sTotalMem = (int) (memInfo.totalMem / 1024 / 1024);
        } else {
            try {
                FileInputStream stream = new FileInputStream("/proc/meminfo");
                sTotalMem = parseFileForValue("MemTotal", stream) / 1024;
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mIsMemInfoInit = true;
        return sTotalMem;
    }

    public static String getSystem() {
        String sys = "";
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            if (properties.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || properties.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || properties.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null) {
                sys = SYS_MIUI;
            } else if (properties.getProperty(KEY_EMUI_API_LEVEL, null) != null
                    || properties.getProperty(KEY_EMUI_VERSION, null) != null
                    || properties.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, null) != null
                    ) {
                sys = SYS_EMUI;
            } else if (getMeizuFlymeOSFlag().toLowerCase().contains("flyme")) {
                sys = SYS_FLYME;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return sys;
        }
        return sys;
    }

    private static String getMeizuFlymeOSFlag() {
        String defaultValue = "";
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, "ro.build.display.id", defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defaultValue;
    }


    private static int parseFileForValue(String text, FileInputStream stream) {
        byte[] buffer = new byte[1024];
        try {
            int length = stream.read(buffer);
            for (int i = 0; i < length; i++) {
                if (buffer[i] == '\n' || i == 0) {
                    if (buffer[i] == '\n') {
                        i++;
                    }
                    for (int j = i; j < length; j++) {
                        int textIndex = j - i;
                        //Text doesn't match query at some point.
                        if (buffer[j] != text.charAt(textIndex)) {
                            break;
                        }
                        //Text matches query here.
                        if (textIndex == text.length() - 1) {
                            return extractValue(buffer, j);
                        }
                    }
                }
            }
        } catch (IOException e) {
            //Ignore any exceptions and fall through to return unknown value.
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    private static int extractValue(byte[] buffer, int index) {
        char newLineChar = '\n';
        while (index < buffer.length && buffer[index] != newLineChar) {
            if (Character.isDigit(buffer[index])) {
                int start = index;
                index++;
                while (index < buffer.length && Character.isDigit(buffer[index])) {
                    index++;
                }
                String str = new String(buffer, 0, start, index - start);
                return Integer.parseInt(str);
            }
            index++;
        }
        return 0;
    }

}
