package com.moioio.moioio_lib;

import android.content.Context;

import com.moioio.android.easyui.UI;
import com.moioio.android.easyui.widget.MyGifView;
import com.moioio.android.easyui.widget.MyLayout;
import com.moioio.android.easyui.widget.MyView;

public class TestView extends MyView {

//    private GameView gameView;

    public TestView(Context context) {
        super(context);
    }

    MyGifView myGifView;
    @Override
    public void initPage(Context context) {

        myGifView = new MyGifView(context);
        myGifView.load("assets://ic_game.gif");
        myGifView.setDelay(0);
        myGifView.makeLayout().center();

//        gameView = new GameView(context);
//
//        gameView.setGame(RememberTouchGame.class);
//
//
//        gameView.setLayoutParams(new MyLayout(UI.FILL_PARENT,UI.FILL_PARENT).get());
//
//        this.addView(gameView);



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
        this.addView(myGifView);

    }
}
