package com.moioio.moioio_lib;

import android.content.Context;
import android.graphics.Color;

import com.moioio.android.easyui.UI;
import com.moioio.android.easyui.widget.MyButton;
import com.moioio.android.easyui.widget.MyView;
import com.moioio.android.util.DisplayUtil;

public class TestView extends MyView {


    public TestView(Context context) {
        super(context);
    }

    @Override
    public void initPage(Context context) {


        MyButton btn = new MyButton(context);
        btn.setText("测试");
        btn.setTextColor(Color.BLACK);
//        btn.setRoundDrawable(30,Color.BLACK);
        btn.setRoundDrawableBord(30,2,Color.BLACK,Color.WHITE);

        btn.makeLayout(DisplayUtil.getDip(context,100),DisplayUtil.getDip(context,40))
        .addRule(UI.CENTER_IN_PARENT);

        btn.setOnClickListener(v->{

        });

        this.addView(btn);

    }
}
