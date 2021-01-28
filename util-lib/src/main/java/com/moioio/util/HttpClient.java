package com.moioio.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpClient {


//    static int BUFFER_SIZE = 20*1024;


    class KeyVal
    {
        String name;
        String value;

        public KeyVal(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }




    List<KeyVal> params = new ArrayList<>();
    List<KeyVal> headers = new ArrayList<>();


    public void addParam(String name,String value)
    {
        params.add(new KeyVal(name,value));
    }

    public void addHeader(String name,String value)
    {
        headers.add(new KeyVal(name,value));
    }



    String makeUrl(String baseUrl)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(baseUrl);

        if(params.size()>0)
        {
            if(!baseUrl.contains("?"))
            {
                sb.append("?");
            }

            int index = 0;
            for(KeyVal keyVal:params)
            {
                sb.append(keyVal.name);
                sb.append("=");
                sb.append(keyVal.value);
                if(index<params.size()-1)
                {
                    sb.append("&");
                }
                index++;
            }
        }

        return sb.toString();
    }


    void makeHeader(HttpURLConnection http)
    {
        if(params.size()>0)
        {
            for(KeyVal keyVal:headers)
            {
                http.addRequestProperty(keyVal.name,keyVal.value);
            }
        }
    }

    public void doPost(String baseUrl, byte[] data, HttpClientListener listener)
    {

    }




    public byte[] doGet(String baseUrl)
    {
        byte[] bs = null;
        boolean isOK = false;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[UtilConf.BUFFER_SIZE];
        int size = 0;

        try {
            String destUrl = makeUrl(baseUrl);
            HttpURLConnection.setFollowRedirects(true);
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            makeHeader(httpUrl);
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int total = httpUrl.getContentLength();
            int length = 0;

            while ((size = bis.read(buf)) != -1) {
                length += size;
                os.write(buf, 0, size);
            }
            bs = os.toByteArray();
            os.close();
            bis.close();
            buf = null;

        } catch (Exception e) {
        }
        finally
        {
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }
        return bs;
    }





    public interface HttpClientListener {
        void loading(long total, long current);
        boolean isStop();
        void onFinishSave(String path);
        void onFinishData(byte[] bs);
        void onStop();
        void onError(Exception e);
    }


}
