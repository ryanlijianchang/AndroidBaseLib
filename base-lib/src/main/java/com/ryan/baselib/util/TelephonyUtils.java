package com.ryan.baselib.util;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephonyUtils {

    public static String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) AppUtils.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) {
            return "";
        }
/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String imei = telephonyManager.getImei();
        } else {
            String imei = telephonyManager.getDeviceId();
        }*/
        return "";
    }
}
