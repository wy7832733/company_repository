package cn.cyit.traffic.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDUtil {
	private static long orderNum = 0l;
	private static String date;

	/**
	 * 获取32UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.replace("-", "");
	}

	/**
	 * 获取毫秒数
	 * 
	 * @return
	 */
	public static String getNUID() {
		
		String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
		if (date == null || !date.equals(str)) {
			date = str;
			orderNum = 0l;
		}
		orderNum++;
		long orderNo = Long.parseLong((date)) * 10000;
		orderNo += orderNum;
		return orderNo + "";
	}

	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
