package com.android.volley;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapCache implements ImageCache{

	private LruCache<String, Bitmap> mCache;  
	
	private final static int RATE=8;
	  
    public BitmapCache() {  
    	//获取程序分配的最大内存容量，单位字节
    	int maxMemory=(int) Runtime.getRuntime().maxMemory();
        int maxCacheSize = maxMemory/RATE; 
        mCache = new LruCache<String, Bitmap>(maxCacheSize) {  
            @Override  
            protected int sizeOf(String key, Bitmap bitmap) {  
                return bitmap.getRowBytes() * bitmap.getHeight();  
            }
            
        };  
    }  
	@Override
	public Bitmap getBitmap(String url) {

		return mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		mCache.put(url, bitmap);
		
	}

}
