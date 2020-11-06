package com.moioio.util;

public class PhysicalUtil {


    public static float getDistance(float x1,float y1,float x2, float y2){

        float _x = Math.abs(x2 - x1);
        float _y = Math.abs(y2 - y1);
        float distance = (float) Math.sqrt(_x*_x+_y*_y);
        return distance;
    }


    public static float getAngle(float cx,float cy,float x, float y){
        float angle = (float)Math.toDegrees(Math.atan2(y -cy,x-cx));
        if(angle< 0){
            angle += 360;
        }
        return angle;
    }

    public static float getAntiAngle(float angle){
        float antiAngel = (180+angle)%360;
        return antiAngel;
    }

    public static boolean pointInRect(float rx,float ry,float rw,float rh,float x,float y)
    {
        return x >= rx && x < (rx+rw) && y >= ry && y < (ry+rh);
    }

    public static boolean rectWithRect(float x1, float y1, float w1, float h1,
                                float x2,float y2, float w2, float h2) {
        if (x1 >= x2 && x1 >= x2 + w2) {
            return false;
        } else if (x1 <= x2 && x1 + w1 <= x2) {
            return false;
        } else if (y1 >= y2 && y1 >= y2 + h2) {
            return false;
        } else if (y1 <= y2 && y1 + h1 <= y2) {
            return false;
        }
        return true;
    }


    public static boolean circleWithRect(float cx, float cy, float radius,
                                         float rx, float ry, float rw, float rh) {

        // temporary variables to set edges for testing
        float testX = cx;
        float testY = cy;

        // which edge is closest?
        if (cx < rx)         testX = rx;      // test left edge
        else if (cx > rx+rw) testX = rx+rw;   // right edge
        if (cy < ry)         testY = ry;      // top edge
        else if (cy > ry+rh) testY = ry+rh;   // bottom edge

        // get distance from closest edges
        float distX = cx-testX;
        float distY = cy-testY;
        float distance = (float) Math.sqrt( (distX*distX) + (distY*distY) );

        // if the distance is less than the radius, collision!
        if (distance <= radius) {
            return true;
        }
        return false;
    }


}
