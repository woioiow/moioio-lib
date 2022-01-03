package com.moioio.moioio_lib;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.widget.ImageView;

import com.moioio.android.MyAndroidLib;
import com.moioio.android.easyui.UI;
import com.moioio.android.easyui.widget.MyDrawView;
import com.moioio.android.easyui.widget.MyEditText;
import com.moioio.android.easyui.widget.MyGifView;
import com.moioio.android.easyui.widget.MyImageView;
import com.moioio.android.easyui.widget.MyLayoutView;
import com.moioio.android.easyui.widget.MyView;
import com.moioio.android.g2d.Graphics;
import com.moioio.android.util.BitmapUtil;
import com.moioio.android.util.DisplayUtil;
import com.moioio.android.util.ViewUtil;
import com.moioio.util.MyLog;

public class TestDrawView extends MyDrawView {


    public TestDrawView(Context context) {
        super(context);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void paint(Graphics g) {

        Path path = new Path();
        path.moveTo(50, 0);
        path.lineTo(90, 0);
        path.lineTo(100, 10);
        path.lineTo(100, 100);
        path.lineTo(30, 100);
        path.lineTo(0, 70);
        path.lineTo(0, 50);
        path.lineTo(50, 0);


        g.reset();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.RED);
        g.fillRect(0,0,50,50);
        g.setColor(Color.BLUE);
        g.fillPath(path);


        g.translate(200,200);
        g.setColor(Color.RED);
        g.fillRect(0,0,50,50);
        g.setColor(Color.BLUE);
        g.fillPath(path);

        g.reset();

        g.setColor(Color.GREEN);
        g.fillPath(path);

    }

    @Override
    protected void onTouch(int action, float x, float y) {

    }

}
