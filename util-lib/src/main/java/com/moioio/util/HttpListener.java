package com.moioio.util;

public interface HttpListener {
    public void loading(long total, long current);
    public boolean isStop();
}
