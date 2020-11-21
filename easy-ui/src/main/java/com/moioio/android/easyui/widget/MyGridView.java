package com.moioio.android.easyui.widget;

import android.content.Context;
import android.widget.GridView;
import android.widget.ListView;

import com.moioio.android.easyui.UIConf;
import com.moioio.android.util.ViewUtil;

public class MyGridView extends GridView {

    public MyGridView(Context context) {
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

}
