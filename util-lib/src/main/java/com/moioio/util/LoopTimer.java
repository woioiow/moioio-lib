package com.moioio.util;

public class LoopTimer {

    private Runnable runnable;
    private long delayMillis;
    private boolean isRunning;


    public LoopTimer(Runnable run, long de)
    {
        this.runnable = run;
        this.delayMillis = de;
    }

    public void setDelay(long mm)
    {
        this.delayMillis = mm;
    }


    public void stop()
    {
        isRunning = false;
    }

    public void sleep()
    {
        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start()
    {
        isRunning = true;
        new Thread(()->{

            while (isRunning)
            {

                runnable.run();
                sleep();
            }
        }).start();


        sleep();
    }

    public void setRunnable(Runnable run) {
        runnable = run;
    }

}
