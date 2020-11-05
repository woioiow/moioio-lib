package com.moioio.game.games.physical.circle_catch;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Ball;

class CatcherBall extends Ball {
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillCircle(x,y,radius);
    }
    @Override
    public void logic() {

    }
}
