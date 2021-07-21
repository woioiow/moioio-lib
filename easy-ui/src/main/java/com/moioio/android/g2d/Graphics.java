/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moioio.android.g2d;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.os.Build;
import android.text.TextPaint;



/**
 *
 * @author Thinkpad
 */
public class Graphics
{


    float width;
    float height;

    public Canvas grap ;
    public Paint paint;
//    public TextPaint textPaint = new TextPaint();



//	private Path path = new Path();

    private RectF clip  = new RectF();

    private RectF rect = new RectF();
    private RectF oval = new RectF();
    private Matrix matrix = new Matrix();
    private DashPathEffect dashPathEffect = new DashPathEffect(new float[]{7, 7}, 0);

    private int canvasInitSave;
    public Graphics()
    {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);

//        textPaint = new TextPaint();
//        textPaint.setAntiAlias(true);
//        textPaint.setDither(true);
    }

    public Graphics(Canvas canvas)
    {
        grap = canvas;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        canvasInitSave = canvas.save();
//        textPaint = new TextPaint();
//        textPaint.setAntiAlias(true);
//        textPaint.setDither(true);

    }
    //09-28 16:48:05.894: W/System.err(1356): java.lang.IllegalStateException: Immutable bitmap passed to Canvas constructor



    public void setCanvas(Canvas canvas)
    {
        grap = canvas;
        canvasInitSave = canvas.save();
    }


    public void drawScaleImage(Bitmap img, float x, float y, float sx, float sy) {
        matrix.reset();
        matrix.setScale(sx, sy);
        matrix.postTranslate(x+offX, y+offY);
        grap.drawBitmap(img,matrix, paint);
    }

    public void drawImage(Bitmap img, float x, float y) {
        grap.drawBitmap(img, x+offX, y+offY, paint);
    }

    public void setXfermode(int type)
    {
//        paint.setXfermode()
//        paint.setXfermode()
//        gra
//        grap2d.setComposite(AlphaComposite.getInstance(AlphaComposite.Xor, 0.5f));
    }

    public void setXfermode(Xfermode xfermode)
    {
        paint.setXfermode(xfermode);
//        paint.setXfermode()
//        gra
//        grap2d.setComposite(AlphaComposite.getInstance(AlphaComposite.Xor, 0.5f));
    }



    public void setColor(int ARGB) {
        paint.setColor(ARGB);
//        textPaint.setColor(ARGB);
    }

    public void setAlpha(float value) {
        int alpha = (int)(255*value);
        paint.setAlpha(alpha);
//        textPaint.setAlpha(alpha);
    }



    public void setMaskFilter(BlurMaskFilter blur) {
        paint.setMaskFilter(blur);
//        textPaint.setMaskFilter(blur);

    }

    public void setMaskFilter(MaskFilter blur) {
        paint.setMaskFilter(blur);
//        textPaint.setMaskFilter(blur);

    }



    public void fillRect(float x, float y, float width, float height) {
        paint.setStyle(Paint.Style.FILL);
        grap.drawRect(x+offX, y+offY, x+offX + width, y+offY + height, paint);

    }

    public void fillRoundRect(float x, float y, float width, float height,
                              float arcWidth, float arcHeight)
    {
        paint.setStyle(Paint.Style.FILL);
        rect.set(x+offX, y+offY, x+offX + width, y+offY + height);
        grap.drawRoundRect(rect, arcWidth, arcHeight, paint);
    }

    public void fillArc(float x, float y, float width, float height, float startAngle,
                        float arcAngle)
    {
        paint.setStyle(Paint.Style.FILL);
        rect.set(x+offX, y+offY, x+offX + width, y+offY + height);
//        grap.drawArc(rect, -(startAngle+arcAngle), arcAngle, true, paint);
        grap.drawArc(rect, startAngle, arcAngle, true, paint);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle,
                        int arcAngle)
    {
        paint.setStyle(Paint.Style.STROKE);
        rect.set(x+offX, y+offY, x+offX + width, y+offY + height);
        grap.drawArc(rect, -(startAngle+arcAngle), arcAngle, true, paint);
    }

    public void drawLine(float x1, float y1, float x2, float y2)
    {
        paint.setStyle(Paint.Style.STROKE);
        grap.drawLine(x1+offX, y1+offY, x2+offX, y2+offY, paint);
    }

    public void fillLine(float x, float y, float w, float h)
    {
        paint.setStyle(Paint.Style.STROKE);
        grap.drawLine(x, y, x+w, y+h, paint);
    }



    public void drawRotateImage(Bitmap img, int x, int y, int r, float angle) {

        matrix.reset();
        matrix.setRotate(angle);
        matrix.postTranslate(x+offX, y+offY);
        grap.drawBitmap(img,matrix, paint);
    }

    public void setClip(int x, int y, int width, int height)
    {
        clip.set(x+offX, y+offY, x+offX + width, y+offY + height);
//        grap.clipRect(clip, Region.Op.REPLACE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            grap.restore();
            grap.save();
            grap.clipRect(clip );//p,
        }else {
            grap.clipRect(clip, Region.Op.REPLACE);
        }
    }

    public void save()
    {
        grap.save();
    }

    public void restore()
    {
        grap.restore();
    }


    public void setOutClip(int x, int y, int width, int height, Region.Op op)
    {
        clip.set(x+offX, y+offY, x+offX + width, y+offY + height);
        if(Build.VERSION.SDK_INT >= 26){
            grap.restore();
            grap.save();
            grap.clipOutRect(clip );//p, android.graphics.Region.Op.REPLACE
        }else {
            grap.clipRect(clip, op);
        }
    }


    public void setColor(int A, int R, int G, int B)
    {
        paint.setARGB(A, R, G, B);
    }

    public void drawRoundRect(float x, float y, float width, float height, float arcWidth, float arcHeight) {
        paint.setStyle(Paint.Style.STROKE);
        rect.set(x+offX, y+offY, x+offX + width, y+offY + height);
        grap.drawRoundRect(rect, arcWidth, arcHeight, paint);
    }

    public void drawRect(float x, float y, float width, float height) {
        paint.setStyle(Paint.Style.STROKE);
        grap.drawRect(x+offX, y+offY, x+offX + width, y+offY + height, paint);
    }

    public void drawRect(RectF rectF,Matrix matrix) {
        paint.setStyle(Paint.Style.STROKE);
    }


    public void drawDashRect(float x, float y, float width, float height) {
        paint.setStyle(Paint.Style.STROKE);
        PathEffect pathEffect =paint.getPathEffect();
        paint.setPathEffect(dashPathEffect);
        grap.drawRect(x+offX, y+offY, x+offX + width, y+offY + height, paint);
        paint.setPathEffect(pathEffect);
    }

    public void fillOval(float x, float y, float width, float height)
    {
        paint.setStyle(Paint.Style.FILL);
        oval.set(x+offX, y+offY, x+offX + width, y+offY + height);
        grap.drawOval(oval, paint);
    }

    public void fillCircle(float x, float y, float radius)
    {
        paint.setStyle(Paint.Style.FILL);
        grap.drawCircle(x+offX, y+offY,radius, paint);
    }

    public void drawCircle(float x, float y, float radius)
    {
        paint.setStyle(Paint.Style.STROKE);
        grap.drawCircle(x+offX, y+offY,radius, paint);
    }

    public void drawOval(int x, int y, int width, int height)
    {
        paint.setStyle(Paint.Style.STROKE);
        oval.set(x+offX, y+offY, x+offX + width, y+offY + height);
        grap.drawOval(oval, paint);

    }


    public void setFontSize(float anInt) {
        paint.setTextSize(anInt);
//        textPaint.setTextSize(anInt);

    }

    public float getFontSize()
    {
        return paint.getTextSize();
    }

    public void setTypeface(Typeface typeface) {
        paint.setTypeface(typeface);
//        textPaint.setTypeface(typeface);
    }

    Rect fontTmpRect = new Rect();
    public void drawString(String str,float x,float y) {
        paint.setStyle(Paint.Style.FILL);
        paint.getTextBounds(str, 0, str.length(), fontTmpRect);
        float dx = x+offX  - fontTmpRect.left;
        float dy = y+offY + fontTmpRect.height() - fontTmpRect.bottom;
        grap.drawText(str,dx,dy,paint);
    }


    public void drawStrokeString(String str,float x,float y,float width) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(width);
        paint.getTextBounds(str, 0, str.length(), fontTmpRect);
        float dx = x+offX  - fontTmpRect.left;
        float dy = y+offY + fontTmpRect.height() - fontTmpRect.bottom;
        grap.drawText(str,dx,dy,paint);
        paint.setStrokeWidth(0);
    }


    Rect stringRect = new Rect();
    public int getStringWidth(String str)
    {
        paint.getTextBounds(str, 0, str.length(), stringRect);
//        paint.getTextBounds(str, 0, str.length(), rect);
        return stringRect.width();
    }

    public int getStringHeight(String str)
    {
        paint.getTextBounds(str, 0, str.length(), stringRect);
//        paint.getTextBounds(str, 0, str.length(), rect);
        return stringRect.height();
    }


    public void drawImage(Bitmap bmp, Matrix matrix) {
        grap.drawBitmap(bmp,matrix,paint);
    }

    public void fillPath(Path path) {

        grap.translate(offX,offY);
        paint.setStyle(Paint.Style.FILL);
        grap.drawPath(path, paint);
        grap.translate(-offX,-offY);
    }



    public void setColorMatrix(ColorMatrix colorMatrix) {

        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
    }

    public void setColorFilter(ColorFilter filter) {

        paint.setColorFilter(filter);
    }



    public void setFontSpace(float space) {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            paint.setLetterSpacing(space);
        }
    }

    public void setFakeBoldText(boolean b) {
        paint.setFakeBoldText(b);

    }

    public void setTextSkewX(float v) {
        paint.setTextSkewX(v);
    }

    public void setUnderlineText(boolean b) {
        paint.setUnderlineText(b);
    }

    public void setShader(Shader shader) {
        paint.setShader(shader);
    }

    public void setStrokeWidth(float width) {
        paint.setStrokeWidth(width);
    }

    public void setMatrix(Matrix matrix) {
        grap.setMatrix(matrix);
    }

    public void setShadowLayer(float radius, float dx, float dy,  int shadowColor) {
        paint.setShadowLayer(radius,dx,dy,shadowColor);
    }

    public void setClipArea(Path path)
    {
        grap.clipPath(path);//p,
    }
    public void setClipAreaOut(Path path)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            grap.clipOutPath(path);//p,
        }
    }

    public void drawPath(Path path)
    {
        grap.drawPath(path,paint);
    }

    public Paint getPaint() {
        return paint;
    }

    public void clear() {
        grap.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }


    private float offX;
    private float offY;

    public void translate(float x, float y) {
        offX += x;
        offY += y;
    }

    public Canvas getCanvas() {
        return grap;
    }

//    public ColorMatrix getColorMatrix() {
//        return colorMatrix;
//    }
}
