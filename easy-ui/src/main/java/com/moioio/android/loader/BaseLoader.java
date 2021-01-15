package com.moioio.android.loader;

import android.content.Context;

import com.moioio.util.Element;
import com.moioio.util.MyLog;

public abstract class BaseLoader {

    private Context context;

    private boolean isLoading;

    private boolean isLoaded;

    public BaseLoader(Context context)
    {
        this.context  = context;
    }


    public void execute()
    {
        if(!isLoading&&!isLoaded)
        {
            isLoading = true;
            new Thread(()->{
                doLoad();
                isLoading = false;
            }).start();
        }
    }

    public void executeSync()
    {
        if(!isLoading&&!isLoaded)
        {
            isLoading = true;
            doLoad();
            isLoading = false;
        }
    }



    public void doLoad()
    {
        Object feedback = load();
        if(feedBackListener!=null)
        {
            feedBackListener.onFeedback(this,feedback);
        }
    }


    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    protected abstract Object load() ;

    private LoaderFeedBackListener feedBackListener;

    public void setProtocolFeedBackListener(LoaderFeedBackListener feedBackListener) {
        this.feedBackListener = feedBackListener;
    }

    public Context getContext() {
        return context;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }


    public void loadOK() {
        setLoaded(true);
    }

}
