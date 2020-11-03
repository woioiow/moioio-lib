package com.moioio.moioio_lib;

import android.content.Context;

import com.moioio.android.easyui.UI;
import com.moioio.android.easyui.widget.MyLayout;
import com.moioio.android.easyui.widget.MyView;
import com.moioio.game.GameView;
import com.moioio.game.games.physical.rotate_shoot.RotateShootGame;
import com.moioio.game.games.physical.swing_shoot.SwingShootGame;

public class TestView extends MyView {

    private GameView gameView;

    public TestView(Context context) {
        super(context);
    }

    @Override
    public void initPage(Context context) {


        gameView = new GameView(context);

        gameView.setGame(SwingShootGame.class);


        gameView.setLayoutParams(new MyLayout(UI.FILL_PARENT,UI.FILL_PARENT).get());

        this.addView(gameView);



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
//        this.addView(btn);

    }
}
