package com.geminno.club.util;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.geminno.club.view.RoundImageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.View;
import android.widget.ImageView;

public class MUtils {
	public static void useVolleyForRoundImage(Context mContext,final View mview,String url){
		
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        
        ImageRequest request = new ImageRequest(url, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {
				// TODO Auto-generated method stub
				((RoundImageView) mview).setImageBitmap(response);
			}
        	
        }, 0, 0, Config.RGB_565, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
        mQueue.add(request);
	}
	
	public static void useVolleyForImage(Context mContext,final View mview,String url){
		
        RequestQueue mQueue = Volley.newRequestQueue(mContext);
        
        ImageRequest request = new ImageRequest(url, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {
				// TODO Auto-generated method stub
				((ImageView) mview).setImageBitmap(response);
			}
        	
        }, 0, 0, Config.RGB_565, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
        mQueue.add(request);
	}
	
	
	
}
