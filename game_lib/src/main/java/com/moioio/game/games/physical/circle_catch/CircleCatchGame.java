package com.moioio.game.games.physical.circle_catch;

import android.graphics.Color;

import com.moioio.android.easyui.widget.MyDrawView;
import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.PhysicalGamePanel;
import com.moioio.game.games.physical.common.ScorePanel;

public class CircleCatchGame extends PhysicalGamePanel
{

    CatcherRing catcherRing;
    AimRing aimRing;
    ScorePanel scorePanel;


    public boolean init()
    {
        aimRing = new AimRing();
        aimRing.setColor(Color.WHITE);
        aimRing.setRadius(getWidth()*0.4f);
        aimRing.setPosition(getWidth()/2,getHeight()/2);
        aimRing.build();
        this.addShape(aimRing);


        catcherRing = new CatcherRing();
        catcherRing.setColor(0xFF00D193);
        catcherRing.setSpeed(3);
        catcherRing.setRadius(aimRing.radius*0.05f);
        catcherRing.setRotateCenter(getWidth()/2,getHeight()/2);
        catcherRing.setRotateRadius(getWidth()*0.28f);
        catcherRing.setAimRing(aimRing);
        catcherRing.setGameEngine(this);
        catcherRing.build();
        this.addShape(catcherRing);


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
            catcherRing.changeDir();
        }
    }

    @Override
    public void clear() {

    }

}
