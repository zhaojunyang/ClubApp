package com.geminno.club.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.clubapp.R;
import com.geminno.club.adapter.MyBaseAdapter;
import com.geminno.club.adapter.ViewHolder;
import com.geminno.club.entity.MessageEntity;
import com.geminno.club.entity.MessageInfo;
import com.geminno.club.model.LoginRegisterEntity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Administrator 石然 2016-3-13
 */
public class MessagePageFragment extends BaseFragment implements
		OnRefreshListener {

	private static final int REFRESH_COMPLETE = 0X110;
	private SwipeRefreshLayout mSwipeLayout;
	private ListView listView;
	private MyBaseAdapter<MessageInfo> adapter;

	private List<MessageInfo> messageInfos;// message集合
	private MessageEntity messageEntity;// 每一条message
	
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 首页

		View view = inflater.inflate(R.layout.messagepagefragment, null);
		Log.i("app", "onCreateView--------------");
		mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swipe_ly);
		listView = (ListView) view.findViewById(R.id.listview_message);
		return view;

	}

	@Override
	public void initView() {
		// // TODO Auto-generated method stub

		messageInfos = new ArrayList<MessageInfo>();
		messageEntity = new MessageEntity();

		/**
		 * 建立网络连接，获取系统消息
		 */
		HttpUtils http = new HttpUtils();
		String url = "http://jlb.oular.com/mobileapp.php";
		RequestParams params = new RequestParams();
		// 添加相应的参数
		params.addQueryStringParameter("mod", "home");
		params.addQueryStringParameter("action", "space");
		params.addQueryStringParameter("op", "message_list");
		params.addQueryStringParameter("sauthtoken",
				"124d330e1ebf869263c31d4cbda5fe37");
		params.addQueryStringParameter("page", "1");
		params.addQueryStringParameter("perpage", "10");
		params.addQueryStringParameter("filter", "announcepm");
		http.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "网络连接异常······", Toast.LENGTH_LONG)
						.show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> resultstr) {
				Log.i("app", resultstr.result);
				// TODO Auto-generated method stub
				if (resultstr.result != null) {
					Gson gson = new Gson();
					Type type = new TypeToken<MessageEntity>() {
					}.getType();
					messageEntity = gson.fromJson(resultstr.result, type);

					messageInfos = messageEntity.getData();

					Log.i("app", messageInfos.toString());

					/**
					 * 初始化适配器
					 */

					adapter = new MyBaseAdapter<MessageInfo>(messageInfos,
							getActivity(), R.layout.item_message) {

						@Override
						public void convert(ViewHolder viewHolder,
								MessageInfo item) {
							// TODO Auto-generated method stub

							viewHolder.setText(R.id.tv_messagename,
									item.getAuthor() + "");
							viewHolder.setText(R.id.tv_messagedetial,
									item.getMessage() + "");
							viewHolder.setText(R.id.tv_messagetime,
									item.getDateline() + "");
						}
					};

					// 绑定监听器
					listView.setAdapter(adapter);
					
				}
			}
		});

	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub

		 mSwipeLayout.setOnRefreshListener(this);
		 /**
		 * 设置刷新时候的颜色
		 */
		 mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
		 android.R.color.holo_green_light,
		 android.R.color.holo_orange_light, android.R.color.holo_red_light);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}

	/**
	 * 当向下拉动的时候，会调用这个方法
	 */
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		 handler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
	}
}