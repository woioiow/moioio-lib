package com.moioio.easyui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import com.moioio.android.g2d.Graphics;
import com.moioio.android.util.ViewUtil;

public abstract class MyDrawView extends View
{

    private Graphics g;
    private float w;
    private float h;
    public static final int NONE = 0;
    public static final int ACTION_DOWN = 1;
    public static final int ACTION_MOVE = 2;
    public static final int ACTION_UP = 3;

    public MyDrawView(Context context)
    {
        super(context);
        ViewUtil.setViewID(this);
        g = new Graphics();
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        w = getWidth();
        h = getHeight();
        g.setWidth(w);
        g.setHeight(h);
        g.setCanvas(canvas);
        paint(g);
    }

    public abstract void paint(Graphics g);


    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:
                onTouch(ACTION_DOWN,x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                onTouch(ACTION_MOVE,x,y);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                onTouch(ACTION_UP,x,y);
                break;
        }
        return true;
    }

    public abstract void onTouch(int action, float x, float y);


    public void repaint()
    {
        postInvalidate();
    }

    public float getW()
    {
        return w;
    }

    public void setW(float w)
    {
        this.w = w;
    }

    public float getH()
    {
        return h;
    }

    public void setH(float h)
    {
        this.h = h;
    }


    public static final int DRAG = 1;
    public static final int ZOOM = 2;
    public int dragMode = NONE;
    float x_down = 0;
    float y_down = 0;
    float x_up = 0;
    float y_up = 0;
    public float dragRate_x;
    public float dragRate_y;


    public void onSeekTouch(int action, float px, float py)
    {
        float mw = getW();
        float mh = getH();
        float x = 0;
        float y = 0;

        switch (action)
        {
            case ACTION_DOWN:
                dragMode = NONE;
                x_down = px;
                y_down = py;

                int dx = (int) px;
                int dy = (int) py;
                RectF rect = new RectF(x, y, x + mw, y + mh);
                if (rect.contains(dx, dy))
                {
                    dragMode = DRAG;
//                    float cc = (x_down - x);
                }
                break;
            case ACTION_MOVE:
                if (dragMode == DRAG)
                {
                    float cx = (px - x_down);
                    float cy = (py - y_down);
                    dragRate_x = cx / mw;
                    dragRate_y = cy / mh;
                }
                break;
            case ACTION_UP:
                dragMode = NONE;
                x_up = px;
                y_up = py;
                break;
        }
    }


}
