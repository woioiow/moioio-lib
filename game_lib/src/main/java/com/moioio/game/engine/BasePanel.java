package com.moioio.game.engine;

import com.moioio.android.g2d.Graphics;

public abstract class BasePanel {

    public GameEngine gameEngine;
    public float x;
    public float y;
    public float width;
    public float height;

    public abstract void draw(Graphics g);

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }
}
