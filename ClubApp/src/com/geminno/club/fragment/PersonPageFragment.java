package com.geminno.club.fragment;


import java.lang.reflect.Type;

import com.example.clubapp.R;
import com.geminno.club.activity.MyactivitiesActivity;
import com.geminno.club.activity.MybbsActivity;
import com.geminno.club.activity.MyclubsActivity;
import com.geminno.club.activity.MyfriendsActivity;
import com.geminno.club.activity.MystoreActivity;
import com.geminno.club.activity.SettingActivity;
import com.geminno.club.activity.UserInfoActivity;
import com.geminno.club.adapter.ViewHolder;
import com.geminno.club.entity.PeopleCenterEntity;
import com.geminno.club.entity.PeopleCenterInfo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;



import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;


import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author Administrator
 *石然 2016-3-12
 */
public class PersonPageFragment extends BaseFragment implements OnClickListener{

	ImageView iv_settingImageView;//设置图标
	RelativeLayout layout_myinfo_areaLayout;//头像与昵称布局
	TextView peopleinfoname;//昵称
	ImageView personinfoimage;//头像框
	RelativeLayout layout_myinfo_myactivitys;
	RelativeLayout layout_myinfo_myfriends;
	RelativeLayout layout_myinfo_mybbs;
	RelativeLayout layout_myinfo_mystorys;
	RelativeLayout layout_myinfo_myclubs;
	
	
	
	PeopleCenterEntity peopleCenterEntity;
	PeopleCenterInfo peopleCenterInfo;

	Intent intent = new Intent();
	View personpagefragment;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//解析页面
		personpagefragment = inflater.inflate(R.layout.personpagefragment,null);
		
		return personpagefragment;
	}
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		/*
		 * 获取页面中的所有控件
		 */
		iv_settingImageView = (ImageView) personpagefragment.findViewById(R.id.myinfo_fragment_iv_setting);
		layout_myinfo_areaLayout = (RelativeLayout) personpagefragment.findViewById(R.id.myinfo_area);
		//五个框
		layout_myinfo_myactivitys = (RelativeLayout) personpagefragment.findViewById(R.id.myinfo_layout_myactivitys);
		layout_myinfo_myfriends = (RelativeLayout) personpagefragment.findViewById(R.id.myinfo_layout_myfriends);
		layout_myinfo_mybbs = (RelativeLayout) personpagefragment.findViewById(R.id.myinfo_layout_mybbs);
		layout_myinfo_mystorys = (RelativeLayout) personpagefragment.findViewById(R.id.myinfo_layout_mystorys);
		layout_myinfo_myclubs = (RelativeLayout) personpagefragment.findViewById(R.id.myinfo_layout_myclubs);
		
		peopleinfoname = (TextView) personpagefragment.findViewById(R.id.personinfo_name);//昵称
		personinfoimage = (ImageView) personpagefragment.findViewById(R.id.personinfo_image);//头像
		
		//初始化个人信息集合
		peopleCenterInfo = new PeopleCenterInfo();
		/**
		 * 建立网络连接，获取系统消息
		 */
		HttpUtils http = new HttpUtils();
		String url = "http://jlb.oular.com/mobileapp.php";
		RequestParams params = new RequestParams();
		// 添加相应的参数
		params.addQueryStringParameter("mod", "home");
		params.addQueryStringParameter("action", "space");
		params.addQueryStringParameter("op", "member_info");
		params.addQueryStringParameter("sauthtoken",
				"124d330e1ebf869263c31d4cbda5fe37");
		
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
					Type type = new TypeToken<PeopleCenterEntity>() {
					}.getType();
					peopleCenterEntity = gson.fromJson(resultstr.result, type);
					
					Log.i("app", peopleCenterEntity.toString());
					//得到用户对象
					peopleCenterInfo = peopleCenterEntity.getData();
					//当返回的数据，data部分部位空的时候，才需要赋值
					if(peopleCenterInfo!=null){	
					Log.i("app", peopleCenterInfo.toString());
					//设置用户名
					peopleinfoname.setText(peopleCenterInfo.getUsername());
					
					//设置头像
					BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
					bitmapUtils.display(personinfoimage, peopleCenterInfo.getAvatar());
					
					}
					
				}
			}
		});

	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub

		iv_settingImageView.setOnClickListener(this);
		layout_myinfo_areaLayout.setOnClickListener(this);
		
		layout_myinfo_myactivitys.setOnClickListener(this);
		layout_myinfo_myfriends.setOnClickListener(this);
		layout_myinfo_mybbs.setOnClickListener(this);
		layout_myinfo_mystorys.setOnClickListener(this);
		layout_myinfo_myclubs.setOnClickListener(this);
		
		
		
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		//点击右上角的“设置”
		case R.id.myinfo_fragment_iv_setting:
			
			intent.setClass(getActivity(), SettingActivity.class);
			
			break;
		//点击我的资料的区域
		case R.id.myinfo_area:
			
			intent.setClass(getActivity(), UserInfoActivity.class);
			//头像uri
			intent.putExtra("image_header", peopleCenterInfo.getAvatar());
			//昵称
			intent.putExtra("username", peopleCenterInfo.getUsername());
			//签名
			intent.putExtra("usersign", peopleCenterInfo.getSightml());
			break;
		//点击“我的活动”
		case R.id.myinfo_layout_myactivitys:
			
			intent.setClass(getActivity(), MyactivitiesActivity.class);
			
			break;
		//点击“我关注的好友”
		case R.id.myinfo_layout_myfriends:
			
			intent.setClass(getActivity(), MyfriendsActivity.class);
			
			break;
		//点击“我的发帖”
		case R.id.myinfo_layout_mybbs:
			
			intent.setClass(getActivity(), MybbsActivity.class);
			break;
		//点击“收藏”	
		case R.id.myinfo_layout_mystorys:
			
			intent.setClass(getActivity(), MystoreActivity.class);
			
			break;
			//点击“钱包”
		case R.id.myinfo_layout_myclubs:
			/**
			 * 跳到“我的俱乐部”页面
			 */
			//intent.setClass(getActivity(), MyclubsActivity.class);
			
			break;

		}
		//启动“intent”
		startActivity(intent);
	}
	


}
