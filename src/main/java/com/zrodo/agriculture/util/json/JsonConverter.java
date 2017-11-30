package com.zrodo.agriculture.util.json;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class JsonConverter {
	public static final String QUOTA = "\"";// quotation
	public static final String COLON = ":";// colon
	public static final String COMMA = ","; // comma
	public static final String BLANK = ""; // blank
	public static final String LB = "{";// left brackets
	public static final String RB = "}";// right brackets
	public static final String LSB = "[";// left square brackets
	public static final String RSB = "]";// right square brackets

	/**
	 * 转换对象为JSON字符串。 参数类型必须为以下两种Collection<Map>以及Map<String,
	 * CharSequence|Collection>之一。Collection中的Map为后者类型，Map中的Collection为前者类型。
	 * 
	 * @param o
	 */
	@SuppressWarnings("rawtypes")
	public static String convert(Object o) {
		StringBuffer buf = new StringBuffer();
		if (o instanceof Map) {// Map<String, CharSequence|Collection>
			Map m = (Map) o;
			buf.append(LB);
			if (!m.isEmpty()) {
				Iterator it = m.keySet().iterator();
				while (it.hasNext()) {
					Object k = it.next();
					Object v = m.get(k);
					if(v == null){
						v = "";
					}
					
					if (v instanceof CharSequence||v instanceof Date||v instanceof Integer) {// CharSequence
						buf.append(kv(k.toString(), v.toString()));
					} else if (v instanceof Collection) {// Collection
						if (((Collection) v).size() > 0) {
							buf.append(kvn(k.toString(), convert(v)));
						} else {
							continue;
						}
					} else if (v instanceof Map) {// Map
						if (((Map) v).size() > 0) {
							buf.append(kvn(k.toString(), convert(v)));
						} else {
							continue;
						}
					} else {
						continue;
					}
					buf.append(COMMA);
				}
				if (buf.charAt(buf.length() - 1) == COMMA.charAt(0)) {
					buf.deleteCharAt(buf.length() - 1);
				}
			}
			buf.append(RB);
		} else if (o instanceof Collection) {// Collection<Map>
			Collection c = (Collection) o;
			buf.append(LSB);
			if (!c.isEmpty()) {// 集合不为空时
				Iterator it = c.iterator();
				while (it.hasNext()) {
					Object k = it.next();
					if (k instanceof Map) {
						buf.append(convert(k));
						buf.append(COMMA);
					}
				}
				if (buf.charAt(buf.length() - 1) == COMMA.charAt(0)) {
					buf.deleteCharAt(buf.length() - 1);
				}
			}
			buf.append(RSB);
		}
		return buf.toString();
	}

	/**
	 * 空对象返回空字符串
	 * 
	 * @param o
	 * @return
	 */
	private static String e(String o) {
		if (o != null) {
			String transChar = "\"";//{}[]/\\:,=;#";
			StringBuffer buf = new StringBuffer();
			for (char ch : o.toCharArray()) {
				String str = String.valueOf(ch);
				if (transChar.indexOf(str) != -1) {
					buf.append("\\");
				}
				buf.append(str);
			}
			return buf.toString();
		} else {
			return "";
		}
	}

	/**
	 * 返回带引号的字符串
	 * 
	 * @param o
	 * @return
	 */
	private static String w(String o) {
		return QUOTA + e(o) + QUOTA;
	}

	/**
	 * 返回JSON键值对。键值都以西文引号包裹。
	 * 
	 * @param k
	 * @param v
	 * @return
	 */
	private static String kv(String k, String v) {
		return w(k) + COLON + w(v);
	}

	/**
	 * 返回JSON键值对。键以西文引号包裹，值为原文。
	 * 
	 * @param k
	 * @param v
	 * @return
	 */
	private static String kvn(String k, String v) {
		return w(k) + COLON + v;
	}
}
