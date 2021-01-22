package com.moioio.android.easyui.widget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.moioio.util.MyLog;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MyBaseAdapter extends BaseAdapter {

    private List dataList;
    private Context context;
    private Class viewCLZ;
    private ViewHolder viewHolder;
    private MyBaseAdapterListener listener;


    public MyBaseAdapter(Context context)
    {
        this.context = context;
        dataList = new ArrayList();
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
        return dataList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        MyLog.debug("position-----"+position);
        if (convertView == null)
        {
            try
            {
//                MyLog.debug("viewCLZ------"+viewCLZ);
                viewHolder = new ViewHolder();
                Constructor con = viewCLZ.getConstructor(Context.class);
                convertView = (MyBaseAdapterItemView)con.newInstance(context);
                ((MyBaseAdapterItemView)convertView).setAdapter(this);
                viewHolder.view = (MyBaseAdapterItemView)convertView;
                convertView.setTag(viewHolder);
            }
            catch (Exception e)
            {
                MyLog.printStackTrace(e);
            }
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(viewHolder.view!=null)
        {
            viewHolder.view.setData(dataList.get(position));
            viewHolder.view.setPosition(position);
        }

        if(listener!=null)
        {
            if(position==(getCount()-1))
            {
                if(!listener.loading())
                {
                    if(listener.haveMore())
                    {
                        listener.loadMore();
                    }
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
        dataList.add(data);
    }

    public void removeAll() {
        dataList.clear();
    }


    public void remove(int index) {
        dataList.remove(index);
    }

    public void remove(Object obj) {
        if(dataList.contains(obj))
        {
            dataList.remove(obj);
        }
    }

    public void setAll(List all) {
        for(Object obj:all)
        {
            dataList.add(obj);
        }
    }

    class ViewHolder {
        private MyBaseAdapterItemView view;
    }



}
