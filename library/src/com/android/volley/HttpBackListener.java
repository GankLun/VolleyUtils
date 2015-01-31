package com.android.volley;

/**
 * 类名:		HttpSuccessListener
 * 描述:		通信成功后的接口回调
 * @author 	GankLun
 * @param <T>
 *
 */
public interface HttpBackListener<T> {

  public void onSuccess(T t);
     
  public void onFail(int statusCode);
}
