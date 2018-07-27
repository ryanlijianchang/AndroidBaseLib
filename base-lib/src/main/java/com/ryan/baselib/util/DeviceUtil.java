package com.ryan.baselib.util;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

import java.io.FileInputStream;
import java.io.IOException;

public class DeviceUtil {
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

    private static int parseFileForValue(String textToMatch, FileInputStream stream) {
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
                        if (buffer[j] != textToMatch.charAt(textIndex)) {
                            break;
                        }
                        //Text matches query here.
                        if (textIndex == textToMatch.length() - 1) {
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
