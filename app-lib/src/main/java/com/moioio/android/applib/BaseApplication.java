package com.moioio.android.applib;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.moioio.android.MyAndroidLib;
import com.moioio.android.applib.res.AppResource;
import com.moioio.android.easyui.MyApplication;
import com.moioio.util.UtilConf;

public abstract class BaseApplication extends MyApplication {


    static Context appContext;


    public void onCreate() {

        super.onCreate();
        appContext = this;

        MultiDex.install(this);
        initConf(this);
        MyAndroidLib.init(this);
        UtilConf.setBufferSize(100*1024);
        AppResource.init(this);
        initApp(this);
    }


    public void initConf(Context context) {

    }

    public abstract void initApp(Context context);


    public static Context getAppContext() {
        return appContext;
    }

}
