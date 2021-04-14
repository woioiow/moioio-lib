package com.moioio.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.view.Gravity;
import android.view.View;

import com.moioio.android.g2d.Graphics;

public class BitmapUtil {

    public static final Bitmap bitmapToGary(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap faceIconGreyBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return faceIconGreyBitmap;
    }

    public static Bitmap getScaleBitmap(Bitmap bitmap, float f) {
        int dstWidth = (int)(bitmap.getWidth()*f);
        int dstHeight = (int)(bitmap.getHeight()*f);
        return Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, true);
    }


    public static Bitmap getCircleBitmap(Bitmap bmp) {
        int w = bmp.getWidth();
        int h = bmp.getHeight();
        int r = Math.min(w, h);

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Bitmap newBitmap = Bitmap.createBitmap(r, r, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(newBitmap);

        BitmapShader bitmapShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        paint.setShader(bitmapShader);

        canvas.drawCircle(r / 2, r / 2, r / 2, paint);

        return newBitmap;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap,float roundPx){

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int r = Math.min(w, h);

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Bitmap newBitmap = Bitmap.createBitmap(r, r, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(newBitmap);

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        paint.setShader(bitmapShader);

        final Rect rect = new Rect(0, 0, w, h);
        final RectF rectF = new RectF(rect);

        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);


//        canvas.drawCircle(r / 2, r / 2, r / 2, paint);

        return newBitmap;
    }


    public static Bitmap getAssetsBitmap(Context context, String string) {
        byte[] data = AppFileUtil.getAssetsData(context, string);
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        data = null;
        return bmp;
    }

    public static Bitmap getFileBitmap(String path) {

        Bitmap bmp = BitmapFactory.decodeFile(path);
        return bmp;
    }

    public static Bitmap getScaleBitmap(Bitmap bitmap,int w,int h) {

        Bitmap bmp = Bitmap.createScaledBitmap(bitmap,w,h,true);
        return bmp;
    }

    public static Bitmap changeBitmapColor(Bitmap bitmap,int color) {

        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        int a = Color.alpha(color);

        float[] colorTransform = {
                1, 0f, 0, 0, r,
                0, 1, 0f, 0, g,
                0, 0, 1, 0f, b,
                0, 0, 0, 1f, a};

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(colorTransform); //Apply the Red

        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);

        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        Graphics grap = new Graphics(canvas);
        grap.setColorFilter(colorFilter);
        grap.drawImage(bitmap,0,0);


        return newBitmap;
    }


    public static Bitmap getBitmapByCanvas(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        if (Build.VERSION.SDK_INT >= 11) {
            view.measure(
                    View.MeasureSpec.makeMeasureSpec(
                            view.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(
                            view.getHeight(), View.MeasureSpec.EXACTLY)
            );
        } else {
            view.measure(
                    View.MeasureSpec.makeMeasureSpec(
                            0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(
                            0, View.MeasureSpec.UNSPECIFIED)
            );
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
        view.draw(canvas);
        return bitmap;
    }


}
