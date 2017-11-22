package com.xxim.client.utils;

public class StringUtil {
	
	/**
	 * 判断字符串是否为空  null or "" 为 true
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str != null && str.length() > 0){
			return false;
		}
		return true;
	}
}
