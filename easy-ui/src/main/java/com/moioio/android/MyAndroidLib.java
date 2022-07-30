package com.moioio.android;

import android.content.Context;

import com.moioio.android.util.DisplayUtil;

public class MyAndroidLib {
    public static Context context;

    public static void init(Context ctx)
    {
        context = ctx;
        DisplayUtil.init();
    }
}
