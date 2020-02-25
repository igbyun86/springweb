package com.springweb.framework.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XssUtil {
	protected static final Logger log = LogManager.getLogger();

	public static String[] xssValidate(String values[]) {
		if (values == null)
			return null;
		String value = "";
		for (int i = 0, k = values.length; i < k; i++)
			if (values[i] != null) {
				StringBuffer strBuff = new StringBuffer();
				for (int j = 0, t = values[i].length(); j < t; j++) {
					char c = values[i].charAt(j);
					switch (c) {
					case 60: // '<'
						strBuff.append("&lt;");
						break;

					case 62: // '>'
						strBuff.append("&gt;");
						break;

					case 37: // '%'
						strBuff.append("％");
						break;

					case 38: // '&'
						strBuff.append("&amp;");
						break;

					case 34: // '"'
						strBuff.append("&quot;");
						break;

					case 39: // '\''
						strBuff.append("&apos;");
						break;

					default:
						strBuff.append(c);
						break;
					}
				}
				value = strBuff.toString();
				value = StringUtil.replace(value, "onload", "no_onload");
				value = StringUtil.replace(value, "expression", "no_expression");
				value = StringUtil.replace(value, "onmouseover", "no_onmouseover");
				value = StringUtil.replace(value, "onmouseout", "no_onmouseout");
				value = StringUtil.replace(value, "onclick", "no_onclick");
				value = StringUtil.replace(value, "document.cookie", "&#100;&#111;&#99;&#117;&#109;&#101;&#110;&#116;&#46;&#99;&#111;&#111;&#107;&#105;&#101;");
				value = StringUtil.replace(value, "eval", "cval");
				values[i] = value;
			} else {
				values[i] = null;
			}

		return values;
	}

	public static String xssValidate(String value) {
		if (value == null)
			return null;
		StringBuffer strBuff = new StringBuffer();
		for (int i = 0, j = value.length(); i < j; i++) {
			char c = value.charAt(i);
			switch (c) {
			case 60: // '<'
				strBuff.append("&lt;");
				break;

			case 62: // '>'
				strBuff.append("&gt;");
				break;

			case 37: // '%'
				strBuff.append("％");
				break;

			case 38: // '&'
				strBuff.append("&amp;");
				break;

//			case 34: // '"'
//				strBuff.append("&quot;");
//				break;
//
			case 39: // '\''
				strBuff.append("&apos;");
				break;

			default:
				strBuff.append(c);
				break;
			}
		}

		value = strBuff.toString();


		value = StringUtil.replace(value, "onload", "no_onload");
		value = StringUtil.replace(value, "expression", "no_expression");
		value = StringUtil.replace(value, "onmouseover", "no_onmouseover");
		value = StringUtil.replace(value, "onmouseout", "no_onmouseout");
		value = StringUtil.replace(value, "onclick", "no_onclick");
		value = StringUtil.replace(value, "document.cookie", "&#100;&#111;&#99;&#117;&#109;&#101;&#110;&#116;&#46;&#99;&#111;&#111;&#107;&#105;&#101;");
		value = StringUtil.replace(value, "eval", "cval");


		return value;
	}

	public static String[] xssValidateExcept(String values[]) {
		if (values == null)
			return null;
		String value = "";
		for (int i = 0, k = values.length; i < k; i++)
			if (values[i] != null) {
				StringBuffer strBuff = new StringBuffer();
				for (int j = 0, t = values[i].length(); j < t; j++) {
					char c = values[i].charAt(j);
					switch (c) {
					case 37: // '%'
						strBuff.append("％");
						break;
					default:
						strBuff.append(c);
						break;
					}
				}
				value = strBuff.toString();
				value = StringUtil.replace(value, "<script", "&lt;script");
				value = StringUtil.replace(value, "</script", "&lt;/script");
				value = StringUtil.replace(value, "onload", "no_onload");
				value = StringUtil.replace(value, "expression", "no_expression");
				value = StringUtil.replace(value, "onmouseover", "no_onmouseover");
				value = StringUtil.replace(value, "onmouseout", "no_onmouseout");
				value = StringUtil.replace(value, "onclick", "no_onclick");
				value = StringUtil.replace(value, "document.cookie", "&#100;&#111;&#99;&#117;&#109;&#101;&#110;&#116;&#46;&#99;&#111;&#111;&#107;&#105;&#101;");
				value = StringUtil.replace(value, "eval", "cval");
				values[i] = value;
			} else {
				values[i] = null;
			}

		return values;
	}


	public static String xssValidateExcept(String value) {
		if (value == null)
			return null;

		StringBuffer strBuff = new StringBuffer();
		for (int i = 0, j = value.length(); i < j; i++) {
			char c = value.charAt(i);
			switch (c) {
			case 37: // '%'
				strBuff.append("％");
				break;
			default:
				strBuff.append(c);
				break;
			}
		}

		value = strBuff.toString();

		if (value == null)
			return null;
		value = StringUtil.replace(value, "<script", "&lt;script");
		value = StringUtil.replace(value, "</script", "&lt;/script");
		value = StringUtil.replace(value, "onload", "no_onload");
		value = StringUtil.replace(value, "expression", "no_expression");
		value = StringUtil.replace(value, "onmouseover", "no_onmouseover");
		value = StringUtil.replace(value, "onmouseout", "no_onmouseout");
		value = StringUtil.replace(value, "onclick", "no_onclick");
		value = StringUtil.replace(value, "document.cookie", "&#100;&#111;&#99;&#117;&#109;&#101;&#110;&#116;&#46;&#99;&#111;&#111;&#107;&#105;&#101;");
		value = StringUtil.replace(value, "eval", "cval");

		return value;
	}

}
