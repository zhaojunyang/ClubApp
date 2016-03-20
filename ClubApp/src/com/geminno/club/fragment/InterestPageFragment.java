package com.geminno.club.fragment;


import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request.Method;
import com.example.clubapp.R;
import com.geminno.club.activity.InvitationDetailActivity;
import com.geminno.club.adapter.InterestAdapter;
import com.geminno.club.model.Activity;
import com.geminno.club.model.Invitation;
import com.geminno.club.model.User;
import com.geminno.club.view.MyrefreshListView;
import com.geminno.club.view.MyrefreshListView.OnRefreshCallBack;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.event.OnItemLongClick;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class InterestPageFragment extends BaseFragment {
			MyrefreshListView lv ;
			List<Object> lists =  new ArrayList<Object>();
			Handler handler = new Handler();
			InterestAdapter adapter ;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//首页
//		TextView tv=new TextView(getActivity());
//		tv.setText("首页兴趣圈");
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.interest_listview, null);
		return v;
	}
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		lv = (MyrefreshListView) getView().findViewById(R.id.lv_interest_lv);
	}

	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
			initData();
			
			
	}
	//初始化数据
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		//联网
//		
//		HttpUtils  http = new HttpUtils();
//		RequestParams params = new RequestParams();
//		params.addBodyParameter("aa", "aa");
//		params.addBodyParameter("aa", "aa");
//		String url = "ggh";
//		
		
		
		
		
		User user  =  new User(1, 100, "小黑", "123", "xiaohei", null, null, "男", "苏州", "zzz");
		lists.add(new Invitation(user, "帖子1", "R.drawable.interest_person_images", 10, 11));
		lists.add(new com.geminno.club.model.Activity(user, "活动1", 10, "R.drawable.interest_person_images", 12));
		lists.add(new Invitation(user, "帖子2", "R.drawable.interest_person_images", 21, 22));
		lists.add(new com.geminno.club.model.Activity(user, "活动2", 20, "R.drawable.interest_person_images", 12));
		lists.add(new Invitation(user, "帖子3", "R.drawable.interest_person_images", 32, 33));
		lists.add(new com.geminno.club.model.Activity(user, "活动3", 30, "R.drawable.interest_person_images", 12));
		lists.add(new Invitation(user, "帖子4", "R.drawable.interest_person_images", 43, 44));
		lists.add(new com.geminno.club.model.Activity(user, "活动4", 40, "R.drawable.interest_person_images", 12));
		adapter = new InterestAdapter(lists, getActivity());
		lv.setAdapter(adapter);
		lv.setOnRefreshCallBack(new OnRefreshCallBack() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						lists.clear();
						List<Object> lists1 =  new ArrayList<Object>();
						User user1  =  new User(1, 100, "葫芦", "123", "xiaohei", null, null, "男", "苏州", "zzz");
						lists.add(new Invitation(user1,"刷新帖子1", "R.drawable.interest_person_images", 10, 11));
						lists.add(new com.geminno.club.model.Activity(user1, "刷新活动1", 10, "R.drawable.interest_person_images", 12));
						lists.add(new Invitation(user1, "刷新帖子2", "R.drawable.interest_person_images", 21, 22));
						lists.add(new com.geminno.club.model.Activity(user1, "刷新活动2", 20, "R.drawable.interest_person_images", 12));
						lists.add(new Invitation(user1, "刷新帖子3", "R.drawable.interest_person_images", 32, 33));
						lists.add(new com.geminno.club.model.Activity(user1, "刷新活动3", 30, "R.drawable.interest_person_images", 12));
						lists.add(new Invitation(user1, "刷新帖子4", "R.drawable.interest_person_images", 43, 44));
						lists.add(new com.geminno.club.model.Activity(user1, "刷新活动4", 40, "R.drawable.interest_person_images", 12));
							 lists.addAll(lists1);
							 if(adapter!= null){
								 adapter.notifyDataSetChanged();
								 
							 }
							 lv.completeRefresh();
					}
				}, 2000
			);}
			
			@Override
			public void onPull() {
				// TODO Auto-generated method stub
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						List<Object> lists1 =  new ArrayList<Object>();
						User user1  =  new User(1, 100, "团长", "123", "xiaohei", null, null, "男", "苏州", "zzz");
						lists.add(new Invitation(user1,"加载帖子1", "R.drawable.interest_person_images", 10, 11));
						lists.add(new com.geminno.club.model.Activity(user1, "加载活动1", 10, "R.drawable.interest_person_images", 12));
						lists.add(new Invitation(user1, "加载帖子2", "R.drawable.interest_person_images", 21, 22));
						lists.add(new com.geminno.club.model.Activity(user1, "加载活动2", 20, "R.drawable.interest_person_images", 12));
						lists.add(new Invitation(user1, "加载帖子3", "R.drawable.interest_person_images", 32, 33));
						lists.add(new com.geminno.club.model.Activity(user1, "加载活动3", 30, "R.drawable.interest_person_images", 12));
						lists.add(new Invitation(user1, "加载帖子4", "R.drawable.interest_person_images", 43, 44));
						lists.add(new com.geminno.club.model.Activity(user1, "加载活动4", 40, "R.drawable.interest_person_images", 12));
							 lists.addAll(lists1);
						if(adapter!=null){
							adapter.notifyDataSetChanged();
						}
						lv.completePull();
						
					}
				}, 2000);
				
			}
		});
		
		
	}

}
