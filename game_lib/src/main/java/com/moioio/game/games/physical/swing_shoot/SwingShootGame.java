package com.moioio.game.games.physical.swing_shoot;

import android.graphics.Color;

import com.moioio.android.easyui.widget.MyDrawView;
import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.PhysicalGamePanel;
import com.moioio.util.PhysicalUtil;

public class SwingShootGame extends PhysicalGamePanel {


    TopBorder topBorder;
    AimBall aimBall;
    SwingPointer swingPointer;
    ShootBall shootBall;



    @Override
    public boolean init() {
        //the top bar is to high to shoot
        //nice

        topBorder = new TopBorder();
        topBorder.setColor(0xFF555569);
        topBorder.setPosition(0,getHeight()*0.4f);//*0.25f
        topBorder.setSize(getWidth(),getHeight()*0.25f*0.08f);
        addShape(topBorder);


        aimBall = new AimBall();
        aimBall.setColor(0xFF98F603);
        aimBall.setRadius(getWidth()*0.025f);
        aimBall.setPosition(getWidth()/2,topBorder.y+topBorder.height+aimBall.radius*2);
        aimBall.setXDomain(0,getWidth());
        addShape(aimBall);


        shootBall = new ShootBall();
        shootBall.setColor(0xFFFEFDFE);
        shootBall.setRadius(getWidth()*0.02f);
        shootBall.setXDomain(0,getWidth());
        shootBall.setYDomain(topBorder.y+topBorder.height,getHeight());
        shootBall.setPosition(getWidth()/2,getHeight()-getHeight()*0.1f-shootBall.radius);
        shootBall.setSpeed((shootBall.y-topBorder.y)/50);
        shootBall.setAimBall(aimBall);
        shootBall.setSwingShootGame(this);

        addShape(shootBall);

        swingPointer = new SwingPointer();
        swingPointer.setColor(0xFFFEFDFE);
        swingPointer.setSize(shootBall.radius);
        swingPointer.setPosition(shootBall.x,shootBall.y);
        float minAngle = 180+PhysicalUtil.getAngle(topBorder.x,topBorder.y+topBorder.height,shootBall.x,shootBall.y)-20;
        float maxAngle = 180+PhysicalUtil.getAngle(topBorder.x+topBorder.width,topBorder.y+topBorder.height,shootBall.x,shootBall.y)+20;
        swingPointer.setAngleDomain(minAngle,maxAngle);
        addShape(swingPointer);


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
        drawShapes(g);
    }

    @Override
    public void onTouch(int action, float x, float y) {

        if(action== MyDrawView.TOUCH_DOWN)
        {
            shootBall.shot(swingPointer.angle);
            aimBall.stopMove();
            shapes.remove(swingPointer);
        }
    }

    @Override
    public void clear() {

    }


}
