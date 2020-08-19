package com.moioio.android.util;

public class ShapeUtil {



    public static boolean collisionRound(float rx,float ry,float r,float x,float y)
    {
        boolean isOk = false;

        float a = (float) Math.pow(Math.abs(x - rx), 2);
        float b = (float) Math.pow(Math.abs(y - ry), 2);

//        MyLog.debug("a----:"+a);
//        MyLog.debug("b----:"+b);
//        MyLog.debug("Math.sqrt( a+b )----:"+Math.sqrt( a+b ));


        if (Math.sqrt( a+b )<= r) {
            isOk = true;
        }
        return isOk;
    }



}
