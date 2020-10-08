package com.moioio.android.easyui.widget;

import android.content.Context;

public abstract class MyBaseAdapterItemView extends MyView {

    private int position;

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
}
