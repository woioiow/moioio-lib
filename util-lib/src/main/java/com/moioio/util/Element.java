package com.moioio.util;




import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;


public class Element
{
    private String tag;

    private String text;

    private Hashtable<String, String> attrs;

    private Vector<Element> children;

    private Element parent;

    public Element getParent()
    {
        return parent;
    }
    
    public Vector<String> getAttrs()
    {
    	Vector<String> vs = new Vector<String>();
    	
    	Enumeration<String> ems =  attrs.keys();
    	while(ems.hasMoreElements())
    	{
    		vs.addElement(ems.nextElement());
    	}
    	return vs;
    }


    public Element(String name)
    {
        this.tag = name;
        attrs = new Hashtable<String, String>();
        children = new Vector<Element>();
    }

    public Element()
    {
        attrs = new Hashtable<String, String>();
        children = new Vector<Element>();
    }

    
    /**
     * @param name
     * @param value
     */
    public void setAttr(String name, String value)
    {
        this.attrs.put(name, value);
    }

    public void setAttrHash(Hashtable<String, String> hash)
    {
        attrs = hash;
    }    
    public Hashtable<String, String> getAttrHash()
    {
        return attrs;
    }    
    
    /**
     * @param name
     * @return
     */
    public String getAttr(String name)
    {
        if(attrs==null)
        {
            return null;
        }
        String s = (String) attrs.get(name);
        if(s==null)
        {
            s = StringUtil.STRING;
        }
        return s;
    }

    /**
     */
    public void addChild(Element node)
    {
        this.children.addElement(node);
    }

    /**
     * @return the children
     */
    public Vector<Element> getChildren()
    {
        return children;
    }

    public void setChildren(Vector<Element> v)
    {
        children = v;
    }

    
    
    public static Element[] getElements(Vector<Element> v)
    {
        if(v==null)
        {
            return null;
        }
        int size = v.size();
        if(size==0)
        {
            return null;
        }
        Element[] el = new Element[size];
        v.copyInto(el);
        return el;
    }

//    /**
//     * @param children the children to set
//     */
//    public void setChildren(Vector children)
//    {
//        this.children = children;
//    }

    /**
     * @return the tag
     */
    public String getTag()
    {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag)
    {
        this.tag = tag;
    }

    /**
     * @return the text
     */
    public String getText()
    {
        if(text==null)
        {
            return StringUtil.STRING;
        }
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text)
    {
        this.text = text;
    }

    
    public static Hashtable<String, String> dtd = new Hashtable<String, String>();
    
    public static void addDtd(String key,String value)
    {
        dtd.put(key, value);
    }

