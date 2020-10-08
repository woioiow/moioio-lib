package com.moioio.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class StringUtil
{
    static String STRING_NULL = "";

    public static String STRING = "";

    public static String T1 = "&#x";

    public static String T2 = "\\n";

    public static String T3 = "&copy;";

    public static String T3_ = "(c)";

    public static String T4 = "&amp;";

    public static String T4_ = "&";

    public static String T5 = "&lt;";

    public static String T5_ = "<";

    public static String T6 = "&gt;";

    public static String T6_ = ">";

    public static String T7 = "&nbsp;";

    public static String T7_ = " ";

    public static String T8 = "&apos;";

    public static String T8_ = "'";

    public static String T9 = "&quot;";

    public static String T9_ = "\"";

    public static String T10 = "&#039;";

    public static String T10_ = "'";

    public static String T11 = "&#32;";

    public static String T11_ = " ";

    public static String T12 = "&#8226;";

    public static String T12_ = "\u25aa";


    public static String T13= "&#169;";

    public static String T13_ = "(c)";

    public static String T14 = "..";

//    public static String T15 = ".news";

    public static String DOCMENT = "";

    public static String DTD_ELEMENT = "ELEMENT";

    public static String DTD_CDATA = "CDATA";

    public static String DTD_EMPTY = "EMPTY";


    public static String COMMET_END = "-->";

    public static String TAG_END = "</";
    public static String TAG_HEX = "0x";

    public static String UTF_8 = "utf-8";


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


    public static final long MILLIS_PER_SECOND = 1000;
    /**
     * Number of milliseconds in a standard minute.
     * @since 2.1
     */
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    /**
     * Number of milliseconds in a standard hour.
     * @since 2.1
     */
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
    /**
     * Number of milliseconds in a standard day.
     * @since 2.1
     */
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    /**
     * This is half a month, so this represents whether a date is in the top
     * or bottom half of the month.
     */
    public static final int SEMI_MONTH = 1001;


    public static String getFormatTime(long time, String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = new Date();
        d.setTime(time);
        String str = sdf.format(d);
        return str;
    }

    public static String getFormatDuration(long durationMillis, String format)
    {

        final Token[] tokens = lexx(format);

        long days         = 0;
        long hours        = 0;
        long minutes      = 0;
        long seconds      = 0;
        long milliseconds = durationMillis;

        if (Token.containsTokenWithValue(tokens, d) ) {
            days = milliseconds / MILLIS_PER_DAY;
            milliseconds = milliseconds - (days * MILLIS_PER_DAY);
        }
        if (Token.containsTokenWithValue(tokens, H) ) {
            hours = milliseconds / MILLIS_PER_HOUR;
            milliseconds = milliseconds - (hours * MILLIS_PER_HOUR);
        }
        if (Token.containsTokenWithValue(tokens, m) ) {
            minutes = milliseconds / MILLIS_PER_MINUTE;
            milliseconds = milliseconds - (minutes * MILLIS_PER_MINUTE);
        }
        if (Token.containsTokenWithValue(tokens, s) ) {
            seconds = milliseconds / MILLIS_PER_SECOND;
            milliseconds = milliseconds - (seconds * MILLIS_PER_SECOND);
        }

        return format(tokens, 0, 0, days, hours, minutes, seconds, milliseconds, true);


    }

    static String format(final Token[] tokens, final long years, final long months, final long days, final long hours, final long minutes, final long seconds,
                         final long milliseconds, final boolean padWithZeros) {
        final StringBuilder buffer = new StringBuilder();
        boolean lastOutputSeconds = false;
        for (final Token token : tokens) {
            final Object value = token.getValue();
            final int count = token.getCount();
            if (value instanceof StringBuilder) {
                buffer.append(value.toString());
            } else {
                if (value.equals(y)) {
                    buffer.append(paddedValue(years, padWithZeros, count));
                    lastOutputSeconds = false;
                } else if (value.equals(M)) {
                    buffer.append(paddedValue(months, padWithZeros, count));
                    lastOutputSeconds = false;
                } else if (value.equals(d)) {
                    buffer.append(paddedValue(days, padWithZeros, count));
                    lastOutputSeconds = false;
                } else if (value.equals(H)) {
                    buffer.append(paddedValue(hours, padWithZeros, count));
                    lastOutputSeconds = false;
                } else if (value.equals(m)) {
                    buffer.append(paddedValue(minutes, padWithZeros, count));
                    lastOutputSeconds = false;
                } else if (value.equals(s)) {
                    buffer.append(paddedValue(seconds, padWithZeros, count));
                    lastOutputSeconds = true;
                } else if (value.equals(S)) {
                    if (lastOutputSeconds) {
                        // ensure at least 3 digits are displayed even if padding is not selected
                        final int width = padWithZeros ? Math.max(3, count) : 3;
                        buffer.append(paddedValue(milliseconds, true, width));
                    } else {
                        buffer.append(paddedValue(milliseconds, padWithZeros, count));
                    }
                    lastOutputSeconds = false;
                }
            }
        }
        return buffer.toString();
    }


    private static String paddedValue(final long value, final boolean padWithZeros, final int count) {
        final String longString = Long.toString(value);
        return padWithZeros ? leftPad(longString, count, '0') : longString;
    }

    public static String leftPad(final String str, final int size, final char padChar) {
        if (str == null) {
            return null;
        }
        final int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return repeat(padChar, pads).concat(str);
    }

    public static String leftPad(final String str, final int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (StringUtil.isNull(padStr)) {
            padStr = SPACE;
        }
        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return leftPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }


    public static String getFileExtensionFromUrl(String url)
    {
        if (!isNull(url))
        {
            int fragment = url.lastIndexOf('#');
            if (fragment > 0)
            {
                url = url.substring(0, fragment);
            }

            int query = url.lastIndexOf('?');
            if (query > 0)
            {
                url = url.substring(0, query);
            }

            int filenamePos = url.lastIndexOf('/');
            String filename = 0 <= filenamePos ? url.substring(filenamePos + 1) : url;

//            Log.d("filename",filename);

            // if the filename contains special characters, we don't
            // consider it valid for our matching purposes:
            if (!filename.isEmpty())
            {
                int dotPos = filename.lastIndexOf('.');
                if (0 <= dotPos)
                {
                    return filename.substring(dotPos + 1);
                }
            }
        }

        return "";
    }


    public static boolean isHttpUrl(String url)
    {
        boolean isurl = false;
        String regex = url.toLowerCase();
        if (regex.startsWith("http://") || regex.startsWith("https://"))
        {
            isurl = true;
        }
        return isurl;
    }

    static final Object y = "y";
    static final Object M = "M";
    static final Object d = "d";
    static final Object H = "H";
    static final Object m = "m";
    static final Object s = "s";
    static final Object S = "S";

    static Token[] lexx(final String format)
    {
        final ArrayList<Token> list = new ArrayList<>(format.length());

        boolean inLiteral = false;
        // Although the buffer is stored in a Token, the Tokens are only
        // used internally, so cannot be accessed by other threads
        StringBuilder buffer = null;
        Token previous = null;
        for (int i = 0; i < format.length(); i++)
        {
            final char ch = format.charAt(i);
            if (inLiteral && ch != '\'')
            {
                buffer.append(ch); // buffer can't be null if inLiteral is true
                continue;
            }
            Object value = null;
            switch (ch)
            {
                // TODO: Need to handle escaping of '
                case '\'':
                    if (inLiteral)
                    {
                        buffer = null;
                        inLiteral = false;
                    }
                    else
                    {
                        buffer = new StringBuilder();
                        list.add(new Token(buffer));
                        inLiteral = true;
                    }
                    break;
                case 'y':
                    value = y;
                    break;
                case 'M':
                    value = M;
                    break;
                case 'd':
                    value = d;
                    break;
                case 'H':
                    value = H;
                    break;
                case 'm':
                    value = m;
                    break;
                case 's':
                    value = s;
                    break;
                case 'S':
                    value = S;
                    break;
                default:
                    if (buffer == null)
                    {
                        buffer = new StringBuilder();
                        list.add(new Token(buffer));
                    }
                    buffer.append(ch);
            }

            if (value != null)
            {
                if (previous != null && previous.getValue().equals(value))
                {
                    previous.increment();
                }
                else
                {
                    final Token token = new Token(value);
                    list.add(token);
                    previous = token;
                }
                buffer = null;
            }
        }
        if (inLiteral)
        { // i.e. we have not found the end of the literal
            throw new IllegalArgumentException("Unmatched quote in format: " + format);
        }
        return list.toArray(new Token[0]);
    }


    static class Token
    {

        /**
         * Helper method to determine if a set of tokens contain a value
         *
         * @param tokens set to look in
         * @param value  to look for
         * @return boolean {@code true} if contained
         */
        static boolean containsTokenWithValue(final Token[] tokens, final Object value)
        {
            for (final Token token : tokens)
            {
                if (token.getValue() == value)
                {
                    return true;
                }
            }
            return false;
        }

        private final Object value;
        private int count;

        /**
         * Wraps a token around a value. A value would be something like a 'Y'.
         *
         * @param value to wrap
         */
        Token(final Object value)
        {
            this.value = value;
            this.count = 1;
        }

        /**
         * Wraps a token around a repeated number of a value, for example it would
         * store 'yyyy' as a value for y and a count of 4.
         *
         * @param value to wrap
         * @param count to wrap
         */
        Token(final Object value, final int count)
        {
            this.value = value;
            this.count = count;
        }

        /**
         * Adds another one of the value
         */
        void increment()
        {
            count++;
        }

        /**
         * Gets the current number of values represented
         *
         * @return int number of values represented
         */
        int getCount()
        {
            return count;
        }

        /**
         * Gets the particular value this token represents.
         *
         * @return Object value
         */
        Object getValue()
        {
            return value;
        }

        /**
         * Supports equality of this Token to another Token.
         *
         * @param obj2 Object to consider equality of
         * @return boolean {@code true} if equal
         */
        @Override
        public boolean equals(final Object obj2)
        {
            if (obj2 instanceof Token)
            {
                final Token tok2 = (Token) obj2;
                if (this.value.getClass() != tok2.value.getClass())
                {
                    return false;
                }
                if (this.count != tok2.count)
                {
                    return false;
                }
                if (this.value instanceof StringBuilder)
                {
                    return this.value.toString().equals(tok2.value.toString());
                }
                else if (this.value instanceof Number)
                {
                    return this.value.equals(tok2.value);
                }
                else
                {
                    return this.value == tok2.value;
                }
            }
            return false;
        }

        /**
         * Returns a hash code for the token equal to the
         * hash code for the token's value. Thus 'TT' and 'TTTT'
         * will have the same hash code.
         *
         * @return The hash code for the token
         */
        @Override
        public int hashCode()
        {
            return this.value.hashCode();
        }

        /**
         * Represents this token as a String.
         *
         * @return String representation of the token
         */
        @Override
        public String toString()
        {
            return repeat(this.value.toString(), this.count);
        }
    }


    public static final String SPACE = " ";

    /**
     * The empty String {@code ""}.
     *
     * @since 2.0
     */
    public static final String EMPTY = "";

    /**
     * A String for linefeed LF ("\n").
     *
     * @see <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.6">JLF: Escape Sequences
     * for Character and String Literals</a>
     * @since 3.2
     */
    public static final String LF = "\n";

    /**
     * A String for carriage return CR ("\r").
     *
     * @see <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.6">JLF: Escape Sequences
     * for Character and String Literals</a>
     * @since 3.2
     */
    public static final String CR = "\r";

    /**
     * Represents a failed index search.
     *
     * @since 2.1
     */
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * <p>The maximum size to which the padding constant(s) can expand.</p>
     */
    private static final int PAD_LIMIT = 8192;

    public static String repeat(final char ch, final int repeat)
    {
        if (repeat <= 0)
        {
            return EMPTY;
        }
        final char[] buf = new char[repeat];
        for (int i = repeat - 1; i >= 0; i--)
        {
            buf[i] = ch;
        }
        return new String(buf);
    }

    // Padding
    //-----------------------------------------------------------------------

    /**
     * <p>Repeat a String {@code repeat} times to form a
     * new String.</p>
     *
     * <pre>
     * StringUtils.repeat(null, 2) = null
     * StringUtils.repeat("", 0)   = ""
     * StringUtils.repeat("", 2)   = ""
     * StringUtils.repeat("a", 3)  = "aaa"
     * StringUtils.repeat("ab", 2) = "abab"
     * StringUtils.repeat("a", -2) = ""
     * </pre>
     *
     * @param str    the String to repeat, may be null
     * @param repeat number of times to repeat str, negative treated as zero
     * @return a new String consisting of the original String repeated,
     * {@code null} if null String input
     */
    public static String repeat(final String str, final int repeat)
    {
        // Performance tuned for 2.0 (JDK1.4)

        if (str == null)
        {
            return null;
        }
        if (repeat <= 0)
        {
            return EMPTY;
        }
        final int inputLength = str.length();
        if (repeat == 1 || inputLength == 0)
        {
            return str;
        }
        if (inputLength == 1 && repeat <= PAD_LIMIT)
        {
            return repeat(str.charAt(0), repeat);
        }

        final int outputLength = inputLength * repeat;
        switch (inputLength)
        {
            case 1:
                return repeat(str.charAt(0), repeat);
            case 2:
                final char ch0 = str.charAt(0);
                final char ch1 = str.charAt(1);
                final char[] output2 = new char[outputLength];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--)
                {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default:
                final StringBuilder buf = new StringBuilder(outputLength);
                for (int i = 0; i < repeat; i++)
                {
                    buf.append(str);
                }
                return buf.toString();
        }
    }

}
