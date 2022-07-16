package com.moioio.android.easyui.widget.round;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;


/**
 * @author kuanggang on 2019/12/10
 */
public class RoundHelper {

//    private View mView;

    private Paint mPaint;
    private RectF mRectF;
//    private RectF mStrokeRectF;

    private Path mPath;
    private Path mTempPath;

    private Xfermode mXfermode;

    private boolean isCircle;

    private float[] mRadii;
    private float[] mStrokeRadii;

    private int mWidth;
    private int mHeight;
    private int mStrokeColor;
    private float mStrokeWidth;

    private float mRadiusTopLeft;
    private float mRadiusTopRight;
    private float mRadiusBottomLeft;
    private float mRadiusBottomRight;

    public void init( ) {

        mRadii = new float[8];
        mStrokeRadii = new float[8];
        mRectF = new RectF();
        mStrokeColor = Color.BLACK;
    }

    private void setData() {
        if(mPaint==null)
        {
            mPaint = new Paint();
            mPath = new Path();
            mTempPath = new Path();
//            mXfermode = new PorterDuffXfermode(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PorterDuff.Mode.DST_OUT : PorterDuff.Mode.DST_IN);
//            mXfermode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
            mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        }



        mRectF.set((mStrokeWidth  ), (mStrokeWidth  ), mWidth - mStrokeWidth , mHeight - mStrokeWidth );



        mRadii[0] = mRadii[1] = mRadiusTopLeft - mStrokeWidth;
        mRadii[2] = mRadii[3] = mRadiusTopRight - mStrokeWidth;
        mRadii[4] = mRadii[5] = mRadiusBottomRight - mStrokeWidth;
        mRadii[6] = mRadii[7] = mRadiusBottomLeft - mStrokeWidth;

        mStrokeRadii[0] = mStrokeRadii[1] = mRadiusTopLeft;
        mStrokeRadii[2] = mStrokeRadii[3] = mRadiusTopRight;
        mStrokeRadii[4] = mStrokeRadii[5] = mRadiusBottomRight;
        mStrokeRadii[6] = mStrokeRadii[7] = mRadiusBottomLeft;
    }

    public void onSizeChanged(int width, int height) {
        mWidth = width;
        mHeight = height;
        mRectF.set((mStrokeWidth  ), (mStrokeWidth  ), mWidth - mStrokeWidth , mHeight - mStrokeWidth );

//        if (mRectF != null) {
//            mRectF.set(0, 0, width, height);
//        }
//        if (mStrokeRectF != null) {
//        }
    }

    public void preDraw(Canvas canvas) {
        if(mPaint==null)
        {
            return;
        }
        canvas.saveLayer(mRectF, null, Canvas.ALL_SAVE_FLAG);
//        if (mStrokeWidth > 0) {
//            float sx = (mWidth - 2 * mStrokeWidth) / mWidth;
//            float sy = (mHeight - 2 * mStrokeWidth) / mHeight;
//            // 缩小画布，使图片内容不被borders覆盖
//            canvas.scale(sx, sy, mWidth / 2.0f, mHeight / 2.0f);
//        }
    }

    public void drawPath(Canvas canvas) {
        if(mPaint==null)
        {
            return;
        }


        mPaint.reset();
        mPath.reset();

        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(mXfermode);

        mPath.addRoundRect(mRectF, mRadii, Path.Direction.CCW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTempPath.reset();
            mTempPath.addRect(mRectF, Path.Direction.CCW);
            mTempPath.op(mPath, Path.Op.DIFFERENCE);
            canvas.drawPath(mTempPath, mPaint);
        } else {
            canvas.drawPath(mPath, mPaint);
        }
        mPaint.setXfermode(null);
        canvas.restore();

        // draw stroke
        if (mStrokeWidth > 0)
        {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setMaskFilter(blurMaskFilter);
            mPaint.setColor(mStrokeColor);
            canvas.drawPath(mPath, mPaint);
            mPaint.setMaskFilter(null);
//            mPaint.setStyle(Paint.Style.STROKE);
//            mPaint.setStrokeWidth(mStrokeWidth);
//            mPaint.setColor(mStrokeColor);
//
//            mPath.reset();
//            mPath.addRoundRect(mStrokeRectF, mStrokeRadii, Path.Direction.CCW);
//            canvas.drawPath(mPath, mPaint);
        }
    }



    public void setRadius(float radiusDp) {

        float radiusPx = radiusDp;
        mRadiusTopLeft = radiusPx;
        mRadiusTopRight = radiusPx;
        mRadiusBottomLeft = radiusPx;
        mRadiusBottomRight = radiusPx;
        setData();
    }





    public void setRadius(float radiusTopLeftDp, float radiusTopRightDp, float radiusBottomLeftDp, float radiusBottomRightDp) {

        mRadiusTopLeft = radiusTopLeftDp;
        mRadiusTopRight = radiusTopRightDp;
        mRadiusBottomLeft = radiusBottomLeftDp;
        mRadiusBottomRight = radiusBottomRightDp;
        setData();
    }

    BlurMaskFilter blurMaskFilter;
    public void setStrokeWidth(float widthDp) {

        blurMaskFilter = new BlurMaskFilter(widthDp, BlurMaskFilter.Blur.OUTER);

        mStrokeWidth = widthDp;
        setData();
        onSizeChanged(mWidth, mHeight);
    }

    public void setStrokeColor(int color) {
        mStrokeColor = color;
    }

    public void setStrokeWidthColor(float widthDp, int color) {
        mStrokeWidth = widthDp;
        mStrokeColor = color;
        setData();
        onSizeChanged(mWidth, mHeight);
    }
}
