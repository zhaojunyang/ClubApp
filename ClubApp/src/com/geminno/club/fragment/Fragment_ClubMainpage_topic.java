package com.geminno.club.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.clubapp.R;
import com.geminno.club.activity.ClubMainPageActivity;
import com.geminno.club.activity.InvitationDetailActivity;
import com.geminno.club.activity.Release_InvitationActivity;
import com.geminno.club.adapter.CommonBaseAdapter;
import com.geminno.club.model.ClubInvitationFields;
import com.geminno.club.model.ClubMembersInfo;
import com.geminno.club.model.InvitationContent;
import com.geminno.club.model.InvitationListInfo;
import com.geminno.club.view.RefreshListView;
import com.geminno.club.view.RefreshListView.OnRefreshCallBack;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Fragment_ClubMainpage_topic extends BaseFragment{
	private String url = "http://jlb.oular.com/mobileapp.php";
	private View topic_relation;
	private RefreshListView lv_topics;
	private List<View> views;
	//用来模拟数据的集合
	private Handler handler= new Handler();
	private Boolean isRefresh = true;
	private int count = 1;//表示刷新的页数
	private InvitationListInfo invitationListInfo;
	private List<InvitationContent> invitationContent;
	private CommonBaseAdapter<InvitationContent> topicAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container
			, Bundle savedInstanceState) {
		// TODO 讨论区的fragment
		View v  = inflater.inflate(R.layout.fragment_clubmainpage_topic, null);
		
		return v;
	}
	
	
	@Override
	public void initView() {
		// TODO 初始化控件
		//话题的listView
		lv_topics = (RefreshListView) getView().findViewById(R.id.clubmainpage_lv_topic);
		//获得主页传递过来的俱乐部id
		Bundle bundle = getArguments();
		int club_id = Integer.parseInt(bundle.getString("club_id"));
		Log.i("Fragment_ClubMainpage_topic", club_id+"");
		//连接数据库获取帖子列表
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		//mod=forum&action=threadlist&page=1&perpage=10&fid=59&digest=0
		params.addQueryStringParameter("mod", "forum");
		params.addQueryStringParameter("action", "threadlist");
		params.addQueryStringParameter("page", count+"");
		params.addQueryStringParameter("perpage", "10");
		params.addQueryStringParameter("fid", club_id+"");
		params.addQueryStringParameter("digest", "0");//0表示一般的帖子；1表示精华帖子
		
		
		http.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

			
		

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "连接失败", 0).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result = arg0.result;
				
				Gson gson = new Gson();
				invitationListInfo = gson.fromJson(result, InvitationListInfo.class);
				if(invitationListInfo.getErr_code()==0){
					invitationContent =  invitationListInfo.getData();
					
					topicAdapter = new CommonBaseAdapter<InvitationContent>(getActivity(),invitationContent,R.layout.item_clubmainpage_topics) {

						@Override
						public void convert(com.geminno.club.adapter.CommonBaseAdapter.ViewHolder holder,
								InvitationContent item) {
							// 设置帖子标题
							holder.setText(R.id.club_mainpage_topics_content_tv, item.getTitle());
							
						}
					};
					//为控件赋值
					lv_topics.setAdapter(topicAdapter);
					
					//topicAdapter = new TopicAdapter();
					//设置适配器
					//lv_topics.setAdapter(topicAdapter);
					
				}else{
					Toast.makeText(getActivity(), "接口返回错误", 0).show();
				}
				
				
			}
		});
		
		
		//点击发送话题的布局
		topic_relation = getView().findViewById(R.id.clubmainpage_topicRelation);
		
		
		
		
		
	}
	
	
	@Override
	public void initEvent() {
		// TODO 所有控件的点击事件
		
		lv_topics.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				if(isRefresh){
					Toast.makeText(getActivity(), "点击帖子", 0).show();
					Intent intent = new Intent(getActivity(),InvitationDetailActivity.class);
					//跳转至帖子详情页面
					String  invitation_id = invitationContent.get(arg2-1).getId();
					Log.i("Fragment_ClubMainpage_topic", "invitation_id:"+invitation_id);
					
					startActivity(intent);
				}
				
			}
		});
		
		lv_topics.setOnRefreshCallBack(new OnRefreshCallBack() {
			
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
		
		
		topic_relation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 判断是否在刷新
				if(isRefresh){
					
					Toast.makeText(getActivity(), "点击发送话题", 0).show();
					Intent intent = new Intent(getActivity(),Release_InvitationActivity.class);
					startActivity(intent);
					
				}
				
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
		
		topicAdapter.notifyDataSetChanged();
		
		// TODO 上拉加载连接网络获取数据后，还原
		lv_topics.completePull();
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
		
		if(topicAdapter!= null){
			topicAdapter.notifyDataSetChanged();
		}
		
		//完成加载后控件复原
		lv_topics.completeRefresh();
		isRefresh = true;
	}
	
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
