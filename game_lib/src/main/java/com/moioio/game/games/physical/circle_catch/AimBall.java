package com.moioio.game.games.physical.circle_catch;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Ball;

class AimBall extends Ball {
    boolean isAim;
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillCircle(x,y,radius);
    }

    @Override
    public void logic() {
        move();
        step++;
        if(radius<=maxSize)
        {
            radius += 1;
        }
    }

    float maxSize;
    public void setMaxSize(float max) {
        maxSize = max;
    }

    public void build() {
        makeSpeedXY();
    }

    float maxStep;
    float step;
    public void setMaxStep(float max) {
        this.maxStep = max;
    }

    @Override
    public boolean isDead() {
        return step>maxStep;
    }

    public void dead() {
        step = maxStep+1;
    }
}
