package com.moioio.game.engine;

import android.content.Context;

import com.moioio.android.g2d.Graphics;

public abstract class GameEngine
{
    public static final long REFRESH_DELAY = 50L;
    public static int RESULT_FAIL = 0;
    public static int RESULT_SUCCESS = 1;
    public static int RESULT_COMMON = 2;

    private float width;
    private float height;
    private Context context;
    private int result;
    private int score;
    private boolean isInit;

    private long count;


    public void doInit(Context context, float w, float h) {
        width = w;
        height = h;
        isInit = init();
    }

    public final void doRun() {
        count++;
        if(count>1000)
        {
            count = 0;
        }
        if(isInit)
        {
            run();
        }
    }

    public final void doPaint(Graphics g)  {
        if(isInit)
        {
            paint(g);
        }
    }

    public final void doOnTouch(int action, float x, float y)  {
        if(isInit)
        {
            onTouch(action, x, y);
        }
    }

    public final void doClear()  {
        clear();
    }

    protected abstract boolean init();

    protected abstract void run();

    protected abstract void paint(Graphics g);

    protected abstract void onTouch(int action, float x, float y);

    protected abstract void clear();

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Context getContext() {
        return context;
    }

    public int getResult()
    {
        return result;
    }

    public void setOverListener()
    {

    }

    public void setResult(int result) {
        this.result = result;
    }

    public final boolean isInit() {
        return isInit;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

//    public boolean isSecond()
//    {
//
//    }
}
