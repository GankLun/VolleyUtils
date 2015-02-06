package com.android.volley.request;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.constant.GlobConstant;
import com.android.volley.toolbox.HttpHeaderParser;

/**
 * ����:		JsonRequest
 * ����:		�Զ����װJsonRequest
 * @author 	GankLun
 * @param <T>
 *
 */
public class JsonRequest<T> extends Request<T> {
    private static final String PROTOCOL_CHARSET      = "utf-8";

    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private String              jsonStr;

    private Class<T>            clazz;

    private boolean             isLogin;

    private Listener<T>         listener;

    private String cookieValue;

    public JsonRequest(int method, String url, String jsonStr, Class<T> clazz, boolean isLogin, String cookieValue, Listener<T> listener,
            ErrorListener errorListener) {
        super(method, url, errorListener);
        this.jsonStr = jsonStr;
        this.clazz = clazz;
        this.isLogin = isLogin;
        this.cookieValue = cookieValue;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        if (!isLogin) {
            headers.put(GlobConstant.COOKIE,cookieValue);
            return headers;
        }
        return super.getHeaders();
    }

    @Override
    protected void deliverResponse(T t) {
        listener.onResponse(t);
    }

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return jsonStr == null ? super.getBody() : jsonStr.getBytes();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            if (isLogin) {
            	//�������󷵻ص���Ӧͷ
                Header[] headers = response.apacheHeaders;
                if (headers != null) {
                    for (Header header : headers) {
                        if (header.getName().equalsIgnoreCase(GlobConstant.SET_COOKIE)) {
                           //�����ҿ������ɷ���,ȥ�����Լ���Ҫ��cookie.
                        }
                    }

                }
            }
            String json = new String(response.data, PROTOCOL_CHARSET);
            return Response.success(JSON.parseObject(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

}
