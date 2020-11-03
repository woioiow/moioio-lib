package com.moioio.game.games.physical.swing_shoot;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.Shape;

public class TopBorder extends Shape {


    float width;
    float height;

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,width,height);
    }

    @Override
    public void logic() {

    }

    void setSize(float w,float h)
    {
        this.width = w;
        this.height = h;
    }
}
