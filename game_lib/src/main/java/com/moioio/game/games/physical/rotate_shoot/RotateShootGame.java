package com.moioio.game.games.physical.rotate_shoot;

import android.graphics.Color;

import com.moioio.android.easyui.widget.MyDrawView;
import com.moioio.android.g2d.Graphics;
import com.moioio.game.engine.GameEngine;
import com.moioio.game.games.physical.common.PhysicalGamePanel;
import com.moioio.game.games.physical.common.ScorePanel;
import com.moioio.game.games.physical.common.shape.Bomb;

public class RotateShootGame extends PhysicalGamePanel
{

    CentreBg centreBg;
    ShootBall shootBall;
    AimRing aimRing;
    ScorePanel scorePanel;


    public boolean init()
    {



        centreBg = new CentreBg();
        centreBg.setColor(Color.WHITE);
        centreBg.setRadius(getWidth()*0.05f);
        centreBg.setPosition(getWidth()/2,getHeight()/2);
        this.addShape(centreBg);

        aimRing = new AimRing();
        aimRing.setColor(0xFF4D515C);
        aimRing.setRadius(getWidth()*0.3f);
        aimRing.setPosition(getWidth()/2,getHeight()/2);
        aimRing.build();
        this.addShape(aimRing);


        shootBall = new ShootBall();
        shootBall.setColor(Color.WHITE);
        shootBall.setRadius(getWidth()*0.05f*0.4f);
        shootBall.setAreaRectByPos(0,0,getWidth(),getHeight());
        shootBall.setSpeed(aimRing.radius/10);
        shootBall.setRotateCenter(getWidth()/2,getHeight()/2);
        shootBall.setRotateRadius(centreBg.radius+shootBall.radius+10);
        shootBall.setAimRing(aimRing);
        shootBall.setGameEngine(this);
        shootBall.build();
        this.addShape(shootBall);


        scorePanel = new ScorePanel();
        scorePanel.setGameEngine(this);
        scorePanel.setPosition(0,0);
        scorePanel.setSize(getWidth(),getHeight()/2-aimRing.radius);

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
            shootBall.shot();
//            aimBall.stopMove();
//            shapes.remove(swingPointer);
        }
    }

    @Override
    public void clear() {

    }

}
