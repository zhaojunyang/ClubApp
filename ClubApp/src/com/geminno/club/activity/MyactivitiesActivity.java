package com.geminno.club.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.example.clubapp.R;

import com.geminno.club.adapter.MyBaseAdapter;
import com.geminno.club.adapter.ViewHolder;
import com.geminno.club.entity.MyactivityEntity;
import com.geminno.club.entity.MyactivityInfo;
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
import android.content.ClipData.Item;
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
import android.widget.TextView;
import android.widget.Toast;

public class MyactivitiesActivity extends Activity implements OnItemClickListener,OnClickListener,OnRefreshListener{

	@ViewInject(R.id.lv_item_activity)
	ListView lvactivityListView;
	
	@ViewInject(R.id.myactivities_swipe_ly)
	SwipeRefreshLayout mSwipeLayout;
	
	@ViewInject(R.id.myactivities_back)
	ImageView backImageView;
	
	@ViewInject(R.id.iv_activity_header)
	ImageView iv_activity_header;
	
	@ViewInject(R.id.tv_avtivitiesname)
	TextView tv_avtivityname;
	
	@ViewInject(R.id.tv_activitytime)
	TextView tv_activitytime;
	
	ArrayList<MyactivityInfo> myactivityInfos;
	
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
		setContentView(R.layout.activity_myactivities);
		ViewUtils.inject(this);
		
		myactivityInfos = new ArrayList<MyactivityInfo>();
		//设置点击事件
		mSwipeLayout.setOnRefreshListener(this);
		lvactivityListView.setOnItemClickListener(this);
		backImageView.setOnClickListener(this);
		
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
		case R.id.myactivities_back:
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
	 * 网络连接取数据
	 */
	public void getdatafromhttp(){
		HttpUtils httpUtils = new HttpUtils();
		String url = "http://jlb.oular.com/mobileapp.php";
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("mod","group");
		params.addQueryStringParameter("action","my_activity_list");
		params.addQueryStringParameter("sauthtoken","124d330e1ebf869263c31d4cbda5fe37");
		params.addQueryStringParameter("page","1");
		params.addQueryStringParameter("perpage","10");
		httpUtils.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(MyactivitiesActivity.this, "网络连接异常······", Toast.LENGTH_LONG)
				.show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> resultstr) {
				// TODO Auto-generated method stub
				if(resultstr!=null){
					//拿到返回的json
					String resultString = resultstr.result;
					//将其中的class全部替换成classes,然后在进行解析
					String newresult = resultString.replaceAll("class", "classes");
					Gson gson = new Gson();
					Type type = new TypeToken<MyactivityEntity>(){}.getType();
					MyactivityEntity entity = gson.fromJson(newresult, type);
					myactivityInfos = entity.getData();
					
					Log.i("app", myactivityInfos.toString());
					adapter = new MyBaseAdapter<MyactivityInfo>(myactivityInfos, MyactivitiesActivity.this, R.layout.item_myavtivities_fragment) {

						@Override
						public void convert(ViewHolder viewHolder,
								MyactivityInfo item) {
							// TODO Auto-generated method stub
							/**
							 * 对照原型图，将对应的控件赋值
							 */
							viewHolder.setImageByUri(R.id.iv_activity_header, item.getPic());
							viewHolder.setText(R.id.tv_avtivitiesname, item.getFields().getFulltitle());
							viewHolder.setText(R.id.tv_activitytime, item.getFields().getTime());
						}
					};
					
					lvactivityListView.setAdapter(adapter);
				}
			}
		});
	}

}
