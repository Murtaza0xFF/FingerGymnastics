package com.murtaza.fingertap.utils;

import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by murtaza on 30/1/17.
 */

public class MultiTouchType {

    public static boolean getMultiTouchType(PackageManager packageManager){
        boolean multitouch = packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH);
        if (multitouch) {
            if (packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_JAZZHAND)) {
                return true;
            }
        }
        return false;
    }
}
