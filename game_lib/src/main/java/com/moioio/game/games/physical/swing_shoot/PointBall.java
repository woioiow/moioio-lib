package com.moioio.game.games.physical.swing_shoot;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Ball;

class PointBall extends Ball {
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillCircle(x,y,radius);
    }

    @Override
    public void logic() {

    }
}
