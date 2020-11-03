package com.moioio.game.games.physical.rotate_shoot;

import android.content.Context;
import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.engine.GameEngine;
import com.moioio.game.games.physical.common.Bomb;
import com.moioio.game.games.physical.common.BombFireParticle;
import com.moioio.util.MyLog;

public class RotateShootGame extends GameEngine
{


    @Override
    public boolean init() {


        return true;

    }

    @Override
    public void run() {

    }


    Bomb bomb;

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        if(bomb==null)
        {
            bomb = new Bomb();
            bomb.makeBomb(getWidth()/2,getHeight()/2);
        }
        bomb.draw(g);
    }

    @Override
    public void onTouch(int action, float x, float y) {
//        if(bomb.isFinish())
        {
            bomb.makeBomb(x,y);
        }
    }

    @Override
    public void clear() {

    }
}
