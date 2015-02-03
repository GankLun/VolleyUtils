package com.android.volley;

import com.android.volley.toolbox.NetworkImageView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
   private NetworkImageView networkImageView;
   
   private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView=(ImageView) findViewById(R.id.iVi_image);
		VolleyUtil.disPlayImage(MainActivity.this, imageView, "https://raw.githubusercontent.com/GankLun/VolleyUtils/master/volley.jpg", R.drawable.ic_launcher, R.drawable.ic_launcher);
		networkImageView=(NetworkImageView)findViewById(R.id.iVi_network_image);
		networkImageView.setDefaultImageResId(R.drawable.ic_launcher);
		networkImageView.setErrorImageResId(R.drawable.ic_launcher);
		networkImageView.setImageUrl("https://raw.githubusercontent.com/GankLun/VolleyUtils/master/volley.jpg", VolleyUtil.getImageLoader(MainActivity.this));
	}

}
