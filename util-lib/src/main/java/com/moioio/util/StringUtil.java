package com.moioio.util;

import java.util.Vector;

public class StringUtil
{
    static String STRING_NULL = "";
    static String STRING = "";
    static String T1 = "&#x";

    static String T2 = "\\n";

    static String T3 = "&copy;";

    static String T3_ = "(c)";

    static String T4 = "&amp;";

    static String T4_ = "&";

    static String T5 = "&lt;";

    static String T5_ = "<";

    static String T6 = "&gt;";

    static String T6_ = ">";

    static String T7 = "&nbsp;";

    static String T7_ = " ";

    static String T8 = "&apos;";

    static String T8_ = "'";

    static String T9 = "&quot;";

    static String T9_ = "\"";

    static String T10 = "&#039;";

    static String T10_ = "'";

    static String T11 = "&#32;";

    static String T11_ = " ";

    static String T12 = "&#8226;";

    static String T12_ = "\u25aa";


    static String T13= "&#169;";

    static String T13_ = "(c)";

    static String T14 = "..";

//    static String T15 = ".news";

    static String DOCMENT = "";

    static String DTD_ELEMENT = "ELEMENT";

    static String DTD_CDATA = "CDATA";

    static String DTD_EMPTY = "EMPTY";


    static String COMMET_END = "-->";

    static String TAG_END = "</";
    static String TAG_HEX = "0x";

    static String UTF_8 = "utf-8";



    public static boolean isNull(String css) {
        if (css == null || css.equals(STRING_NULL) || css.length() == 0) {
            return true;
        }
        return false;
    }

    public static String unicodeToString(String str) {
        return str;
    }





    public static String getTagString(String line,int stag,int etag)
    {
        if(!isNull(line))
        {
            int start = line.indexOf(stag);
            int end = line.indexOf(etag);
            return line.substring(start+1, end);
        }
        return line;
    }

    public static String getTagString(String line)
    {
        if(!isNull(line))
        {
            int start = line.indexOf('<');
            int end = line.indexOf('>');
            return line.substring(start+1, end);
        }
        return line;
    }



    public static String convertString(String s)
    {
        if (s == null)
        {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        s = s.toLowerCase();
        int size = s.length();
        for (int i = 0; i < size; i++)
        {
            char c = s.charAt(i);
            int type = Character.getType(c);
            if (type == Character.LOWERCASE_LETTER
                    || type == Character.UPPERCASE_LETTER
                    || type == Character.DECIMAL_DIGIT_NUMBER)
            {
                sb.append(c);
            } else
            {
//				sb.append('_');
            }
        }

        return sb.toString();
    }




    public static String[] getDevilString(String s, char s1) {
        String[] back = null;
        try {
            int i = 0;
            int j = 0;
            Vector<String> stringbuffer = new Vector<String>();
            while ((j = s.indexOf(s1, i)) >= 0) {
                stringbuffer.addElement(s.substring(i, j));
                i = j + 1;
            }
            if (i < s.length()) {
                stringbuffer.addElement(s.substring(i, s.length()));
            }

            back = new String[stringbuffer.size()];
            stringbuffer.copyInto(back);
            stringbuffer = null;
        } catch (Exception e) {
        }
        return back;
    }


    public static String replaceAll(String s, String s1, String s2, boolean ignoreCase) {
        String tmp = "";
        if (s == null || s1 == null || s2 == null) {
            return s;
        }
        int j;
        if (ignoreCase) {
            s1 = s1.toLowerCase();
            int i;
            while ((i = s.toLowerCase().indexOf(s1)) != -1) {
                String s3 = s.substring(0, i);
                String s5 = s.substring(i + s1.length());
                tmp = tmp + s3 + s2;
                s = s5;
            }
        } else {
            while ((j = s.indexOf(s1)) != -1) {
                String s4 = s.substring(0, j);
                String s6 = s.substring(j + s1.length());
                tmp = tmp + s4 + s2;
                s = s6;
            }
        }
        return tmp + s;
    }




}
