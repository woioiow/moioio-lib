package com.moioio.android.easyui.widget;

import android.content.Context;

public abstract class MyBaseAdapterItemView extends MyView {

    private int position;
    private MyBaseAdapter adapter;

    public MyBaseAdapterItemView(Context context) {
        super(context);
    }


    public abstract void setData(Object obj);



    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private Object bind;
    public void setBind(Object bind) {
        this.bind = bind;
    }

    public Object getBind() {
        return bind;
    }

    public MyBaseAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(MyBaseAdapter adapter) {
        this.adapter = adapter;
    }
}
