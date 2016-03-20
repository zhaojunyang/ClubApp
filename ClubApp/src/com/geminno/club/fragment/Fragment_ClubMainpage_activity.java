package com.geminno.club.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.clubapp.R;
import com.geminno.club.view.RefreshListView;
import com.geminno.club.view.RefreshListView.OnRefreshCallBack;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_ClubMainpage_activity extends BaseFragment{
	
	private RefreshListView lv_clubmainpage_activities;
	private List<View> views;
	//用来模拟数据的集合
	private Handler handler= new Handler();
	private Boolean isRefresh = true;
	private ClubActivityAdapter clubActivityAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container
			, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_clubmainpage_activity, null);
		
		return v;
	}
	
	@Override
	public void initView() {
		// TODO 初始化活动区的控件
		lv_clubmainpage_activities = (RefreshListView) getView().findViewById(R.id.clubmainpage_lv_activities);

		
		//模拟数据
		
		views = new ArrayList<View>();
		views.add(new View(getActivity()));
		views.add(new View(getActivity()));
		views.add(new View(getActivity()));
		
		 
		clubActivityAdapter = new ClubActivityAdapter();
		//为listView设置Adapter
		lv_clubmainpage_activities.setAdapter(clubActivityAdapter);
		
	}
	
	
	//
	private class ViewHolder{
		private ImageView iv_pic;
		private TextView tv_title;
		private TextView tv_time;
	}
	
	private class  ClubActivityAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return views.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			//View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_clubmainpage_activity, null);
			ViewHolder vh = null;
			if(convertView == null){
				vh = new ViewHolder();
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_clubmainpage_activity, null);
				vh.iv_pic = (ImageView) convertView.findViewById(R.id.clubmainpage_act_bg);
				vh.tv_title = (TextView) convertView.findViewById(R.id.clubmainpage_tv_actname);
				vh.tv_time = (TextView) convertView.findViewById(R.id.clubmainpage_tv_acttime);
				convertView.setTag(vh);
			}else{
				vh = (ViewHolder) convertView.getTag();
			}

			
			return convertView;
			
		}
		
	}
	
	
	
	@Override
	public void initEvent() {
		// TODO Auto-generated method stub
	
		lv_clubmainpage_activities.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				
				// TODO Auto-generated method stub
				if(isRefresh){
					Toast.makeText(getActivity(), "点击相应的活动", 0).show();
				}
			}
		});
		
		lv_clubmainpage_activities.setOnRefreshCallBack(new OnRefreshCallBack() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				//延迟2秒
				isRefresh = false;
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						downToRefreshData();
					}

					
				}, 2000);
			}
			
			@Override
			public void onPull() {
				// TODO Auto-generated method stub
				isRefresh = false;
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						upToLoadData();
					}

				
				}, 2000);
			}
		});
		
	}
	
	private void  upToLoadData(){
		//连接网路加载数据
		views.add(new View(getActivity()));
		views.add(new View(getActivity()));
		views.add(new View(getActivity()));
		views.add(new View(getActivity()));
		views.add(new View(getActivity()));
		views.add(new View(getActivity()));
		
		clubActivityAdapter.notifyDataSetChanged();
		
		// TODO 上拉加载连接网络获取数据后，还原
		lv_clubmainpage_activities.completePull();
		isRefresh = true;
	}
	
	
	private void downToRefreshData(){
		//连接网络加载数据，这里就模拟一下数据
		views.clear();
		views.add(new View(getActivity()));
		views.add(new View(getActivity()));
		views.add(new View(getActivity()));
		views.add(new View(getActivity()));
		views.add(new View(getActivity()));
		views.add(new View(getActivity()));
		views.add(new View(getActivity()));
		
		if(clubActivityAdapter!= null){
			clubActivityAdapter.notifyDataSetChanged();
		}
		
		//完成加载后控件复原
		lv_clubmainpage_activities.completeRefresh();
		isRefresh = true;
	}
	
	
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

}
