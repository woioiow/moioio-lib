package com.moioio.android.easyui.widget;

import android.content.Context;
import android.widget.LinearLayout;

public class MySquareLayout extends MyView {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private int orientation = HORIZONTAL;

    public MySquareLayout(Context context) {
        super(context);
    }

    @Override
    public void initPage(Context context) {


    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(this.orientation==HORIZONTAL)
        {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        }
        else
        {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        }
    }


    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
