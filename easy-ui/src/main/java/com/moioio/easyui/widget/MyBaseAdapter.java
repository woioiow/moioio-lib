package com.moioio.easyui.widget;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.moioio.util.MyLog;

import java.lang.reflect.Constructor;
import java.util.Vector;

public class MyBaseAdapter extends BaseAdapter {

    private Vector dataList;
    private Context context;
    private Class viewCLZ;
    private Object bind;
    private ViewHolder viewHolder;
    private MyBaseAdapterListener listener;


    public MyBaseAdapter(Context context)
    {
        this.context = context;
        dataList = new Vector();
    }

    public void setItemViewClass(Class clz)
    {
        this.viewCLZ = clz;
    }

    public void setMyBaseAdapterListener(MyBaseAdapterListener listener) {
        this.listener = listener;
    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.elementAt(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        MyLog.debug("position----"+position);

        if (convertView == null)
        {
            try
            {
                viewHolder = new ViewHolder();
                Constructor con = viewCLZ.getConstructor(Context.class);
                convertView = (MyBaseAdapterItemView)con.newInstance(context);
                viewHolder.view = (MyBaseAdapterItemView)convertView;
                convertView.setTag(viewHolder);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.view.setPosition(position);
        viewHolder.view.setBind(bind);
        viewHolder.view.setData(dataList.elementAt(position));

        if(listener!=null)
        {
            if(position==(getCount()-1))
            {
                if(listener.haveMore())
                {
                    listener.loadMore();
                }
            }
        }
        return convertView;
    }


    public void refresh() {
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    public void addData(Object data) {
        dataList.addElement(data);
    }

    public void removeAll() {
        dataList.removeAllElements();
    }

    public void setAll(Vector all) {

        dataList = all;

//        dataList.removeAllElements();
//        for(Object obj:all)
//        {
//            dataList.addElement(obj);
//        }
    }

    public void setBind(Object bind) {
        this.bind = bind;
    }


    class ViewHolder {
        private MyBaseAdapterItemView view;
    }



}