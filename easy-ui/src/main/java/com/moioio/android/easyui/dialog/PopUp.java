package com.moioio.android.easyui.dialog;

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
        if(currentView!=null&&currentView instanceof PopUpBaseView)
        {
            ((PopUpBaseView)currentView).setPopUp(this);
        }

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        super.setContentView(view,layoutParams);
    }

    public void show()
    {
        super.show();
        if(currentView!=null&&currentView instanceof PopUpBaseView)
        {
            ((PopUpBaseView)currentView).onShow();
        }
    }

    public void cancel()
    {
        super.cancel();
        if(currentView!=null&&currentView instanceof PopUpBaseView)
        {
            ((PopUpBaseView)currentView).onCancel();
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
