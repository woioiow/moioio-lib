package com.moioio.android.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

public class AppUtil
{
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
                    e.printStackTrace();
                }
            }
        });
    }





    public static void setNoStatusBar(Activity activity) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                View decorView = activity.getWindow().getDecorView();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int id_index = 666;
    public static int getID()
    {
        id_index = 1 + id_index;
        return id_index;
    }




}
