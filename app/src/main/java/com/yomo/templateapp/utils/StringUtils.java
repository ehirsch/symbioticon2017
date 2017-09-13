package com.yomo.templateapp.utils;


import com.google.common.base.Strings;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Created by bul on 04.04.16.
 */
public class StringUtils {

	private static BiMap<Character, String> superScriptCharMap = HashBiMap.create();
	private static BiMap<Character, String> subScriptCharMap = HashBiMap.create();

	static {
		superScriptCharMap.put('0', "\u2070");
		superScriptCharMap.put('1', "\u00B9");
		superScriptCharMap.put('2', "\u00B2");
		superScriptCharMap.put('3', "\u00B3");
		superScriptCharMap.put('4', "\u2074");
		superScriptCharMap.put('5', "\u2075");
		superScriptCharMap.put('6', "\u2076");
		superScriptCharMap.put('7', "\u2077");
		superScriptCharMap.put('8', "\u2078");
		superScriptCharMap.put('9', "\u2079");

		subScriptCharMap.put('0', "\u2080");
		subScriptCharMap.put('1', "\u2081");
		subScriptCharMap.put('2', "\u2082");
		subScriptCharMap.put('3', "\u2083");
		subScriptCharMap.put('4', "\u2084");
		subScriptCharMap.put('5', "\u2085");
		subScriptCharMap.put('6', "\u2086");
		subScriptCharMap.put('7', "\u2087");
		subScriptCharMap.put('8', "\u2088");
		subScriptCharMap.put('9', "\u2089");
	}

	public static String toSuperScript(Object value) {
		return subOrSuperScript(value, superScriptCharMap);
	}

	private static String subOrSuperScript(Object obj, Map<Character, String> mapping) {
		String value = obj == null ? null : obj.toString();
		if (Strings.isNullOrEmpty(value)) {
			return value;
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			Character c = value.charAt(i);
			String supString = mapping.get(c);
			stringBuilder.append(supString != null ? supString : c);
		}
		return stringBuilder.toString();
	}

	public static String getAmountBigPart(long amountValue) {
		int amount = (int) (amountValue / 100);
		String formatted = NumberFormat.getNumberInstance(Locale.GERMANY).format(amount);
		return formatted + ",";
	}

	public static String getAmountSmallPart(long amountValue) {
		String s = String.valueOf(Math.abs(amountValue % 100));
		String s1 = Strings.padStart(s, 2, '0');
		return StringUtils.toSuperScript(s1);
	}
}