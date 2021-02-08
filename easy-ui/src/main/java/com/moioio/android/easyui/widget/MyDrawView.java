package com.moioio.android.easyui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import com.moioio.android.easyui.UIConf;
import com.moioio.android.g2d.Graphics;
import com.moioio.android.util.ViewUtil;
import com.moioio.util.MyLog;

public abstract class MyDrawView extends View
{
    private Graphics g;
    private float w;
    private float h;
    public static final int NONE = 0;
    public static final int TOUCH_DOWN = 1;
    public static final int TOUCH_MOVE = 2;
    public static final int TOUCH_UP = 3;
    private SparseArray<PointF> multiTouchPoints;
    private MyLayout MyLayout;
    boolean isUseTouch;

    public MyLayout makeLayout(int width, int height)
    {
        MyLayout = new MyLayout(width,height);
        this.setLayoutParams(MyLayout.get());
        return MyLayout;
    }

    public MyLayout getMyLayout()
    {
        return MyLayout;
    }

    public MyLayout makeLayout()
    {
        MyLayout = new MyLayout(UIConf.WRAP_CONTENT, UIConf.WRAP_CONTENT);
        this.setLayoutParams(MyLayout.get());
        return MyLayout;
    }

    public void show()
    {
        this.setVisibility(VISIBLE);
    }

    public void hide()
    {
        this.setVisibility(INVISIBLE);
    }

    public void gone()
    {
        this.setVisibility(GONE);
    }

    public Activity getActivity()
    {
        return (Activity)getContext();
    }

    public void setUseTouch(boolean useTouch) {
        isUseTouch = useTouch;
    }

    public MyDrawView(Context context)
    {
        super(context);
        ViewUtil.setViewID(this);
        multiTouchPoints = new SparseArray<PointF>();
        g = new Graphics();
    }


    boolean isInit;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        w = widthSpecSize;
        h = heightSpecSize;
        if(!isInit)
        {
            init();
            isInit = true;
        }
    }


    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        g.setWidth(w);
        g.setHeight(h);
        g.setCanvas(canvas);
        paint(g);
    }

    protected abstract void init();
    protected abstract void paint(Graphics g);
    protected abstract void onTouch(int action, float x, float y);




    protected void onVisibilityChanged(View changedView, int visibility) {
        if(visibility==VISIBLE)
        {
            onShow();
        }
        else
        {
            onHide();
        }
    }

    protected void onHide() {

    }

    protected void onShow() {

    }

    public boolean onTouchEvent(MotionEvent event)
    {
        if(!isUseTouch)
        {
            return super.onTouchEvent(event);
        }


        int size = event.getPointerCount();

        if(size==1)
        {
            float x = event.getX();
            float y = event.getY();
            int type = NONE;

            switch (event.getAction() & MotionEvent.ACTION_MASK)
            {
                case MotionEvent.ACTION_DOWN:
                    type = TOUCH_DOWN;
                    break;
                case MotionEvent.ACTION_MOVE:
                    type = TOUCH_MOVE;
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    type = TOUCH_UP;
                    break;
            }
            if(type!=NONE)
            {
                onTouch(type,x,y);
            }
        }
        else
        {
            int pointerIndex = event.getActionIndex();
            int pointerId = event.getPointerId(pointerIndex);
            int maskedAction = event.getActionMasked();

            switch (maskedAction)
            {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN: {
                    PointF point = new PointF();
                    point.x = event.getX(pointerIndex);
                    point.y = event.getY(pointerIndex);
                    multiTouchPoints.put(pointerId, point);
                    onMultiTouch(pointerId, TOUCH_DOWN,point.x,point.y);
                    break;
                }
                case MotionEvent.ACTION_MOVE: { // a pointer was moved
                    for (int i = 0; i < size; i++) {
                        PointF point = multiTouchPoints.get(event.getPointerId(i));
                        if (point != null) {
                            point.x = event.getX(i);
                            point.y = event.getY(i);
                            onMultiTouch(pointerId, TOUCH_MOVE,point.x,point.y);
                        }
                    }
                    break;
                }
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL: {
                    multiTouchPoints.remove(pointerId);
                    onMultiTouch(pointerId, TOUCH_UP,event.getX(pointerIndex),event.getY(pointerIndex));
                    break;
                }
            }
        }

        return true;
    }



    protected void onMultiTouch(int id,int action, float x, float y)
    {

    }



    public void repaint()
    {
        postInvalidate();
    }

    public float width()
    {
        return w;
    }


    public float height()
    {
        return h;
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
    public float dragValue_x;
    public float dragValue_y;


    public void onSeekTouch(int action, float px, float py)
    {
        float mw = width();
        float mh = height();
        float x = 0;
        float y = 0;

        switch (action)
        {
            case TOUCH_DOWN:
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
            case TOUCH_MOVE:
                if (dragMode == DRAG)
                {
                    dragValue_x = (px - x_down);
                    dragValue_y = (py - y_down);
                    dragRate_x = dragValue_x / mw;
                    dragRate_y = dragValue_y / mh;
                }
                break;
            case TOUCH_UP:
                dragMode = NONE;
                x_up = px;
                y_up = py;
                break;
        }
    }


}
