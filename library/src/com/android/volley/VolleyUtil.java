package com.android.volley;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * 类名：VolleyUtil
 * 
 * 描述：volley封装工具类
 * 
 * @author GankLun
 * 
 */
public class VolleyUtil {
	private static RequestQueue requestQueue;

	private static ImageLoader mImageLoader;

	private final static int TIME_OUT = 15000;

	/**
	 * Construct 构造方法
	 */
	private VolleyUtil() {

	}

	private static RequestQueue getInstance(Context context) {
		if (requestQueue == null) {
			synchronized (VolleyUtil.class) {
				if (requestQueue == null) {
					requestQueue = Volley.newRequestQueue(context);
					requestQueue.start();
				}
			}

		}
		return requestQueue;
	}

	private static <T> void addRequest(RequestQueue requestQueue,
			Request<T> request, Object tag) {
		if (tag != null) {
			request.setTag(tag);
		}
		request.setShouldCache(false);
		request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		requestQueue.add(request);
	}

	public static void cancelAll(Object tag) {
		if (null != requestQueue) {
			requestQueue.cancelAll(tag);
		}
	}

	public static ImageLoader getImageLoader(Context context) {
		mImageLoader = new ImageLoader(getInstance(context), new BitmapCache());
		return mImageLoader;
	}

	public static void disPlayImage(Context context, ImageView imageView,
			String url, int defaultResourceId, int errorResourceId) {
		ImageListener listener = ImageLoader.getImageListener(imageView,
				defaultResourceId, errorResourceId);
		getImageLoader(context).get(url, listener);

	}

	public static void disPlayImage(Context context, ImageView imageView,
			String url, int defaultResourceId, int errorResourceId,
			int maxWidth, int maxHeight) {
		ImageListener listener = ImageLoader.getImageListener(imageView,
				defaultResourceId, errorResourceId);
		getImageLoader(context).get(url, listener, maxWidth, maxHeight);

	}

	public static <T> void sendStringRequestByPost(Context context, String url,
			Object tag, final Map<String, String> params, final Class<T> clazz,
			final HttpBackListener<T> listener, final boolean isLogin,
			final String cookieValue) {
		StringRequest stringRequest = new StringRequest(Method.POST, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String s) {
						T t = JSON.parseObject(s, clazz);
						listener.onSuccess(t);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError volleyError) {
						if (volleyError != null) {
							Log.e("VolleyError", volleyError.getMessage());
							listener.onFail(volleyError.networkResponse.statusCode);
						}
					}
				}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = new HashMap<String, String>();
				if (!isLogin) {
					headers.put(GlobConstant.COOKIE,
							cookieValue);
					return headers;
				}
				return super.getHeaders();

			}

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {

				return params == null ? super.getParams() : params;
			}

		};
		addRequest(getInstance(context), stringRequest, tag);
	}

	public static <T> void sendStringRequestByGet(Context context,
			final String url, Object tag, final Map<String, String> params,
			final Class<T> clazz, final HttpBackListener<T> listener,
			final boolean isLogin, final String cookieValue) {
		StringRequest stringRequest = new StringRequest(Method.GET, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String s) {
						T t = JSON.parseObject(s, clazz);
						listener.onSuccess(t);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError volleyError) {
						if (volleyError != null) {
							Log.e("VolleyError", volleyError.getMessage());
							listener.onFail(volleyError.networkResponse.statusCode);
						}
					}
				}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = new HashMap<String, String>();
				if (!isLogin) {
					headers.put(GlobConstant.COOKIE,
							cookieValue);
					return headers;
				}
				return super.getHeaders();

			}

			@Override
			public String getUrl() {
				String sParams = BaseUtil.mapToStringParams(params);
				if (sParams.equals("")) {
					return super.getUrl();
				} else {
					return url + "?" + sParams;
				}

			}

		};
		addRequest(getInstance(context), stringRequest, tag);
	}

	public static <T> void sendJsonObject(Context context, int method,
			String url, JSONObject jsonRequest, Object tag,
			final Class<T> clazz, final HttpBackListener<T> listener,
			final boolean isLogin, final String cookieValue) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method,
				url, jsonRequest, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject jsonObject) {
						T t = JSON.parseObject(jsonObject.toString(), clazz);
						listener.onSuccess(t);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError volleyError) {
						if (volleyError != null) {
							Log.e("VolleyError", volleyError.getMessage());
							listener.onFail(volleyError.networkResponse.statusCode);
						}
					}
				}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = new HashMap<String, String>();
				if (!isLogin) {
					headers.put(GlobConstant.COOKIE,
							cookieValue);
					return headers;
				}
				return super.getHeaders();

			}

		};
		addRequest(getInstance(context), jsonObjectRequest, tag);
	}

	public static <T> void sendJsonRequest(Context context, int method,
			String url, String jsonStr, Object tag, Class<T> clazz,
			final HttpBackListener<T> listener, final boolean isLogin,
			final String cookieValue) {
		JsonRequest<T> jsonRequest = new JsonRequest<T>(method, url, jsonStr,
				clazz, isLogin, cookieValue, new Listener<T>() {

					@Override
					public void onResponse(T t) {
						listener.onSuccess(t);
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError volleyError) {
						if (volleyError != null) {
							Log.e("VolleyError", volleyError.getMessage());
							listener.onFail(volleyError.networkResponse.statusCode);
						}
					}
				});
		addRequest(getInstance(context), jsonRequest, tag);
	}
}
