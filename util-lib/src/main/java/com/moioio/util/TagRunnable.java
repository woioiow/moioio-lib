package com.moioio.util;

import java.util.Hashtable;

public class TagRunnable implements Runnable{

    private Object tag;

    private Hashtable<Object,Object> values;

    public TagRunnable()
    {
        values = new Hashtable<Object,Object>();
    }

    @Override
    public void run() {

    }

    public Object getTag(Object key) {
        return values.get(key);
    }

    public void setTag(Object key,Object value) {
        values.put(key,value);
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }
}
