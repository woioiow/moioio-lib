package com.moioio.android.easyui.widget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.moioio.android.easyui.UIConf;
import com.moioio.android.easyui.widget.MyLayout;
import com.moioio.android.util.DisplayUtil;
import com.moioio.android.util.ViewUtil;


public abstract class MyView extends RelativeLayout {


    public MyLayout MyLayout;
    private boolean isInited;
    private int margin;


    public MyView(Context context) {
        super(context);
        margin = DisplayUtil.getDip(getContext(),5);
        initPage(getContext());
        ViewUtil.setViewID(this);
    }

    public abstract void initPage(Context context);

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

    public void reMyLayout()
    {
        this.setLayoutParams(MyLayout.get());
    }


    public MyLayout makeLayout()
    {
        MyLayout = new MyLayout(UIConf.WRAP_CONTENT, UIConf.WRAP_CONTENT);
        this.setLayoutParams(MyLayout.get());
        return MyLayout;
    }


    public void onResume() {

    }
    public void onPause() {

    }
    public void onStop() {

    }
    public void onDestroy() {

    }


    public int defaultMargin() {
        return margin;
    }
}
