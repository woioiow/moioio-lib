package com.moioio.android.easyui.widget;

import android.content.Context;
import android.view.View;
import android.widget.Toast;


public class MyToast extends Toast {

    public MyToast(Context context ) {
        super(context);
    }


    public static Toast makeView( Context context,View view, int duration) {
        MyToast result = new MyToast(context);
        result.setView(view);
        result.setDuration(duration);
        return result;
    }

}
