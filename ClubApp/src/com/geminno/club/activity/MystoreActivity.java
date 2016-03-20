package com.geminno.club.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.example.clubapp.R;
import com.geminno.club.adapter.MyBaseAdapter;
import com.geminno.club.adapter.ViewHolder;
import com.geminno.club.entity.MyStoreEntity;
import com.geminno.club.entity.MyStoreInfo;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MystoreActivity extends Activity implements OnRefreshListener,
		OnItemClickListener, OnClickListener {
	// 左上方的返回图标
	@ViewInject(R.id.mystore_back)
	ImageView backImageView;
	// listview
	@ViewInject(R.id.lv_mystore)
	ListView lv_mystoreListView;
	// "搜索"两个字
	@ViewInject(R.id.tv_searchstr)
	TextView tv_searchstr;
	// “放大镜”图标
	@ViewInject(R.id.iv_search)
	ImageView iv_search;
	// 搜索编辑框
	@ViewInject(R.id.et_searchcontent)
	EditText et_storesearcher;
	// 活动或者帖子的头像
	@ViewInject(R.id.iv_store_header)
	ImageView iv_store_header;
	// 头像后面的用户名
	@ViewInject(R.id.iv_store_name)
	TextView iv_store_name;
	// 收藏的时间
	@ViewInject(R.id.iv_store_time)
	TextView iv_store_time;
	// 活动或者帖子的配的图片
	@ViewInject(R.id.iv_activityimage)
	ImageView iv_activity_header;
	// 活动或者帖子的标题
	@ViewInject(R.id.tv_avtivityname)
	TextView tv_avtivityname;
	// 活动和帖子的类型
	@ViewInject(R.id.tv_activitytype)
	TextView tv_activitytype;
	// 删除的图标
	@ViewInject(R.id.mystore_clear)
	ImageView mystore_clear;

	// 下拉刷新
	@ViewInject(R.id.mystores_swipe_ly)
	SwipeRefreshLayout mSwipeLayout;;

	MyStoreEntity myStoreEntity;
	ArrayList<MyStoreInfo> myStoreInfos;// 收藏列表的集合
	MyBaseAdapter<MyStoreInfo> adapter;// 适配器

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
		setContentView(R.layout.activity_mystore);
		ViewUtils.inject(this);

		mSwipeLayout.setOnRefreshListener(this);
		backImageView.setOnClickListener(this);
		mystore_clear.setOnClickListener(this);

		/**
		 * 设置刷新时候的颜色
		 */
		mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);

		// 网络连接，取回数据
		getDatafromHttp();

		/**
		 * 焦点的设置
		 */
		et_storesearcher.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean flag) {
				// TODO Auto-generated method stub
				if (flag == false && et_storesearcher.getText() == null) {
					iv_search.setVisibility(View.VISIBLE);
					tv_searchstr.setVisibility(View.VISIBLE);
					Log.i("app",
							"flag == false----------------------------------");
				} else {
					iv_search.setVisibility(View.INVISIBLE);
					tv_searchstr.setVisibility(View.INVISIBLE);
					Log.i("app",
							"flag == true--------------------------------------");
				}
			}
		});

		/**
		 * 搜索框的设置
		 */
		mystore_clear.setVisibility(View.INVISIBLE);
		et_storesearcher.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				Log.i("HomePageFragment", "ontextchange-- s:" + s + ",start:"
						+ start + ",before:" + before + ",count:" + count);
				Log.i("HomePageFragment", "s.length:" + s.length());

				if (s.length() != 0) {

					mystore_clear.setVisibility(View.VISIBLE);
				} else {
					
					mystore_clear.setVisibility(View.INVISIBLE);

				}

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mystore_back:
			finish();
			break;
		case R.id.mystore_clear:
			v.setSelected(true);
			et_storesearcher.getText().clear();
			v.setVisibility(View.INVISIBLE);
			break;

		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	private void getDatafromHttp() {
		// TODO Auto-generated method stub

		HttpUtils httpUtils = new HttpUtils();
		String url = "http://jlb.oular.com/mobileapp.php";
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("mod", "home");
		params.addQueryStringParameter("action", "space");
		params.addQueryStringParameter("op", "favorite_list");
		params.addQueryStringParameter("sauthtoken",
				"124d330e1ebf869263c31d4cbda5fe37");
		params.addQueryStringParameter("page", "1");
		params.addQueryStringParameter("perpage", "10");
		params.addQueryStringParameter("dataType", "");

		httpUtils.send(HttpMethod.GET, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						Toast.makeText(MystoreActivity.this, "网络连接异常······",
								Toast.LENGTH_LONG).show();

					}

					@Override
					public void onSuccess(ResponseInfo<String> resultstr) {
						// TODO Auto-generated method stub
						if (resultstr != null) {
							Gson gson = new Gson();
							Type type = new TypeToken<MyStoreEntity>() {
							}.getType();
							MyStoreEntity entity = gson.fromJson(
									resultstr.result, type);
							myStoreInfos = entity.getData();

							Log.i("app", myStoreInfos.toString());

							/**
							 * 初始化适配器
							 */

							adapter = new MyBaseAdapter<MyStoreInfo>(
									myStoreInfos, MystoreActivity.this,
									R.layout.item_mystore_fragment) {

								@Override
								public void convert(ViewHolder viewHolder,
										MyStoreInfo item) {
									// TODO Auto-generated method stub
									// 头像
									viewHolder.setCircleImageByUri(
											R.id.iv_store_header, 
											item.getFields().getAvatar());
									viewHolder.setText(R.id.iv_store_name, item
											.getFields().getAuthor());
									viewHolder.setText(R.id.iv_store_time,
											item.getFav_dateline());
									viewHolder.setCircleImageByUri(
											R.id.iv_activityimage,
											item.getPic());
									viewHolder.setText(R.id.tv_avtivityname,
											item.getTitle());
									viewHolder.setText(R.id.tv_activitytype,
											item.getIdtype());

								}
							};

							// 绑定监听器
							lv_mystoreListView.setAdapter(adapter);
						}
					}
				});
	}

}
