package com.moioio.game.games.physical.swing_shoot;

import android.graphics.Color;

import com.moioio.android.easyui.widget.MyDrawView;
import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.PhysicalGamePanel;
import com.moioio.game.games.physical.common.ScorePanel;
import com.moioio.util.PhysicalUtil;

public class SwingShootGame extends PhysicalGamePanel {


    TopBorder topBorder;
    AimBall aimBall;
    ShootBall shootBall;

    ScorePanel scorePanel;



    @Override
    public boolean init() {

        scorePanel = new ScorePanel();
        scorePanel.setGameEngine(this);
        scorePanel.setPosition(0,0);
        scorePanel.setSize(getWidth(),getHeight()*0.5f);


        topBorder = new TopBorder();
        topBorder.setColor(0xFF555569);
        topBorder.setPosition(0,getHeight()*0.5f);
        topBorder.setSize(getWidth(),getHeight()*0.25f*0.08f);
        addShape(topBorder);


        aimBall = new AimBall();
        aimBall.setColor(0xFF98F603);
        aimBall.setRadius(getWidth()*0.025f);
        aimBall.setPosition(getWidth()/2,topBorder.y+topBorder.height+aimBall.radius*2);
        aimBall.setXDomain(aimBall.radius,getWidth()-aimBall.radius);
        addShape(aimBall);




        shootBall = new ShootBall();
        shootBall.setColor(0xFFFEFDFE);
        shootBall.setRadius(getWidth()*0.02f);
        shootBall.setAreaRectByPos(0,topBorder.y+topBorder.height,getWidth(),getHeight());
        shootBall.setPosition(getWidth()/2,getHeight()-getHeight()*0.1f-shootBall.radius);
        shootBall.setSpeed((shootBall.y-topBorder.y)/50);
        shootBall.setAimBall(aimBall);
        shootBall.setGameEngine(this);
        shootBall.setStartY(shootBall.y);

        float minAngle = 180+PhysicalUtil.getAngle(topBorder.x,topBorder.y+topBorder.height,shootBall.x,shootBall.y)-20;
        float maxAngle = 180+PhysicalUtil.getAngle(topBorder.x+topBorder.width,topBorder.y+topBorder.height,shootBall.x,shootBall.y)+20;


        shootBall.setAngleDomain(minAngle,maxAngle);
        shootBall.build();


        addShape(shootBall);

//        swingPointer = new SwingPointer();
//        swingPointer.setColor(0xFFFEFDFE);
//        swingPointer.setSize(shootBall.radius);
//        swingPointer.setPosition(shootBall.x,shootBall.y);
//        swingPointer.setAngleDomain(minAngle,maxAngle);
//        addShape(swingPointer);


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
            aimBall.stopMove();
//            shapes.remove(swingPointer);
        }
    }

    @Override
    public void clear() {

    }


}
