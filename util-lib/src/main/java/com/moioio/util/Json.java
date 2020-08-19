package com.moioio.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


public class Json {
	private Hashtable<String, Object> attrs;

	public Json() {
		attrs = new Hashtable<String, Object>();
	}

	/**
	 * @param name
	 * @param value
	 */
	public void setAttr(String name, Object value) {
		this.attrs.put(name, value);
	}

	public void setAttrHash(Hashtable<String, Object> hash) {
		attrs = hash;
	}

	public Hashtable<String, Object> getAttrHash() {
		return attrs;
	}

	/**
	 * @param name
	 * @return
	 */
	public String getAttr(String name) {
		if (attrs == null) {
			return null;
		}
		String s = (String) attrs.get(name);
		if (s == null) {
			s = "";//Const.STRING;
		}
		return s;
	}

	public Json[] getJsons(String name) {
		if (attrs == null) {
			return null;
		}
		Vector<Json> objs = (Vector<Json>) attrs.get(name);
		Json js[] = new Json[objs.size()];
		objs.copyInto(js);
		return js;
	}

	public static Json parse(String text) {
		if (StringUtil.isNull(text)) {
			return null;
		}
		Json root = new Json();
		createJson(text, root);
		return root;
	}

	private static int createJson(String text, Json root) {
		int endIndex = 0;
		int lenght = text.length();
		int index = 0;
		char aChar = 0;

		StringBuffer buffer = new StringBuffer();

		boolean isKeySetStart = false;
		String name = "";

		while (index < lenght) {
			aChar = text.charAt(index);
			if (aChar == '{') {
				// splitStartIndex = index;
			} else if (aChar == ':') {
				if (!isKeySetStart) {
					name = buffer.toString();
					name = StringUtil.replaceAll(name, "\"", "", true);
					name = StringUtil.replaceAll(name, "{", "", true);
					buffer.delete(0, buffer.length());
					isKeySetStart = true;
				}
                                else
                                {
                                    buffer.append(aChar);
                                }
			} else if (aChar == '[') {
				if (isKeySetStart) {
					Vector<Json> subJson = new Vector<Json>();
					String subText = text.substring(index + 1);
					char bChar = subText.charAt(0);
					if (bChar == ']') {
						isKeySetStart = false;
//                                                if(StringUtil.isNull(name))
//                                                {
//                                                    name = "null";
//                                                }
						root.setAttr(name, subJson);
						index++;
					} else {
						while (bChar != ']') {
							Json newJson = new Json();

							int kuoIndex = subText.indexOf('{');
							if (kuoIndex != -1) {
								subText = subText.substring(kuoIndex);
							}
							// System.out.println("subText---"+subText);

							int subIndex = createJson(subText, newJson);
							if (newJson.size() > 0) {
								subJson.addElement(newJson);
							}
							if (subIndex < subText.length()) {
								subIndex = subIndex + 1;
							}
							subText = subText.substring(subIndex);
							index = index + subIndex + 1;
							bChar = text.charAt(index);
//							 System.out.println("bChar---"+bChar);
//							 System.out.println("subText---"+subText);
						}
						isKeySetStart = false;
						root.setAttr(name, subJson);

						// System.out.println(name+"--+++-"+subJson.size());
					}
				}
			} else if (aChar == ',' || aChar == '}') {
				if (isKeySetStart) {
					String value = buffer.toString();
					value = StringUtil.replaceAll(value, "\"", "", true);
					value = StringUtil.replaceAll(value, "{", "", true);
					buffer.delete(0, buffer.length());
					isKeySetStart = false;
					root.setAttr(name, value);
					// System.out.println(name+"---"+value);
				}
			} else {
				buffer.append(aChar);
			}

			if (aChar == '}' || aChar == ']') {
				endIndex = index;
				break;
			}

			index++;

		}

		return endIndex;
	}

	public int size() {
		return attrs.size();
	}

	public void debug(String s) {

	}

    public static String getJson(Hashtable hash)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        Enumeration ens = hash.keys();
        while(ens.hasMoreElements())
        {
            String key = (String)ens.nextElement();
            String value = (String)hash.get(key);
            sb.append(key+":\""+value+"\",");
        }
        sb.deleteCharAt(sb.length()-1);
        
        sb.append("}");
        
        return sb.toString();
    }
	
	
	
	private void getJson(Json json, StringBuffer sb) {
		if (json.size() == 0) {
			return;
		}
		sb.append("{");
		Enumeration<String> ekeys = json.getAttrHash().keys();
		Vector<String> keys = new Vector<String>();
		while (ekeys.hasMoreElements()) {
			String key = ekeys.nextElement();
			keys.addElement(key);
		}

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.elementAt(i);
			Object obj = json.getAttrHash().get(key);
			if (obj instanceof String) {
				sb.append("\"" + key + "\":\"" + (String) obj + "\"");
			} else if (obj instanceof Vector) {
				sb.append("\"" + key + "\":[");
				Vector<Json> subJson = (Vector<Json>) obj;
				for (int j = 0; j < subJson.size(); j++) {
					Json jobj = subJson.elementAt(j);
					getJson(jobj, sb);
					if (j != subJson.size() - 1) {
						sb.append(",");
					}
				}
				sb.append("]");
			}

			if (i != keys.size() - 1) {
				sb.append(",");
			}

		}

		sb.append("}");
	}

	public String getJson() {
		StringBuffer sb = new StringBuffer();
		getJson(this, sb);
		return sb.toString();
	}
}
