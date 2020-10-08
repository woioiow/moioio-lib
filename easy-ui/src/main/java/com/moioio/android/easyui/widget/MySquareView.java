package com.moioio.android.easyui.widget;

import android.content.Context;

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
