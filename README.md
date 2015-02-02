# VolleyUtils for Android
![Screenshot](https://raw.githubusercontent.com/GankLun/VolleyUtils/master/volley.jpg)  
This project aims to provide a volley wrapper widget for Android. you just call a static method in VolleyUtils with one line code to make  http request and load image from website done.
##Features  
* Call http request with StringRequest、JsonObjectRequest、CustomRequest.  
* Download image source from web and support cache.  
* Resolve the problem of cookie handle.  
* Support https protocol with modifying origin volley jar.

## Usage 
* VolleyUtil.sendStringRequestByPost(Context context, String url,Object tag, final Map<String, String> params, final Class<T> clazz,final HttpBackListener<T> listener, final boolean isLogin,final String cookieValue)
* VolleyUtil.sendStringRequestByGet(Context context,	final String url, Object tag, final Map<String, String> params,final Class<T> clazz, final HttpBackListener<T> listener,final boolean isLogin, final String cookieValue)
* VolleyUtil.sendJsonObject(Context context, int method,String url, JSONObject jsonRequest, Object tag,final Class<T> clazz, final HttpBackListener<T> listener,final boolean isLogin, final String cookieValue)
* VolleyUtil.sendJsonRequest(Context context, int method,	String url, String jsonStr, Object tag, Class<T> clazz,final HttpBackListener<T> listener, final boolean isLogin,final String cookieValue)  

## More Introduction  
If you want to learn more about VolleyUtils,please see my csdn blog [Volley封装，一行代码搞定http请求](http://blog.csdn.net/ganklun/article/details/43372355).  
## License     
    Copyright 2014, 2015 GankLun
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



 
