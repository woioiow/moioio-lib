package com.moioio.game.games.physical.landing_ball;

import android.graphics.Color;

import com.moioio.android.easyui.widget.MyDrawView;
import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.PhysicalGamePanel;
import com.moioio.game.games.physical.common.ScorePanel;

public class LandingBallGame extends PhysicalGamePanel
{

    BackGround backGround;
    Sky sky;
    ShopBall shopBall;


    ScorePanel scorePanel;


    public boolean init()
    {
        backGround = new BackGround();
        backGround.setColor(0xFF22252D);
        backGround.setRectSize(getWidth(),getWidth());
        backGround.setPosition(0,(getHeight()-backGround.height)/2);
        this.addShape(backGround);


        sky = new Sky();
        sky.setColor(Color.BLACK);
        sky.setSpeed(getWidth()/100);
        sky.setRectSize(getWidth(),getWidth());
        sky.setPosition(0,(getHeight()-backGround.height)/2);
        sky.build();
        this.addShape(sky);



        shopBall = new ShopBall();
        shopBall.setColor(Color.WHITE);
        shopBall.setRadius(backGround.width/60);
        shopBall.setAreaRect(backGround.x,backGround.y,backGround.width, backGround.height);
        shopBall.setSpeed(6 );
        shopBall.setPosition(getWidth()*0.2f,getHeight()/2);
        shopBall.setRotateCenter(sky.landBall.x,sky.landBall.y);
        shopBall.setRotateRadius(sky.landBall.radius+shopBall.radius+10);

        shopBall.setGameEngine(this);
        shopBall.setSky(sky);
        shopBall.build();
        this.addShape(shopBall);


        scorePanel = new ScorePanel();
        scorePanel.setGameEngine(this);
        scorePanel.setPosition(0,0);
        scorePanel.setSize(getWidth(),backGround.y);

        return true;
    }



    @Override
    public void run() {

        logicShapes();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        scorePanel.draw(g);
        drawShapes(g);
    }

    @Override
    public void onTouch(int action, float x, float y) {

        if(action== MyDrawView.TOUCH_DOWN)
        {
            shopBall.fly();
//            shopBall.changeDir();
//            aimBall.stopMove();
//            shapes.remove(swingPointer);
        }
    }

    @Override
    public void clear() {

    }

}
