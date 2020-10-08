package com.moioio.android.easyui.widget.alert;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moioio.android.easyui.UIConf;
import com.moioio.android.easyui.widget.MyLayout;
import com.moioio.android.util.ViewUtil;

public class MyAlertView extends MyCustomPopView {

//    private MyTextButton titleView;
    private TextView messageView;
//    private LinearLayout btnsLayout;


    public MyAlertView(Context context) {
        super(context);
    }


    @Override
    public void initView(Context context) {
        super.initView(context);
//        titleView = new MyTextButton(context);
//        ViewUtil.setViewID(titleView);
//        titleView.setTextColor(UIConf.ALERT_TITLE_COLOR);
//        titleView.setTextColor(Color.WHITE);


        messageView = new TextView(context);
        ViewUtil.setViewID(messageView);
        messageView.setTextColor(UIConf.ALERT_MESSAGE_COLOR);

        RelativeLayout.LayoutParams messageView_params = new MyLayout(UIConf.WRAP_CONTENT, UIConf.WRAP_CONTENT)
                .addRule(RelativeLayout.CENTER_IN_PARENT).get();
        messageView.setLayoutParams(messageView_params);


        this.setContentView(messageView);

    }


    public void setMessage(String msg) {
        messageView.setText(msg);
    }


}
