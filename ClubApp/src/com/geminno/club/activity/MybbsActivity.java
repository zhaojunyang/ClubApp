package com.geminno.club.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.example.clubapp.R;
import com.geminno.club.adapter.MyBaseAdapter;
import com.geminno.club.adapter.ViewHolder;
import com.geminno.club.entity.MyBbsEntity;
import com.geminno.club.entity.MyBbsInfo;

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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MybbsActivity extends Activity implements OnClickListener,OnItemClickListener,OnRefreshListener{

	@ViewInject(R.id.lv_item_bbs)
	ListView iv_item_bbsListView;
	
	@ViewInject(R.id.mybbs_back)
	ImageView iv_backImageView;
	
	@ViewInject(R.id.mybbs_swipe_ly)
	SwipeRefreshLayout mSwipeLayout;
	
	BaseAdapter adapter;
	private static final int REFRESH_COMPLETE = 0X110;
	
	ArrayList<MyBbsInfo> myBbsInfos;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {

			case REFRESH_COMPLETE:

				/**
				 * 鍛婅瘔閫傞厤鍣紝闆嗗悎鍐呭宸茬粡鍒锋柊锛岀洿鎺ユ帴鍦ㄥ悗闈紝涓嶄細璺冲埌绗竴鏉�
				 */
				adapter.notifyDataSetChanged();

				/**
				 * 璁剧疆鍒锋柊缁撴潫涔嬪悗锛屽湀鍋滄杞姩
				 */
				mSwipeLayout.setRefreshing(false);
				break;
			}

		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mybbs);
		ViewUtils.inject(this);
		//璁剧疆鐩戝惉
		iv_item_bbsListView.setOnItemClickListener(this);
		iv_backImageView.setOnClickListener(this);
		mSwipeLayout.setOnRefreshListener(this);
		//鍒濆鍖栭泦鍚�
		myBbsInfos = new ArrayList<MyBbsInfo>();
		
		mSwipeLayout.setColorScheme(
				 android.R.color.holo_blue_bright,
				 android.R.color.holo_green_light,
				 android.R.color.holo_orange_light,
				 android.R.color.holo_red_light);
		
		getdatafromhttp();
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mybbs_back:
			finish();
			break;
		}
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		 handler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
	}

	/**
	 * 缃戠粶杩炴帴鍙栨暟鎹�
	 */
	public void getdatafromhttp(){
		HttpUtils httpUtils = new HttpUtils();
	
		String url = "http://jlb.oular.com/mobileapp.php";
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("mod","forum");
		params.addQueryStringParameter("action","my_guide");
		params.addQueryStringParameter("sauthtoken","124d330e1ebf869263c31d4cbda5fe37");
		params.addQueryStringParameter("viewtype","thread");
		params.addQueryStringParameter("page","1");
		params.addQueryStringParameter("perpage","10");
		params.addQueryStringParameter("special","");
		
		httpUtils.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MybbsActivity.this, "缃戠粶杩炴帴寮傚父路路路路路路", Toast.LENGTH_LONG)
				.show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> resultstr) {
				// TODO Auto-generated method stub
				if(resultstr!=null){
					Gson gson = new Gson();
					Type type = new TypeToken<MyBbsEntity>(){}.getType();
					MyBbsEntity bbsEntity = gson.fromJson(resultstr.result, type);
					myBbsInfos = bbsEntity.getData();
					
					Log.i("app", myBbsInfos.toString());
					
					adapter = new MyBaseAdapter<MyBbsInfo>(myBbsInfos, MybbsActivity.this, R.layout.item_mybbs_fragment) {

						@Override
						public void convert(ViewHolder viewHolder,
								MyBbsInfo item) {
							// TODO Auto-generated method stub
							viewHolder.setText(R.id.tv_bbs_name, item.getSubject());
							viewHolder.setText(R.id.tv_bbs_sendtime, item.getDbdateline());
						}
					};
					
					iv_item_bbsListView.setAdapter(adapter);
				}
			}
		});
	}
}
