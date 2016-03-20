package com.geminno.club.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.example.clubapp.R;

import com.geminno.club.entity.MyClubsEntity;
import com.geminno.club.entity.MyClubsInfo;
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

public class MyclubsActivity extends Activity implements OnClickListener,OnRefreshListener,OnItemClickListener{

	@ViewInject(R.id.myclubs_back)
	ImageView back;
	
	@ViewInject(R.id.myclubs_swipe_ly)
	SwipeRefreshLayout mSwipeLayout;
	
	@ViewInject(R.id.lv_item_clubs)
	ListView lv_item_clubs;
	
	ArrayList<MyClubsInfo> clubsInfos;
	BaseAdapter adapter;
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
		setContentView(R.layout.activity_myclubs);		
		ViewUtils.inject(this);
		clubsInfos = new ArrayList<MyClubsInfo>();
		//设置监听事件
		back.setOnClickListener(this);
		mSwipeLayout.setOnRefreshListener(this);
		lv_item_clubs.setOnItemClickListener(this);
		
		mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
				 android.R.color.holo_green_light,
				 android.R.color.holo_orange_light,
				 android.R.color.holo_red_light);
		
		getdatafromhttp();
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.myclubs_back:
			finish();
			break;
		}
	}

	/**
	 * 网络连接区数据
	 */
	public void getdatafromhttp(){
		HttpUtils httpUtils = new HttpUtils();

		String url = "http://jlb.oular.com/mobileapp.php";
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("mod","group");
		params.addQueryStringParameter("action","my_grouplist");
		params.addQueryStringParameter("sauthtoken","124d330e1ebf869263c31d4cbda5fe37");

		httpUtils.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MyclubsActivity.this, "网络连接异常······", Toast.LENGTH_LONG)
				.show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> resultstr) {
				// TODO Auto-generated method stub
				if(resultstr!=null){
					String resultstring = resultstr.result+"\"}]}";
					Log.i("app", resultstring);
					Gson gson = new Gson();
					Type type = new TypeToken<MyClubsEntity>(){}.getType();
					MyClubsEntity entity = gson.fromJson(resultstring, type);
					clubsInfos = entity.getData();
					Log.i("app", clubsInfos.toString());
				}
			}
		});
	}
	
}
