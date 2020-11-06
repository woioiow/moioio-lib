package com.moioio.game.games.physical.flappy_ball;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Shape;

class BackGround extends Shape {
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,width,height);
    }

    @Override
    public void logic() {

    }
}
