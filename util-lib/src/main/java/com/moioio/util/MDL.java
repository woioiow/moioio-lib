package com.moioio.util;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class MDL {


    public LinkedHashMap<String,Object> hashMap;

    public MDL()
    {
        hashMap = new LinkedHashMap<>();
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        Set<String> keys = hashMap.keySet();
        if(keys!=null)
        {
            Iterator<String> iterator = keys.iterator();
            while(iterator.hasNext())
            {
                String key = iterator.next();
                Object value = hashMap.get(key);
                sb.append(key);
                sb.append(":");
                sb.append(value);
                sb.append(",");
            }

            sb.deleteCharAt(sb.length()-1);
        }

        sb.append("]");
        return sb.toString();
    }

    public Object get(String key)
    {
        Object ret = null;
        if(hashMap.containsKey(key))
        {
            ret = hashMap.get(key);
        }

        return ret;
    }

    public String getString(String key)
    {
        String ret = String.valueOf(get(key));
        return ret;
    }

    public int getInt(String key)
    {
        int ret = DataUtil.getInt(getString(key));
        return ret;
    }


    public static MDL parse(String str)
    {
        MDL mdl = new MDL();
        if(str.startsWith("["))
        {
            int start = str.indexOf("[");
            int end = str.lastIndexOf("]");
            String mdlStr = str.substring(start+1,end);
            String[] dbs = mdlStr.split(",");
            if(dbs!=null)
            {
                for (String db:dbs)
                {
                    String[] kvs = db.split(":");
                    mdl.hashMap.put(kvs[0],kvs[1]);
                }
            }
        }
        return mdl;
    }

    public static class Builder
    {
        MDL mdl;
        private Builder()
        {
            mdl = new MDL();
        }

        public static Builder create()
        {
            Builder builder = new Builder();
            return builder;
        }

        public <T> Builder add(String key,T t)
        {
            mdl.hashMap.put(key,t);
            return this;
        }

        public String toString()
        {
            return mdl.toString();
        }

        public MDL get()
        {
            return mdl;
        }

    }


}
