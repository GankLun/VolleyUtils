package com.android.volley.listener;

/**
 * ����:		HttpSuccessListener
 * ����:		ͨ�ųɹ���Ľӿڻص�
 * @author 	GankLun
 * @param <T>
 *
 */
public interface HttpBackListener<T> {

  public void onSuccess(T t);
     
  public void onFail(int statusCode);
}