    public static Element parse(String text)
    {
        if(StringUtil.isNull(text))
        {
            return null;
        }
        Stack<Element> nodeStack = new Stack<Element>();

//        Hashtable dtd = new Hashtable();


        int lenght = text.length();

        int index = 0;

        char aChar = 0;

//        int type = 0;

        StringBuffer buffer = new StringBuffer();
        Element root = new Element(StringUtil.DOCMENT);
        nodeStack.push(root);
        Element node = null;

        String stmp = null;

        while (index < lenght)
        {
            aChar = text.charAt(index);
            if (aChar == '<')
            {
                aChar = text.charAt(index + 1);
                stmp = buffer.toString().trim();
                buffer.delete(0, buffer.length());

                if (aChar == '/')
                {
                    if (!nodeStack.empty())
                    {
                        root = (Element) nodeStack.elementAt(nodeStack.size() - 1);
                    }
                    root.setText(processEntities(stmp));


                    int tmp = text.indexOf('>', index);
                    stmp = text.substring(index, tmp + 1);
                    String tag = getTag(stmp);

                    if (tag.equals(root.getTag()))
                    {
                        if (root.children != null)
                        {
                            if (root.children.size() > 0)
                            {
                                if (root.getText() != null && !root.getText().equals(StringUtil.STRING))
                                {
                                    node = new Element(StringUtil.STRING);
                                    node.setText(processEntities(root.getText()));
                                    root.addChild(node);
                                    root.setText(null);
                                }
                            }
                        }
                    }
                    nodeStack.pop();

                    aChar = text.charAt(tmp - 1);
                    index = tmp + 1;
                }
                else if (aChar == '!')
                {
                    if (text.charAt(index + 2) == '-' && text.charAt(index + 3) == '-')
                    {
                        int tmp = text.indexOf(StringUtil.COMMET_END, index);
                        stmp = text.substring(index, tmp + 3);
                        aChar = text.charAt(tmp - 1);
                        index = tmp + 3;
                    }
                    else
                    {
                        int tmp = text.indexOf('>', index);
                        stmp = text.substring(index, tmp + 1);
//                        parseDtd(stmp, dtd);
                        aChar = text.charAt(tmp - 1);
                        index = tmp + 1;
                    }
                }
                else if (aChar == '?')
                {
                    int tmp = text.indexOf('>', index);
                    stmp = text.substring(index, tmp + 1);
                    aChar = text.charAt(tmp - 1);
                    index = tmp + 1;
                }
                else
                {
                    if (!nodeStack.empty())
                    {
                        root = (Element) nodeStack.elementAt(nodeStack.size() - 1);
                    }
                    if (stmp != null && stmp.length() > 0)
                    {
                        node = new Element(StringUtil.STRING);
                        node.setText(processEntities(stmp));
                        root.addChild(node);
                        node.parent = root;
                    }

                    int tmp = text.indexOf('>', index);
                    stmp = text.substring(index, tmp + 1);
//                    System.out.println(stmp);


                    String tag = getTag(stmp);
                    node = createElement(stmp);
                    node.setTag(tag);
//                    MyLog.debug("parent:"+root.getTag()+"---------tag:"+tag);
//                    MyLog.debug("src:"+root.getAttr("src"));

                    if (text.charAt(tmp - 1) == '/')
                    {
                        root.addChild(node);
                        node.parent = root;
                        index = tmp + 1;
                    }
                    else
                    {
                        String dtype = null;

                        if(dtd.size()>0)
                        {
                            dtype = (String) dtd.get(tag);
                        }
                        
                        if (dtype != null)
                        {
                            String tag_ = StringUtil.TAG_END + tag + '>';
                            int tmp_ = text.indexOf(tag_, tmp);
                            if (tmp_ == -1)
                            {
                                index = tmp + 1;                                          
                            }
                            else
                            {
                                node.setText(text.substring(tmp + 1, tmp_));
                                index = tmp_ + tag_.length();
                            }
                            root.addChild(node);
                            node.parent = root;                            
                        }
                        else
                        {
                            root.addChild(node);
                            node.parent = root;
                            nodeStack.push(node);
                            index = tmp + 1;
                        }
                    }
                }
            }
            else
            {
                buffer.append(aChar);
                index++;
            }
        }


        return (Element) nodeStack.pop();
    }

    public static String getTag(String str)
    {
        int end = str.indexOf(' ');
        int tmp = 1;
        if (str.charAt(1) == '/')
        {
            tmp = 2;
            end = str.length() - 1;
        }
        if (end == -1 || tmp == 2)
        {
            if (str.charAt(str.length() - 2) == '/')
            {
                end = str.length() - 2;
            }
            else
            {
                end = str.length() - 1;
            }
        }
        return str.substring(tmp, end);
    }

    public static Element createElement(String str)
    {
        Element node = new Element(StringUtil.STRING);
        int index = 0;
        int length = str.length();
        char aChar = 0;
        int type = 0;
        StringBuffer buffer = new StringBuffer();
        char quot = 0;
        int quot_Type = 0;
        String name = null;
        String value = null;

        while (index < length)
        {
            aChar = str.charAt(index);
//            if(aChar==0x0A||aChar==0x0D)
//            {
//                continue;
//            }

            if (type == 0)
            {
                if ('\r' == aChar || '\n' == aChar ||
                        '\t' == aChar || ' ' == aChar)
                {
                    type = 1;
                }
                ++index;
            }
            else if (type == 1)
            {
                if ('=' == aChar)
                {
                    name = buffer.toString().trim();
//                    MyLog.debug(name);
                    buffer.delete(0, buffer.length());
                    type = 2;
                    quot_Type = 0;
                    quot = 0;
                }
                else
                {
                    buffer.append(aChar);
                }
                ++index;
            }
            else if (type == 2)
            {
                if (quot_Type == 0)
                {
                    if (' ' != aChar)
                    {
                        if ('\'' == aChar || '\"' == aChar)
                        {
                            quot = aChar;
                        }
                        else
                        {
                            buffer.append(aChar);
                            quot = ' ';
                            quot_Type = 1;
                        }
                        quot_Type = 1;
                    }
                    ++index;
                }
                else if (quot_Type == 1)
                {
                    if (quot == aChar)
                    {
                        type = 0;
                        value = buffer.toString().trim();

                        buffer.delete(0, buffer.length());
                        node.setAttr(name, processEntities(value));
//                        System.out.println("name:"+name+"--value:"+processEntities(value));
                        name = null;
                        value = null;
                    }
                    else
                    {
                        buffer.append(aChar);
                        ++index;
                    }
                }
            }
        }
        buffer.delete(0, buffer.length());
        buffer = null;
        name = null;
        value = null;

        return node;
    }

