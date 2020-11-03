package com.moioio.game;

import android.content.Context;

import com.moioio.android.easyui.widget.MyDrawView;
import com.moioio.android.g2d.Graphics;
import com.moioio.android.util.AppUtil;
import com.moioio.android.util.TimerHandler;
import com.moioio.game.engine.GameEngine;
import com.moioio.util.MyLog;

import java.lang.reflect.Constructor;

public class GameView extends MyDrawView   {


    private GameEngine gameEngine;
    private static TimerHandler uiTimer;
//    private static TimerHandler logicTimer;

    public GameView(Context context) {
        super(context);
        uiTimer = new TimerHandler(()->{logic();},GameEngine.REFRESH_DELAY, AppUtil.getId());
//        logicTimer = new TimerHandler(()->{logic();},1000, AppUtil.getId());

    }



    boolean isInit;
    @Override
    protected void init() {
        if(!isInit)
        {
            if(gameEngine!=null)
            {
                gameEngine.doInit(getContext(),width(),height());
            }
            isInit = true;
        }
//        MyLog.debug("isInit::::"+isInit);

    }

    @Override
    protected void paint(Graphics g) {

        if(gameEngine!=null)
        {
            gameEngine.doPaint(g);
        }
    }

    @Override
    protected void onTouch(int action, float x, float y) {
        if(gameEngine!=null)
        {
            gameEngine.doOnTouch(action, x, y);
        }
    }

    private void logic()
    {
        if(gameEngine!=null)
        {
            gameEngine.doRun();
            repaint();
        }
    }

    public void setGame(Class clz)
    {
        if(gameEngine!=null)
        {
            gameEngine.doClear();
        }
        try
        {
            Constructor con = clz.getConstructor();
            gameEngine = (GameEngine) con.newInstance();

        }
        catch (Exception e)
        {
            MyLog.printStackTrace(e);
        }

        isInit = false;
    }




    public void onShow()
    {
        uiTimer.start();
//        logicTimer.start();
    }

    public void onHide()
    {
        uiTimer.stop();
//        logicTimer.stop();

    }



}
