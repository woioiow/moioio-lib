package com.moioio.android.easyui;

import java.util.Enumeration;
import java.util.Hashtable;

public class ViewDataWatcher {

    private static ViewDataWatcher viewDataWatcher;
    private Hashtable<String, ViewDataChangeListener> listenerHashtable;

    private ViewDataWatcher() {

        listenerHashtable = new Hashtable<String, ViewDataChangeListener>();
    }

    public static ViewDataWatcher getInstance()
    {
        if(viewDataWatcher==null)
        {
            viewDataWatcher = new ViewDataWatcher();
        }
        return viewDataWatcher;
    }

    public void addViewDataChangeListener(ViewDataChangeListener viewDataChangeListener)
    {
        listenerHashtable.put(viewDataChangeListener.getDataChangeID(),viewDataChangeListener);
    }


    public void updateViews() {

        Enumeration<ViewDataChangeListener> ens =  listenerHashtable.elements();
        while (ens.hasMoreElements())
        {
            ens.nextElement().onDataChanged();
        }
    }


    public void removeViewDataChangeListener(ViewDataChangeListener viewDataChangeListener) {
        listenerHashtable.remove(viewDataChangeListener.getDataChangeID());
    }
}
