package com.geminno.club.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;

import com.example.clubapp.R;
import com.geminno.club.adapter.MyBaseAdapter;
import com.geminno.club.adapter.ViewHolder;
import com.geminno.club.entity.MyFocusedFriendInfo;
import com.geminno.club.entity.MyFocusedFriendsEntity;
import com.geminno.club.entity.RecommendFriendsEntity;
import com.geminno.club.entity.RecommendFriendsInfo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyfriendsActivity extends Activity implements OnClickListener,OnItemClickListener,OnRefreshListener{

	@ViewInject(R.id.lv_myfriends)
	ListView lv_item_friendListView;
	
	@ViewInject(R.id.myfriends_back)
	ImageView backImageView;
	
	@ViewInject(R.id.layout_left)
	LinearLayout layout_left;
	
	@ViewInject(R.id.layout_center)
	LinearLayout layout_center;
	
	@ViewInject(R.id.layout_right)
	LinearLayout layout_right;
	
	@ViewInject(R.id.tv_left)
	TextView tv_left;
	
	@ViewInject(R.id.tv_center)
	TextView tv_center;
	
	@ViewInject(R.id.tv_right)
	TextView tv_right;
	
	@ViewInject(R.id.myfriends_swipe_ly)
	SwipeRefreshLayout mSwipeLayout;
	//关注的好友，和我的粉丝，共用一个集合
	ArrayList<MyFocusedFriendInfo> myFocusedFriendInfos;
	//系统推荐好友用的集合
	ArrayList<RecommendFriendsInfo> recommendFriendsInfos;
	
	private MyBaseAdapter<MyFocusedFriendInfo> adapter;
	private MyBaseAdapter<RecommendFriendsInfo> adapter1;
	
	private static final int REFRESH_COMPLETE = 0X110;
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {

			case REFRESH_COMPLETE:

				/**
				 * 告诉适配器，集合内容已经刷新，直接接在后面，不会跳到第一条
				 */
				adapter.notifyDataSetChanged();

				/**
				 * 设置刷新结束之后，圈停止转动
				 */
				mSwipeLayout.setRefreshing(false);
				break;
			}

		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myfriends);
		ViewUtils.inject(this);
		
		lv_item_friendListView.setOnItemClickListener(this);
		backImageView.setOnClickListener(this);
		layout_left.setOnClickListener(this);
		layout_center.setOnClickListener(this);
		layout_right.setOnClickListener(this);
		
		myFocusedFriendInfos = new ArrayList<MyFocusedFriendInfo>();
		recommendFriendsInfos = new ArrayList<RecommendFriendsInfo>();
		
		 mSwipeLayout.setOnRefreshListener(this);
		 /**
		 * 设置刷新时候的颜色
		 */
		
		 mSwipeLayout.setColorScheme(
				 android.R.color.holo_blue_bright,
				 android.R.color.holo_green_light,
				 android.R.color.holo_orange_light,
				 android.R.color.holo_red_light);
		 
		 
		 findViewById(R.id.layout_center).setSelected(true);
		 getDatafromHttp("fans_list");
		 
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.myfriends_back:
			finish();
			break;
		case R.id.layout_left:
			layout_left.setSelected(true);
			layout_right.setSelected(false);
			layout_center.setSelected(false);

			if(myFocusedFriendInfos.size()>0){
				myFocusedFriendInfos.clear();
			}

			getDatafromHttp("friend_list");

		
			break;
		case R.id.layout_center:
			layout_left.setSelected(false);
			layout_right.setSelected(false);
			layout_center.setSelected(true);
			if(myFocusedFriendInfos.size()>0){
				myFocusedFriendInfos.clear();
			}
			
			getDatafromHttp("fans_list");
			
			break;
		case R.id.layout_right:
			layout_left.setSelected(false);
			layout_right.setSelected(true);
			layout_center.setSelected(false);
			
			HttpUtils httpUtils = new HttpUtils();
			String url = "http://jlb.oular.com/mobileapp.php";
			RequestParams params = new RequestParams();
			params.addQueryStringParameter("mod","home");
			params.addQueryStringParameter("action","spacecp");
			params.addQueryStringParameter("op","recommenduser");
			params.addQueryStringParameter("sauthtoken","124d330e1ebf869263c31d4cbda5fe37");
			params.addQueryStringParameter("mobile","2");
			
			httpUtils.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(MyfriendsActivity.this, "网络连接异常······", Toast.LENGTH_LONG)
					.show();
					
				}

				@Override
				public void onSuccess(ResponseInfo<String> resultstr) {
					// TODO Auto-generated method stub
					if(resultstr!=null){
						Gson gson = new Gson();
						Type type = new TypeToken<RecommendFriendsEntity>(){}.getType();
						Log.i("app",resultstr.result);
						RecommendFriendsEntity entity = gson.fromJson(resultstr.result, type);
						recommendFriendsInfos = entity.getData();
						
						Log.i("app", recommendFriendsInfos.toString());
						
						/**
						 * 初始化适配器
						 */

						adapter1 = new MyBaseAdapter<RecommendFriendsInfo>(recommendFriendsInfos,
								MyfriendsActivity.this, R.layout.item_myfriends_fragment) {

							@Override
							public void convert(ViewHolder viewHolder,
									RecommendFriendsInfo item) {
								// TODO Auto-generated method stub

								viewHolder.setCircleImageByUri(R.id.iv_friends_header, item.getAvatar());
								viewHolder.setText(R.id.tv_friendsname,
										item.getUsername());
								viewHolder.setText(R.id.tv_friendsign,
										"推荐理由："+item.getReason());
								
							}
						};

						// 绑定监听器
						lv_item_friendListView.setAdapter(adapter);
					}
				}
			});
			break;
		
		}
	}
	
	/**
	 * 建立网络连接
	 */
	private void getDatafromHttp(String op_value) {
		// TODO Auto-generated method stub

		HttpUtils httpUtils = new HttpUtils();
		String url = "http://jlb.oular.com/mobileapp.php";
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("mod","home");
		params.addQueryStringParameter("action","space");
		params.addQueryStringParameter("op",op_value);
		params.addQueryStringParameter("sauthtoken","124d330e1ebf869263c31d4cbda5fe37");
		params.addQueryStringParameter("page","1");
		params.addQueryStringParameter("perpage","10");
		
		httpUtils.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MyfriendsActivity.this, "网络连接异常······", Toast.LENGTH_LONG)
				.show();
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> resultstr) {
				// TODO Auto-generated method stub
				if(resultstr!=null){
					Gson gson = new Gson();
					Type type = new TypeToken<MyFocusedFriendsEntity>(){}.getType();
					MyFocusedFriendsEntity entity = gson.fromJson(resultstr.result, type);
					myFocusedFriendInfos = entity.getData();
					
					Log.i("app", myFocusedFriendInfos.toString());
					
					/**
					 * 初始化适配器
					 */

					adapter = new MyBaseAdapter<MyFocusedFriendInfo>(myFocusedFriendInfos,
							MyfriendsActivity.this, R.layout.item_myfriends_fragment) {

						@Override
						public void convert(ViewHolder viewHolder,
								MyFocusedFriendInfo item) {
							// TODO Auto-generated method stub

							viewHolder.setCircleImageByUri(R.id.iv_friends_header, item.getAvatar());
							viewHolder.setText(R.id.tv_friendsname,
									item.getFusername());
							viewHolder.setText(R.id.tv_friendsign,
									item.getThread_subject_last());
							
						}
					};

					// 绑定监听器
					lv_item_friendListView.setAdapter(adapter);
				}
			}
		});
	}
	/**
	 * 下拉刷新的效果
	 */
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		 handler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
	}

	

}
