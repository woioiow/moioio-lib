package com.moioio.easyui.widget;

import android.content.Context;

import com.moioio.easyui.MyView;

public class MySquareLayout extends MyView {


    public MySquareLayout(Context context) {
        super(context);
    }

    @Override
    public void initPage(Context context) {

    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
