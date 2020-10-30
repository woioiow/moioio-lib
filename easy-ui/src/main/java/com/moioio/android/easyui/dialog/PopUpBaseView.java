package com.moioio.android.easyui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

import com.moioio.android.easyui.UIConf;
import com.moioio.android.easyui.widget.MyLayout;
import com.moioio.android.easyui.widget.MyView;
import com.moioio.android.util.DisplayUtil;
import com.moioio.android.util.ViewUtil;


public abstract class PopUpBaseView extends MyView {

    PopUp popUp;

    public PopUpBaseView(Context context) {
        super(context);
    }


    public PopUp getPopUp() {
        return popUp;
    }

    public void setPopUp(PopUp popUp) {
        this.popUp = popUp;
    }

    public void onShow()
    {

    }

    public void onCancel()
    {

    }


}