    private static String processEntities(String str)
    {
        str = convert(str);
        str = replaceTagString(str);
        return str;
    }
    
    public static String replaceTagString(String s)
    {
//        s = Util.replaceAll(s, Const.T3, Const.T3_, false);
//        s = Util.replaceAll(s, Const.T4, Const.T4_, false);
//        s = Util.replaceAll(s, Const.T5, Const.T5_, false);
//        s = Util.replaceAll(s, Const.T6, Const.T6_, false);
//        s = Util.replaceAll(s, Const.T7, Const.T7_, false);
//        s = Util.replaceAll(s, Const.T8, Const.T8_, false);
//        s = Util.replaceAll(s, Const.T9, Const.T9_, false);
//        s = Util.replaceAll(s, Const.T10, Const.T10_, false);
//        s = Util.replaceAll(s, Const.T11, Const.T11_, false);
//        s = Util.replaceAll(s, Const.T12, Const.T12_, false);
//        s = Util.replaceAll(s, Const.T13, Const.T13_, false);
        return s;
    }

    public static String convert(String s)
    {
        do
        {
            int k1;
            if ((k1 = s.indexOf(StringUtil.T1)) == -1)
            {
                break;
            }
            int k2 = k1;
            int j4 = s.length();
            int j5 = k1;
            do
            {
                if (j5 >= j4)
                {
                    break;
                }
                if (s.charAt(j5) == ';')
                {
                    k2 = j5;
                    break;
                }
                j5++;
            } while (true);
            if (k2 == k1)
            {
                break;
            }
            String str = StringUtil.T2 + s.substring(k1 + 3, k2);
            str = StringUtil.unicodeToString(str);
            s = s.substring(0, k1) + str + s.substring(k2 + 1);
        } while (true);
        return s;
    }

    public void remove()
    {
        this.tag = null;
        this.text = null;

        if (this.attrs != null)
        {
            Enumeration<String> e = this.attrs.keys();
            while (e.hasMoreElements())
            {
                String key = (String) e.nextElement();
                this.attrs.remove(key);
                key = null;
            }
            e = null;
        }
        this.attrs = null;
        if (this.children != null)
        {
            for (int i = 0; i < this.children.size(); i++)
            {
                ((Element) this.children.elementAt(i)).remove();
            }

            this.children.removeAllElements();
        }
        this.children = null;
    }

    public int size;

    public int size()
    {
        if (this.children != null)
        {
            size = this.children.size();
        }
        return size;
    }

    public void debug(String s)
    {
        int ss = size();
        s += "-";
//        MyLog.debug(s+getTag()+":"+ss);
        for (int i = 0; i < ss; i++)
        {
            Element em = (Element) this.children.elementAt(i);
//            MyLog.debug("tag:"+em.tag);
            em.debug(s);
        }
    }

    public String getXml()
    {
         StringBuffer sb = new StringBuffer();
         sb.append("<");
         sb.append(this.tag);
         sb.append(" ");

         Enumeration<String> enu = this.attrs.keys();
         while(enu.hasMoreElements())
         {
             String key = enu.nextElement();
             sb.append(key);
             sb.append("=\"");
             sb.append(attrs.get(key));
             sb.append("\"");
             sb.append(" ");
         }
        int size = size();
        if(size==0)
        {
            sb.deleteCharAt(sb.length()-1);
             sb.append("/>");
        }
        else
        {
            sb.deleteCharAt(sb.length()-1);
            sb.append(">");
            sb.append("\n");
            for (int i = 0; i < size; i++)
            {
                Element em = (Element) this.children.elementAt(i);
                sb.append(em.getXml());
                sb.append("\n");
            }
             sb.append("</");
             sb.append(this.tag);
             sb.append(">");
        }
        return sb.toString();
    }



    

}