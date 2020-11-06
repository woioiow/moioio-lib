package com.moioio.game.games.physical.flappy_ball;

import android.graphics.Color;

import com.moioio.android.easyui.widget.MyDrawView;
import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.PhysicalGamePanel;
import com.moioio.game.games.physical.common.ScorePanel;

public class FlappyBallGame extends PhysicalGamePanel
{

    BackGround backGround;
    Wall wall;
    BirdBall birdBall;


    ScorePanel scorePanel;


    public boolean init()
    {
        backGround = new BackGround();
        backGround.setColor(0xFF404552);
        backGround.setRectSize(getWidth(),getHeight()*0.3f);
        backGround.setPosition(0,(getHeight()-backGround.height)/2);
        this.addShape(backGround);


        wall = new Wall();
        wall.setColor(Color.BLACK);
        wall.setSpeed(getWidth()/100);
        wall.setRectSize(getWidth(),getHeight()*0.3f);
        wall.setPosition(0,(getHeight()-backGround.height)/2);
        wall.build();
        this.addShape(wall);


//        centreBg.setRadius(getWidth()*0.05f);
//        centreBg.setPosition(getWidth()/2,getHeight()/2);
//
//        aimRing = new AimRing();
//        aimRing.setColor(0xFF4D515C);
//        aimRing.setRadius(getWidth()*0.3f);
//        aimRing.setPosition(getWidth()/2,getHeight()/2);
//        aimRing.build();
//        this.addShape(aimRing);


        birdBall = new BirdBall();
        birdBall.setColor(Color.WHITE);
        birdBall.setRadius(backGround.width/60);
        birdBall.setAreaRect(backGround.x,backGround.y,backGround.width, backGround.height);
        birdBall.setSpeed(backGround.height/50);
        birdBall.setPosition(getWidth()*0.2f,getHeight()/2);
        birdBall.setGameEngine(this);
        birdBall.setWall(wall);
        birdBall.build();
        this.addShape(birdBall);


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
            birdBall.changeDir();
//            aimBall.stopMove();
//            shapes.remove(swingPointer);
        }
    }

    @Override
    public void clear() {

    }

}
