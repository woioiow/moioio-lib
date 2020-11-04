package com.moioio.game.games.physical.rotate_shoot;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Ball;
import com.moioio.util.MyLog;

class AimBall extends Ball {
    boolean isAim;
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillCircle(x,y,radius);
    }

    @Override
    public void logic() {

    }
}
