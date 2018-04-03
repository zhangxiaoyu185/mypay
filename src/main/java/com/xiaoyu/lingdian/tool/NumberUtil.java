package com.xiaoyu.lingdian.tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberUtil {

	private NumberUtil() {}

	/**
	 * 格式化金额，最多保留2位小数，如果末尾是0，去除掉
	 * 比如:
	 * 2.199变成2.1
	 * 2.123变成2.12
	 * 
	 * @param n
	 * @return
	 */
	public static String formatMoney(BigDecimal n) {
		if (n == null) return "";
		DecimalFormat formatter = new DecimalFormat("0.##");
		return formatter.format(n.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		// or
//		String s = n.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
//		return s.indexOf(".") < 0 ? s : s.replaceAll("0*$", "").replaceAll("\\.$", "");
	}

	/**
	 * 格式化金额，最多保留2位小数，如果末尾是0，去除掉
	 * 比如:
	 * 2.199变成2.1
	 * 2.123变成2.12
	 * 
	 * @param n
	 * @return
	 */
	public static String formatMoney(double n) {
		DecimalFormat formatter = new DecimalFormat("0.##");
		formatter.setRoundingMode(RoundingMode.HALF_UP);
		return formatter.format(n);
	}

	/**
	 * 格式化金额，保留2位小数
	 * 比如:
	 * 2.199变成2.10
	 * 2.123变成2.12
	 * 
	 * @param n
	 * @return
	 */
	public static String formatMoney2(BigDecimal n) {
		if (n == null) return "";
		DecimalFormat formatter = new DecimalFormat("0.00");
		return formatter.format(n.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	/**
	 * 格式化金额，保留2位小数
	 * 比如:
	 * 2.199变成2.10
	 * 2.123变成2.12
	 * 
	 * @param n
	 * @return
	 */
	public static String formatMoney2(double n) {
		DecimalFormat formatter = new DecimalFormat("0.00");
		formatter.setRoundingMode(RoundingMode.HALF_UP);
		return formatter.format(n);
	}

	/**
	 * 根据传入的<code>scale</code>格式化小数，默认去掉小数后面的0
	 * 
	 * @param n
	 * @param scale
	 * @return
	 */
	public static String formatNumber(BigDecimal n, int scale) {
		if (n == null) return "";
		StringBuilder buff = new StringBuilder(8);
		buff.append("0");
		if (scale > 0) {
			buff.append(".");
			for (int i = 0; i < scale; ++i) buff.append("#");
		}
		DecimalFormat formatter = new DecimalFormat(buff.toString());
		return formatter.format(n.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

}
