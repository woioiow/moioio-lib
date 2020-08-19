package com.moioio.easyui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.moioio.android.util.ViewUtil;


public abstract class MyView extends RelativeLayout {


    public MyLayout baseLayout;


    public MyView(Context context) {
        super(context);
        ViewUtil.setViewID(this);
        initPage(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ViewUtil.setViewID(this);
        initAttributeSet(attrs);
        initPage(context);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewUtil.setViewID(this);
        initAttributeSet(attrs,defStyleAttr);
        initPage(context);
    }

    public void initAttributeSet(AttributeSet attrs) {

    }

    public void initAttributeSet(AttributeSet attrs, int defStyleAttr) {

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


    public MyLayout makeLayout(int width,int height)
    {
        baseLayout = new MyLayout(width,height);
        this.setLayoutParams(baseLayout.get());
        return baseLayout;
    }

    public MyLayout makeLayout()
    {
        baseLayout = new MyLayout(UIConf.WRAP_CONTENT, UIConf.WRAP_CONTENT);
        this.setLayoutParams(baseLayout.get());
        return baseLayout;
    }


}
