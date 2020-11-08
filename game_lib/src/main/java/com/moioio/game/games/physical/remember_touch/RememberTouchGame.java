package com.moioio.game.games.physical.remember_touch;

import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.PhysicalGamePanel;
import com.moioio.game.games.physical.common.ScorePanel;

public class RememberTouchGame extends PhysicalGamePanel {


    TouchBroad touchBg;
    ScorePanel scorePanel;

    @Override
    protected boolean init() {

        float blockSize = getWidth()/5;


        touchBg = new TouchBroad();
        touchBg.setColor(0xFF00D193);
        touchBg.setBlockSize(blockSize);
        touchBg.setRectSize(blockSize*3,blockSize*3);
        touchBg.setPosition((getWidth()-touchBg.width)/2,(getHeight()-touchBg.height)/2);
        touchBg.setGameEngine(this);
        touchBg.build();
        this.addShape(touchBg);


        scorePanel = new ScorePanel();
        scorePanel.setGameEngine(this);
        scorePanel.setPosition(0,0);
        scorePanel.setSize(getWidth(),touchBg.y);

        return true;
    }

    @Override
    protected void run() {
        logicShapes();

    }

    @Override
    protected void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        scorePanel.draw(g);
        drawShapes(g);
    }

    @Override
    protected void onTouch(int action, float x, float y) {
        touchBg.touchBlock(action,x,y);
    }

    @Override
    protected void clear() {

    }
}
