package com.moioio.android.easyui.widget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.moioio.android.easyui.UI;
import com.moioio.android.easyui.UIConf;
import com.moioio.android.util.ViewUtil;

public class MyScrollView extends ScrollView {


    MyLinearLayout layout;
    public MyScrollView(Context context) {
        super(context);
        ViewUtil.setViewID(this);
        layout = new MyLinearLayout(context);
        layout.setOrientation(UI.VERTICAL);
        this.addView(layout);
    }

    public void addView(View child) {
        this.removeAllViews();
        super.addView(child);
    }

    public void addItem(View child) {
        layout.addView(child);
    }

    public int getItemCount() {
        return layout.getChildCount();
    }

    public View getItemAt(int index) {
        return layout.getChildAt(index);
    }

    public void removeItem(View view) {
        layout.removeView(view);
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
