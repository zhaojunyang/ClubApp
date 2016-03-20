package com.geminno.club.fragment;

import com.example.clubapp.R;
import com.geminno.club.activity.SameCityActivityActivity;
import com.geminno.club.activity.SameCityClubActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 
 * @author Administrator
 *石然 2016-3-13
 */
public class FindPageFragment extends BaseFragment implements OnClickListener{
	LinearLayout linearlayout_same_city_club;//同城俱乐部
	LinearLayout linearlayout_same_city_activity;//同城活动
	LinearLayout linearlayout_my_clubs;//加入的俱乐部
	
	ImageView iv_search;
	ImageView iv_locationImageView;
	TextView tv_locationameTextView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//发现
		View v=inflater.inflate(R.layout.find_page_fragment, null);
		return v;
	}
	@Override
	public void initView() {
		//初始化View
		linearlayout_same_city_activity = (LinearLayout) getView().findViewById(R.id.linearlayout_samecityactivity);
		linearlayout_same_city_club = (LinearLayout) getView().findViewById(R.id.linearlayout_samecityclub);
		linearlayout_my_clubs = (LinearLayout) getView().findViewById(R.id.linearlayout_myclubs);
	
		iv_search = (ImageView) getView().findViewById(R.id.find_page_fragment_search);
		iv_locationImageView = (ImageView) getView().findViewById(R.id.find_page_fragment_iv_location);
		tv_locationameTextView = (TextView) getView().findViewById(R.id.tv_location_name);
	}

	@Override
	public void initEvent() {
		//初始化事件
		linearlayout_same_city_club.setOnClickListener(this);
		linearlayout_same_city_activity.setOnClickListener(this);
		linearlayout_my_clubs.setOnClickListener(this);
		
		iv_search.setOnClickListener(this);
		iv_locationImageView.setOnClickListener(this);
		tv_locationameTextView.setOnClickListener(this);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.find_page_fragment_search:
			//点击放大镜
			break;
			
		//case R.id.find_page_fragment_location:
		//case R.id.location_name:
			//点击右上角的定位图标或者textview都可以进行定位
			//break;
			
		case R.id.linearlayout_samecityactivity:
			//跳转到同城活动列表
			Intent intentActivity=new Intent();
			//跳转找页面B
			intentActivity.setClass(getActivity(), SameCityActivityActivity.class);
			//打开Activity
			startActivity(intentActivity);
			
			break;
		case R.id.linearlayout_samecityclub:
			
			//跳转同城俱乐部
			Intent intentClub=new Intent();
			//跳转找页面B
			intentClub.setClass(getActivity(), SameCityClubActivity.class);
			//打开Activity
			startActivity(intentClub);
			break;
		case R.id.linearlayout_myclubs:
			//跳转到我加入的俱乐部
			break;
		}
		
	}

}
