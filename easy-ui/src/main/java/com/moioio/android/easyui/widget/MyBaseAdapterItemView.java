package com.moioio.android.easyui.widget;

import android.content.Context;
import android.view.View;

public abstract class MyBaseAdapterItemView extends MyView {

    private int position;
    private Object adapter;

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

    public Object getAdapter() {
        return adapter;
    }

    public void setAdapter(Object adapter) {
        this.adapter = adapter;
    }

    public void setParentView(View view) {

    }
}
