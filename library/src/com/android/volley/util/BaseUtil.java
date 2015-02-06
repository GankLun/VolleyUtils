package com.android.volley.util;

import java.util.Map;

public class BaseUtil {

	/**
	 * @方法：mapToStringParams
	 * @描述：把map转成param1=a&param2=b..的键值对形式
	 * @param params
	 * @return
	 */
	public static String mapToStringParams(Map<String, String> params) {
		String queryString = "";
		if (params != null) {
			for (String key : params.keySet()) {
				String value = params.get(key);
				queryString += key + "=" + value + "&";
			}
		}
		if (queryString.length() > 0) {
			queryString = queryString.substring(0, queryString.length() - 1);
		}
		return queryString;
	}
}
