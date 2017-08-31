package com.yanjingw.opengldemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by Piasy{github.com/Piasy} on 6/7/16.
 */
public final class Utils {

    public static final int BYTES_PER_FLOAT = 4;

    private static final String TAG = "Utils";

    private Utils() {
        // util
    }

    public static boolean supportGlEs20(Activity activity) {
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(
                Context.ACTIVITY_SERVICE);
        return activityManager.getDeviceConfigurationInfo().reqGlEsVersion >= 0x20000;
    }
}
