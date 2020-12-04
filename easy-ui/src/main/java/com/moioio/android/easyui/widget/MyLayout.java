package com.moioio.android.easyui.widget;

import android.view.View;
import android.widget.RelativeLayout;

import com.moioio.android.easyui.UI;
import com.moioio.android.easyui.UIConf;

public class MyLayout {


    private RelativeLayout.LayoutParams layoutParams;

    public MyLayout()
    {
        layoutParams = new RelativeLayout.LayoutParams(UIConf.WRAP_CONTENT,UIConf.WRAP_CONTENT);
    }


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

    public MyLayout below(View view)
    {
        addRule(UI.BELOW,view.getId());
        return this;
    }

    public MyLayout above(View view)
    {
        addRule(UI.ABOVE,view.getId());
        return this;
    }


    public MyLayout rightOf(View view)
    {
        addRule(UI.RIGHT_OF,view.getId());
        return this;
    }

    public MyLayout leftOf(View view)
    {
        addRule(UI.LEFT_OF,view.getId());
        return this;
    }

    public MyLayout center()
    {
        addRule(UI.CENTER_IN_PARENT);
        return this;
    }

    public MyLayout centerH()
    {
        addRule(UI.CENTER_HORIZONTAL);
        return this;
    }

    public MyLayout centerV()
    {
        addRule(UI.CENTER_VERTICAL);
        return this;
    }


    public MyLayout left()
    {
        addRule(UI.ALIGN_PARENT_LEFT);
        return this;
    }

    public MyLayout right()
    {
        addRule(UI.ALIGN_PARENT_RIGHT);
        return this;
    }

    public MyLayout bottom()
    {
        addRule(UI.ALIGN_PARENT_BOTTOM);
        return this;
    }

    public MyLayout top()
    {
        addRule(UI.ALIGN_PARENT_TOP);
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
