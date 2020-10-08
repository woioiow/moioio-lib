package com.moioio.android.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.moioio.util.MyLog;

public class AppUtil
{


    public static String getVersionName(Context context) {

        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            MyLog.printStackTrace(e);
        }

        return null;

    }

    private static int id_index = 666;
    public static int getId()
    {
        id_index = 1 + id_index;
        return id_index;
    }



    private static Handler handler;
    public static void showTips(final Context context, final String tips) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }

        handler.post(new Runnable() {
            @SuppressLint("WrongConstant")
            public void run() {
                try {
                    Toast.makeText(context, tips, 60).show();
                } catch (Exception e) {
                    MyLog.printStackTrace(e);
                }
            }
        });
    }

    public static void openWeb(Context context,String url) {

        try {
            Intent intent = new Intent();
            intent.setData(Uri.parse(url));
            intent.setAction(Intent.ACTION_VIEW);
            context.startActivity(intent);
        }
        catch (Exception e)
        {
            MyLog.printStackTrace(e);

        }

    }
}
