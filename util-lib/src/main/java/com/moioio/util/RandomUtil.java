package com.moioio.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {
    static Random rand = new Random();


    public static void makeRandomList(List list)
    {
        if(list!=null&&list.size()>1)
        {
            int[] nums = getRandomArray(0,list.size()-1,list.size());
            if(nums!=null)
            {
                List randList = new ArrayList();
                for(int index:nums)
                {
                    randList.add(list.get(index));
                }
                list.clear();
                for(Object o:randList)
                {
                    list.add(o);
                }
                randList.clear();
                randList = null;
            }
        }
    }



    public static int[] getRandomArray(int min,int max,int n){
        int len = max-min+1;

        if(max < min || n > len){
            return null;
        }

        int[] source = new int[len];
        for (int i = min; i < min+len; i++){
            source[i-min] = i;
        }

        int[] result = new int[n];
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            index = Math.abs(rand.nextInt() % len--);
            result[i] = source[index];
            source[index] = source[len];
        }
        return result;
    }


    public static int getRandom(int size) {
        int which = Math.abs(rand.nextInt()%size);
        return which;
    }

    public static int getRandomNoSign(int size) {
        int which = rand.nextInt()%size;
        return which;
    }

    public static String getRandomStr(String[] letters)
    {
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
