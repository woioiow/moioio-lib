package com.moioio.util;

import java.util.Random;

public class RandomUtil {

    public static int getRandom(int size) {
        Random rand = new Random();
        int which = Math.abs(rand.nextInt()%size);
        return which;
    }
    public static String getRandomStr(String[] letters)
    {
        Random rand = new Random();
        int which = Math.abs(rand.nextInt()%letters.length);
        String s = letters[which];
        return s;
    }

    public static int getRandomRate(float[] rates)
    {
        int which = 0;
        if(rates!=null&&rates.length>0)
        {
            double randomNumber;
            randomNumber = Math.random();

            float currentRate = 0.0f;
            for(int i=0;i<rates.length;i++)
            {
                if(randomNumber>=currentRate&&randomNumber<=(currentRate+rates[i]))
                {
                    which = (int)(rates[i]*100);
                    break;
                }
                currentRate += rates[i];
            }
        }
        return which;
    }


}
