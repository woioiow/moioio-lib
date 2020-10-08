package com.moioio.android.easyui.widget;

import android.widget.RelativeLayout;

public class MyLayout {





    private RelativeLayout.LayoutParams layoutParams;

    public MyLayout(int width, int height)
    {
        layoutParams = new RelativeLayout.LayoutParams(width,height);
    }

    public MyLayout setWidth(int width)
    {
        layoutParams.width = width;
        return this;
    }

    public MyLayout setHeight(int height)
    {
        layoutParams.height = height;
        return this;
    }

    public MyLayout addRule(int rule)
    {
        layoutParams.addRule(rule);
        return this;
    }

    public MyLayout addRule(int rule, int id)
    {
        layoutParams.addRule(rule,id);
        return this;
    }

    public MyLayout setMargins(int left, int top, int right, int bottom)
    {
        layoutParams.setMargins(left,top,right,bottom);
        return this;
    }

    public MyLayout setMargin(int margin)
    {
        layoutParams.setMargins(margin,margin,margin,margin);
        return this;
    }


    public RelativeLayout.LayoutParams get() {
        return layoutParams;
    }

    public MyLayout leftMargin(int margin)
    {
        layoutParams.leftMargin = margin;
        return this;
    }

    public MyLayout topMargin(int margin)
    {
        layoutParams.topMargin = margin;
        return this;
    }

    public MyLayout rightMargin(int margin)
    {
        layoutParams.rightMargin = margin;
        return this;
    }

    public MyLayout bottomMargin(int margin)
    {
        layoutParams.bottomMargin = margin;
        return this;
    }


}
