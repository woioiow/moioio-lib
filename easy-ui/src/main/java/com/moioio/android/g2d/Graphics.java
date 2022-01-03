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


public class Graphics
{


    private float width;
    private float height;

    public Canvas canvas;
    public Paint paint;

    private RectF clip;

    private RectF rect;
    private RectF oval;
    private Matrix matrix;
    private DashPathEffect dashPathEffect;
    private Matrix pathM;
    private float offX;
    private float offY;
    private Rect stringRect;

    private int canvasInitSave;
    public Graphics()
    {
        stringRect = new Rect();
        oval = new RectF();
        rect = new RectF();
        clip  = new RectF();
        matrix = new Matrix();
        dashPathEffect = new DashPathEffect(new float[]{7, 7}, 0);
        pathM = new Matrix();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
//        textPaint = new TextPaint();
//        textPaint.setAntiAlias(true);
//        textPaint.setDither(true);
    }

    public Graphics(Canvas canvas)
    {
        this();
        this.canvas = canvas;
        canvasInitSave = canvas.save();
//        textPaint = new TextPaint();
//        textPaint.setAntiAlias(true);
//        textPaint.setDither(true);

    }



    public void setCanvas(Canvas canvas)
    {
        this.canvas = canvas;
        canvasInitSave = canvas.save();
    }


    public void drawScaleImage(Bitmap img, float x, float y, float sx, float sy) {
        matrix.reset();
        matrix.setScale(sx, sy);
        matrix.postTranslate(x+offX, y+offY);
        canvas.drawBitmap(img,matrix, paint);
    }

    public void drawImage(Bitmap img, float x, float y) {
        canvas.drawBitmap(img, x+offX, y+offY, paint);
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
        canvas.drawRect(x+offX, y+offY, x+offX + width, y+offY + height, paint);

    }

    public void fillRoundRect(float x, float y, float width, float height,
                              float arcWidth, float arcHeight)
    {
        paint.setStyle(Paint.Style.FILL);
        rect.set(x+offX, y+offY, x+offX + width, y+offY + height);
        canvas.drawRoundRect(rect, arcWidth, arcHeight, paint);
    }

    public void fillArc(float x, float y, float width, float height, float startAngle,
                        float arcAngle)
    {
        paint.setStyle(Paint.Style.FILL);
        rect.set(x+offX, y+offY, x+offX + width, y+offY + height);
//        grap.drawArc(rect, -(startAngle+arcAngle), arcAngle, true, paint);
        canvas.drawArc(rect, startAngle, arcAngle, true, paint);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle,
                        int arcAngle)
    {
        paint.setStyle(Paint.Style.STROKE);
        rect.set(x+offX, y+offY, x+offX + width, y+offY + height);
        canvas.drawArc(rect, -(startAngle+arcAngle), arcAngle, true, paint);
    }

    public void drawLine(float x1, float y1, float x2, float y2)
    {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(x1+offX, y1+offY, x2+offX, y2+offY, paint);
    }

    public void fillLine(float x, float y, float w, float h)
    {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(x+offX, y+offY, x+offX+w, y+offY+h, paint);
    }



    public void drawRotateImage(Bitmap img, int x, int y, int r, float angle) {

        matrix.reset();
        matrix.setRotate(angle);
        matrix.postTranslate(x+offX, y+offY);
        canvas.drawBitmap(img,matrix, paint);
    }

