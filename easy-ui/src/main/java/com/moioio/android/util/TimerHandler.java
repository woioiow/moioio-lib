package com.moioio.android.util;

import android.os.Handler;
import android.os.Message;

public class TimerHandler extends Handler
{
	
	private Runnable runnable;
	private long delayMillis;
	private boolean isRuning;
	private int id;


	public TimerHandler(Runnable run, long de, int id)
	{
		this.runnable = run;
		this.delayMillis = de;
		this.id = id;
	}

	public void setDelay(long mm)
	{
		this.delayMillis = mm;
	}

	public void handleMessage(Message msg)
	{
//		Util.debug("000000000000000000000000000000000000000000----------------");
		runnable.run();
		sleep();
	}


	public void stop()
	{
		this.removeMessages(id);
		isRuning = false;
	}

	public void sleep()
	{
		this.removeMessages(id);
		if(isRuning)
		{
			sendMessageDelayed(obtainMessage(id), delayMillis);
		}
	}

	public void start()
	{
		isRuning = true;
		sleep();
	}	

}
