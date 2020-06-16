package com.moioio.easyui.dailog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;


public class PopUp extends Dialog
{
    private View currentView;
    public PopUp( Context context)
    {
        super(context,android.R.style.Theme_Translucent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public void setContentView(View view)
    {
        currentView = view;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        super.setContentView(view,layoutParams);
    }

    public void show()
    {
        super.show();
        if(currentView!=null&&currentView instanceof PopUpView)
        {
            ((PopUpView)currentView).onShow();
        }
    }

    public void cancel()
    {
        super.cancel();
        if(currentView!=null&&currentView instanceof PopUpView)
        {
            ((PopUpView)currentView).onCancel();
        }
    }

    public static interface OnClickListener
    {
        public void onClick(PopUp popUp);
    }

    public View getContentView()
    {
        return currentView;
    }

}