    public void setClip(int x, int y, int width, int height)
    {
        clip.set(x+offX, y+offY, x+offX + width, y+offY + height);
//        grap.clipRect(clip, Region.Op.REPLACE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            canvas.restore();
            canvas.save();
            canvas.clipRect(clip );//p,
        }else {
            canvas.clipRect(clip, Region.Op.REPLACE);
        }
    }

    public void save()
    {
        canvas.save();
    }

    public void restore()
    {
        canvas.restore();
    }


    public void setOutClip(int x, int y, int width, int height, Region.Op op)
    {
        clip.set(x+offX, y+offY, x+offX + width, y+offY + height);
        if(Build.VERSION.SDK_INT >= 26){
            canvas.restore();
            canvas.save();
            canvas.clipOutRect(clip );//p, android.graphics.Region.Op.REPLACE
        }else {
            canvas.clipRect(clip, op);
        }
    }


    public void setColor(int A, int R, int G, int B)
    {
        paint.setARGB(A, R, G, B);
    }

    public void drawRoundRect(float x, float y, float width, float height, float arcWidth, float arcHeight) {
        paint.setStyle(Paint.Style.STROKE);
        rect.set(x+offX, y+offY, x+offX + width, y+offY + height);
        canvas.drawRoundRect(rect, arcWidth, arcHeight, paint);
    }

    public void drawRect(float x, float y, float width, float height) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x+offX, y+offY, x+offX + width, y+offY + height, paint);
    }

    public void drawRect(RectF rectF,Matrix matrix) {
        paint.setStyle(Paint.Style.STROKE);
    }


    public void drawDashRect(float x, float y, float width, float height) {
        paint.setStyle(Paint.Style.STROKE);
        PathEffect pathEffect =paint.getPathEffect();
        paint.setPathEffect(dashPathEffect);
        canvas.drawRect(x+offX, y+offY, x+offX + width, y+offY + height, paint);
        paint.setPathEffect(pathEffect);
    }

    public void fillOval(float x, float y, float width, float height)
    {
        paint.setStyle(Paint.Style.FILL);
        oval.set(x+offX, y+offY, x+offX + width, y+offY + height);
        canvas.drawOval(oval, paint);
    }

    public void fillCircle(float x, float y, float radius)
    {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x+offX, y+offY,radius, paint);
    }

    public void drawCircle(float x, float y, float radius)
    {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(x+offX, y+offY,radius, paint);
    }

    public void drawOval(int x, int y, int width, int height)
    {
        paint.setStyle(Paint.Style.STROKE);
        oval.set(x+offX, y+offY, x+offX + width, y+offY + height);
        canvas.drawOval(oval, paint);

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
        canvas.drawText(str,dx,dy,paint);
    }


    public void drawStrokeString(String str,float x,float y,float width) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(width);
        paint.getTextBounds(str, 0, str.length(), fontTmpRect);
        float dx = x+offX  - fontTmpRect.left;
        float dy = y+offY + fontTmpRect.height() - fontTmpRect.bottom;
        canvas.drawText(str,dx,dy,paint);
        paint.setStrokeWidth(0);
    }


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
        canvas.drawBitmap(bmp,matrix,paint);
    }

    public void fillPath(Path path) {

        paint.setStyle(Paint.Style.FILL);
        if(offX!=0&&offY!=0)
        {
            pathM.reset();
            pathM.setTranslate(offX,offY);
            path.transform(pathM);
        }

        canvas.drawPath(path, paint);

        if(offX!=0&&offY!=0)
        {
            pathM.reset();
            pathM.setTranslate(-offX,-offY);
            path.transform(pathM);
        }
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
        canvas.setMatrix(matrix);
    }

    public void setShadowLayer(float radius, float dx, float dy,  int shadowColor) {
        paint.setShadowLayer(radius,dx,dy,shadowColor);
    }

    public void setClipArea(Path path)
    {
        if(offX!=0&&offY!=0)
        {
            pathM.reset();
            pathM.setTranslate(offX,offY);
            path.transform(pathM);
        }

        canvas.clipPath(path);

        if(offX!=0&&offY!=0)
        {
            pathM.reset();
            pathM.setTranslate(-offX,-offY);
            path.transform(pathM);
        }
    }
    public void setClipAreaOut(Path path)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            if(offX!=0&&offY!=0)
            {
                pathM.reset();
                pathM.setTranslate(offX,offY);
                path.transform(pathM);
            }

            canvas.clipOutPath(path);

            if(offX!=0&&offY!=0)
            {
                pathM.reset();
                pathM.setTranslate(-offX,-offY);
                path.transform(pathM);
            }

        }
    }

    public void drawPath(Path path)
    {
        if(offX!=0&&offY!=0)
        {
            pathM.reset();
            pathM.setTranslate(offX,offY);
            path.transform(pathM);
        }

        canvas.drawPath(path,paint);

        if(offX!=0&&offY!=0)
        {
            pathM.reset();
            pathM.setTranslate(-offX,-offY);
            path.transform(pathM);
        }
    }

    public Paint getPaint() {
        return paint;
    }

    public void clear() {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
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

    public void translate(float x, float y) {
        offX = x;
        offY = y;
    }

    public void reset() {
        offX = 0;
        offY = 0;
    }



    public Canvas getCanvas() {
        return canvas;
    }

//    public ColorMatrix getColorMatrix() {
//        return colorMatrix;
//    }
}
