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
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DashPathEffect;
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

    Canvas grap ;
    public Paint paint = new Paint();
    public TextPaint textPaint = new TextPaint();



//	private Path path = new Path();
	
	private RectF clip  = new RectF();

	private RectF rect = new RectF();
	private RectF oval = new RectF();
	private Matrix matrix = new Matrix();
    private DashPathEffect dashPathEffect = new DashPathEffect(new float[]{7, 7}, 0);


    public Graphics()
    {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);

        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
    }

     public Graphics(Canvas canvas)
     {
         grap = canvas;
         paint = new Paint();
         paint.setAntiAlias(true);
         paint.setDither(true);

         textPaint = new TextPaint();
         textPaint.setAntiAlias(true);
         textPaint.setDither(true);

     }
     //09-28 16:48:05.894: W/System.err(1356): java.lang.IllegalStateException: Immutable bitmap passed to Canvas constructor


     
     public void setCanvas(Canvas canvas) 
     {
         grap = canvas;
     }
     

    public void drawScaleImage(Bitmap img, float x, float y, float sx, float sy) {
		matrix.reset();
		matrix.setScale(sx, sy);
		matrix.postTranslate(x, y);
		grap.drawBitmap(img,matrix, paint);
    }

    public void drawImage(Bitmap img, float x, float y) {
    	grap.drawBitmap(img, x, y, paint);
    }
    
    public void setXfermode(int type)
    {
//        gra
//        grap2d.setComposite(AlphaComposite.getInstance(AlphaComposite.Xor, 0.5f));
    }
    

    public void setColor(int ARGB) {
		paint.setColor(ARGB);
        textPaint.setColor(ARGB);
    }

    public void setAlpha(float value) {
        int alpha = (int)(255*value);
        paint.setAlpha(alpha);
        textPaint.setAlpha(alpha);
    }



    public void setMaskFilter(BlurMaskFilter blur) {
        paint.setMaskFilter(blur);
        textPaint.setMaskFilter(blur);

    }



    public void fillRect(float x, float y, float width, float height) {
		paint.setStyle(Paint.Style.FILL);
		grap.drawRect(x, y, x + width, y + height, paint);

    }
    
    public void fillRoundRect(float x, float y, float width, float height,
                              float arcWidth, float arcHeight)
    {
		paint.setStyle(Paint.Style.FILL);
	   	rect.set(x, y, x + width, y + height);
	   	grap.drawRoundRect(rect, arcWidth, arcHeight, paint);
    }    
   
    public void fillArc(float x, float y, float width, float height, int startAngle,
                    int arcAngle)
    {
	   	paint.setStyle(Paint.Style.FILL);
	    rect.set(x, y, x + width, y + height);
	    grap.drawArc(rect, -(startAngle+arcAngle), arcAngle, true, paint);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle,
                        int arcAngle)
    {
        paint.setStyle(Paint.Style.STROKE);
        rect.set(x, y, x + width, y + height);
        grap.drawArc(rect, -(startAngle+arcAngle), arcAngle, true, paint);
    }

    public void drawLine(float x1, float y1, float x2, float y2)
    {
		paint.setStyle(Paint.Style.STROKE);
		grap.drawLine(x1, y1, x2, y2, paint);
    }

    public void fillLine(float x, float y, float w, float h)
    {
        paint.setStyle(Paint.Style.STROKE);
        grap.drawLine(x, y, x+w, y+h, paint);
    }



    public void drawRotateImage(Bitmap img, int x, int y, int r, float angle) {

		matrix.reset();
		matrix.setRotate(angle);
		matrix.postTranslate(x, y);
		grap.drawBitmap(img,matrix, paint);
    }

    public void setClip(int x, int y, int width, int height)
    {
		clip.set(x, y, x + width, y + height);
//        grap.clipRect(clip, Region.Op.REPLACE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
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
        clip.set(x, y, x + width, y + height);
        if(Build.VERSION.SDK_INT >= 26){
            grap.clipOutRect(clip );//p, android.graphics.Region.Op.REPLACE
        }else {
            grap.clipRect(clip, op);
        }
    }


    public void setColor(int A, int R, int G, int B)
    {
    	paint.setARGB(A, R, G, B);
    }

	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		paint.setStyle(Paint.Style.STROKE);
	   	rect.set(x, y, x + width, y + height);
	   	grap.drawRoundRect(rect, arcWidth, arcHeight, paint);
	}

	public void drawRect(float x, float y, float width, float height) {
		paint.setStyle(Paint.Style.STROKE);
		grap.drawRect(x, y, x + width, y + height, paint);
	}

    public void drawRect(RectF rectF,Matrix matrix) {
        paint.setStyle(Paint.Style.STROKE);
    }


    public void drawDashRect(float x, float y, float width, float height) {
        paint.setStyle(Paint.Style.STROKE);
        PathEffect pathEffect =paint.getPathEffect();
        paint.setPathEffect(dashPathEffect);
        grap.drawRect(x, y, x + width, y + height, paint);
        paint.setPathEffect(pathEffect);
    }

    public void fillOval(float x, float y, float width, float height)
    {
		paint.setStyle(Paint.Style.FILL);
    	oval.set(x, y, x + width, y + height);
    	grap.drawOval(oval, paint);
    }

    public void fillCircle(float x, float y, float radius)
    {
        paint.setStyle(Paint.Style.FILL);
        grap.drawCircle(x,y,radius, paint);
    }

    public void drawOval(int x, int y, int width, int height)
    {
		paint.setStyle(Paint.Style.STROKE);
    	oval.set(x, y, x + width, y + height);
    	grap.drawOval(oval, paint);
        
    }


    public void setFontSize(float anInt) {
        paint.setTextSize(anInt);
        textPaint.setTextSize(anInt);

    }

    public float getFontSize()
    {
        return textPaint.getTextSize();
    }

    public void setTypeface(Typeface typeface) {
        paint.setTypeface(typeface);
        textPaint.setTypeface(typeface);
    }

    Rect fontTmpRect = new Rect();
    public void drawString(String str,float x,float y) {
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.getTextBounds(str, 0, str.length(), fontTmpRect);
        float dx = x  - fontTmpRect.left;
        float dy = y + fontTmpRect.height() - fontTmpRect.bottom;
        grap.drawText(str,dx,dy,textPaint);
    }


    public void drawStrokeString(String str,float x,float y,float width) {
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(width);
        textPaint.getTextBounds(str, 0, str.length(), fontTmpRect);
        float dx = x  - fontTmpRect.left;
        float dy = y + fontTmpRect.height() - fontTmpRect.bottom;
        grap.drawText(str,dx,dy,textPaint);
        textPaint.setStrokeWidth(0);
    }


    public int getStringWidth(String str)
    {
        Rect rect = new Rect();
        textPaint.getTextBounds(str, 0, str.length(), rect);
//        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }

    public int getStringHeight(String str)
    {
        Rect rect = new Rect();
        textPaint.getTextBounds(str, 0, str.length(), rect);
//        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }


    public void drawImage(Bitmap bmp, Matrix matrix) {
        grap.drawBitmap(bmp,matrix,paint);
    }

    public void fillPath(Path path) {

        paint.setStyle(Paint.Style.FILL);
        grap.drawPath(path, paint);
    }



    public void setColorMatrix(ColorMatrix colorMatrix) {

        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        textPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

    }

    public void setFontSpace(float space) {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            textPaint.setLetterSpacing(space);
        }
    }

    public void setFakeBoldText(boolean b) {
        textPaint.setFakeBoldText(b);

    }

    public void setTextSkewX(float v) {
        textPaint.setTextSkewX(v);
    }

    public void setUnderlineText(boolean b) {
        textPaint.setUnderlineText(b);
    }

    public void setShader(Shader shader) {
        paint.setShader(shader);
        textPaint.setShader(shader);
    }

    public void setStrokeWidth(float width) {
        textPaint.setStrokeWidth(width);
    }

    public void setMatrix(Matrix matrix) {
        grap.setMatrix(matrix);
    }

    public void setShadowLayer(float radius, float dx, float dy,  int shadowColor) {
        paint.setShadowLayer(radius,dx,dy,shadowColor);
        textPaint.setShadowLayer(radius,dx,dy,shadowColor);
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

//    public ColorMatrix getColorMatrix() {
//        return colorMatrix;
//    }
}
