package com.moioio.android.easyui.widget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.moioio.android.easyui.UIConf;
import com.moioio.android.util.ViewUtil;

public class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context) {
        super(context);
        ViewUtil.setViewID(this);
    }

    private MyLayout MyLayout;
    public MyLayout makeLayout(int width, int height)
    {
        MyLayout = new MyLayout(width,height);
        this.setLayoutParams(MyLayout.get());
        return MyLayout;
    }

    public MyLayout getMyLayout()
    {
        return MyLayout;
    }

    public MyLayout makeLayout()
    {
        MyLayout = new MyLayout(UIConf.WRAP_CONTENT, UIConf.WRAP_CONTENT);
        this.setLayoutParams(MyLayout.get());
        return MyLayout;
    }

    public void show()
    {
        this.setVisibility(VISIBLE);
    }

    public void hide()
    {
        this.setVisibility(INVISIBLE);
    }

    public void gone()
    {
        this.setVisibility(GONE);
    }

    public boolean isShow()
    {
        return this.getVisibility()==VISIBLE;
    }

    public boolean isHide()
    {
        return this.getVisibility()==INVISIBLE;
    }

    public boolean isGone()
    {
        return this.getVisibility()==GONE;
    }


    public Activity getActivity()
    {
        return (Activity)getContext();
    }

    protected void onVisibilityChanged(View changedView, int visibility) {
        if(visibility==VISIBLE)
        {
            onShow();
        }
        else
        {
            onHide();
        }
    }

    public void onShow()
    {
    }

    public void onHide()
    {

    }

}
