package com.conDB.until;

import com.alibaba.common.lang.StringUtil;

public class StringUntilMy {

	/**
	 * 将ac_b转换成AcB
	 * 
	 * @param a_b
	 * @return
	 */
	public static String changeAaA(String a_b) {
		return StringUtil.capitalize(changeabA(a_b));
	}

	/**
	 * 将ac_b转换成acB
	 * 
	 * @param a_b
	 * @return
	 */
	public static String changeabA(String ab_b) {
		String[] temps = ab_b.split("_");
		ab_b = temps[0];
		for (int i = 1; i < temps.length; i++) {
			ab_b += StringUtil.capitalize(temps[i]);
		}
		return ab_b;
	}
}
