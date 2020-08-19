package com.moioio.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Shader;

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


    public static Bitmap getCirleBitmap(Bitmap bmp) {
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





}
