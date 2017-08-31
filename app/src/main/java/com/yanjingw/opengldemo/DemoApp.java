package com.yanjingw.opengldemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by wangyanjing on 2017/9/1.
 */

public class DemoApp extends Application {

    public static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }
}
