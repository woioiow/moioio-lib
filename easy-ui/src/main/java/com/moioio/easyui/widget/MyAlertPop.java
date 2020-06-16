package com.moioio.easyui.widget;

import android.content.Context;
import android.graphics.Bitmap;

import com.moioio.android.util.DisplayUtil;
import com.moioio.easyui.dailog.PopUp;
import com.moioio.easyui.widget.alert.MyAlertView;
import com.moioio.util.DataUtil;

public class MyAlertPop extends PopUp {

    private MyAlertView myAlertView;

    public MyAlertPop(Context context) {
        super(context);
        myAlertView = new MyAlertView(context);
        myAlertView.setPopUp(this);

        int width = DataUtil.getRateValue(DisplayUtil.getScreenWidth(context),80);
        int height = DataUtil.getRateValue(DisplayUtil.getScreenHeight(context),30);
        myAlertView.setViewSize(width,height);

        this.setContentView(myAlertView);
    }


    public MyAlertPop setTitle(String title)
    {
        myAlertView.setTitle(title);
        return this;
    }

    public MyAlertPop setMessage(String msg)
    {
        myAlertView.setMessage(msg);
        return this;
    }

    public MyAlertPop setIcon(Bitmap icon)
    {
        myAlertView.setIcon(icon);
        return this;
    }

    public MyAlertPop addButton(String name, PopUp.OnClickListener onClickListener)
    {
        myAlertView.addButton(name,onClickListener);
        return this;
    }





}
