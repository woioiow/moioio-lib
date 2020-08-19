package com.moioio.easyui.widget;

import android.content.Context;

import com.moioio.easyui.MyView;

public class MySquareView extends MyView {


    public MySquareView(Context context) {
        super(context);
    }

    @Override
    public void initPage(Context context) {

    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
