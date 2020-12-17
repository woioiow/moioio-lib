package com.moioio.android.loader;

public interface ResourceLoaderListener {




    boolean isCancel();

    boolean isLoading();

    void startLoading();

    void endLoading();

    void onFinish(String url,int type,String path);

    void loading(long total, long current);
}
