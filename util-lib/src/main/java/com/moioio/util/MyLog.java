package com.moioio.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map;

public class MyLog {

    public static boolean DEBUG = true;

    public static void debug(Object obj)
    {
        if(DEBUG)
        {
            System.out.println("mylog---"+obj);
        }
    }



    public static void showAllStackTraces()
    {
        if(!MyLog.DEBUG)
        {
            return;
        }
        try
        {
            Map<Thread, StackTraceElement[]> traces =  Thread.getAllStackTraces();

            if(traces.size()>0)
            {
                Iterator<StackTraceElement[]> iterator = traces.values().iterator();
                while(iterator.hasNext())
                {
                    StackTraceElement[] es = iterator.next();
                    for(StackTraceElement stack:es)
                    {
                        MyLog.debug(stack);
                    }

                }
            }
        }
        catch (Exception e)
        {
        }
    }

    public static void printStackTrace(Exception e)
    {
        if(!MyLog.DEBUG)
        {
            return;
        }
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(baos);
            e.printStackTrace(printStream);
            byte[] data = baos.toByteArray();
            baos.close();
            String content = new String(data);
            String[] lines = content.split("\n");
            for(String line:lines)
            {
                MyLog.debug(line);
            }
        }
        catch (Exception ee)
        {
        }
    }

    public static void printStackTrace()
    {
        if(!MyLog.DEBUG)
        {
            return;
        }
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(baos);
            new Throwable().printStackTrace(printStream);
            byte[] data = baos.toByteArray();
            baos.close();
            String content = new String(data);
            String[] lines = content.split("\n");
            for(String line:lines)
            {
                MyLog.debug(line);
            }
        }
        catch (Exception e)
        {
        }
    }


}
