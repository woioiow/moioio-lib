package com.moioio.moioio_lib;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.moioio.android.MyAndroidLib;
import com.moioio.android.easyui.UI;
import com.moioio.android.easyui.widget.MyGifView;
import com.moioio.android.easyui.widget.MyImageView;
import com.moioio.android.easyui.widget.MyLayout;
import com.moioio.android.easyui.widget.MyLayoutView;
import com.moioio.android.easyui.widget.MyView;
import com.moioio.android.util.BitmapUtil;
import com.moioio.android.util.DisplayUtil;
import com.moioio.android.util.ViewUtil;

public class TestView extends MyView {

//    private GameView gameView;

    public TestView(Context context) {
        super(context);
    }

    MyGifView myGifView;
    @Override
    public void initPage(Context context) {
        setBackgroundColor(Color.BLUE);

//        myGifView = new MyGifView(context);
//        myGifView.load("assets://ic_game.gif");
//        myGifView.setDelay(0);
//        myGifView.makeLayout().center();

//        gameView = new GameView(context);
//
//        gameView.setGame(RememberTouchGame.class);
//
//
//        gameView.setLayoutParams(new MyLayout(UI.FILL_PARENT,UI.FILL_PARENT).get());
//
//        this.addView(gameView);


        MyAndroidLib.init(context);

        MyLayoutView view = new MyLayoutView(context);
        view.makeLayout(DisplayUtil.dip(200),DisplayUtil.dip(200))
        .center();

        MyLayoutView view2 = new MyLayoutView(context);
        view2.makeLayout(DisplayUtil.dip(200),DisplayUtil.dip(200))
                .center();
        view2.setBackground(ViewUtil.makeRoundBord(0,1,Color.RED));


        MyImageView iconView = new MyImageView(context);
        iconView.setScaleType(ImageView.ScaleType.FIT_XY);
        iconView.setImageBitmap(BitmapUtil.getAssetsBitmap(context,"assets://lib_test.jpg"));
        iconView.makeLayout(UI.WRAP_CONTENT,UI.WRAP_CONTENT);
        view.addView(iconView);

//        MyLayoutView iconView = new MyLayoutView(context);
//        iconView.setBackgroundColor(Color.GRAY);
//        iconView.makeLayout(UI.FILL_PARENT,UI.FILL_PARENT);
//        view.addView(iconView);



        view.setRoundAngle(DisplayUtil.dip(20));
        view.setShadow(Color.RED,defaultMargin());

//        MyButton btn = new MyButton(context);
//        btn.setText("测试");
//        btn.setTextColor(Color.BLACK);
////        btn.setRoundDrawable(30,Color.BLACK);
//        btn.setRoundDrawableBord(30,2,Color.BLACK,Color.WHITE);
//
//        btn.makeLayout(DisplayUtil.getDip(context,100),DisplayUtil.getDip(context,40))
//        .addRule(UI.CENTER_IN_PARENT);
//
//        btn.setOnClickListener(v->{
//
//        });
//
        this.addView(view);
        this.addView(view2);


        view.setOnClickListener(v->{
            v.requestLayout();
        });
    }
}
