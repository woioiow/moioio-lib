package com.moioio.android.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
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
}
