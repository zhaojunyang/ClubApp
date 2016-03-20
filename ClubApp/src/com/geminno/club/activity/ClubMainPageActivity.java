package com.geminno.club.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.clubapp.R;
import com.geminno.club.adapter.CommonBaseAdapter;
import com.geminno.club.adapter.CommonBaseAdapter.ViewHolder;
import com.geminno.club.fragment.BaseFragment;
import com.geminno.club.fragment.Fragment_ClubMainpage_activity;
import com.geminno.club.fragment.Fragment_ClubMainpage_topic;
import com.geminno.club.model.Club;
import com.geminno.club.util.MUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class ClubMainPageActivity extends Activity {
	//获取信息URL
	private String url = "http://jlb.oular.com/mobileapp.php";
	
	private ImageView clubmainpage_iv_back;//返回按钮
	private ImageView clubmainpage_join;//是否加入按钮
	private TextView clubmainpage_Activities;
	private TextView clubmainpage_Taolun;
	private TextView line1;
	private TextView line2;
	private int currentFragment = 0;
	private Fragment_ClubMainpage_topic topic_Fragment;
	private Fragment_ClubMainpage_activity activity_Fragment;
	private List<BaseFragment> fragments;
	private ImageView iv_club_LOGO;
	private Context mContext;
	private Club club;
	private CommonBaseAdapter<Club> commonBaseAdapter;
	
	private TextView club_name;

	private TextView club_location;

	private TextView club_type;

	private TextView club_invitation_num;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_club_main_page);
		//找到界面上的对应控件
		mContext = this;
		//返回按钮
		clubmainpage_iv_back = (ImageView) findViewById(R.id.clubmaipage_back);
		//加入按钮
		clubmainpage_join = (ImageView) findViewById(R.id.clubmaipage_clubjoin);
		//活动按钮
		clubmainpage_Activities = (TextView) findViewById(R.id.clubmaipage_tv__activities);
		//讨论区按钮
		clubmainpage_Taolun = (TextView) findViewById(R.id.clubmaipage_tv__taolun);
		//活动区下面的线条
		line1 = (TextView) findViewById(R.id.clubmaipage_tv__line1);
		//讨论区下面的线条
		line2 = (TextView) findViewById(R.id.clubmaipage_tv__line2);
		//俱乐部头像
		
		iv_club_LOGO = (ImageView) findViewById(R.id.roundclubmaipage_clubjoin);
		//俱乐部的名字
		
		club_name = (TextView) findViewById(R.id.clubmaipage_clubname);
		
		//俱乐部地 定位
		
		club_location = (TextView) findViewById(R.id.clubmaipage_tv__clublocation);
		
		//俱乐部的类型
		
		club_type = (TextView) findViewById(R.id.clubmaipage_tv__type);
		
		//俱乐部的帖子数
		
		club_invitation_num = (TextView) findViewById(R.id.clubmaipage_tv__topics);
		
		//View clubmainpage_fragment = findViewById(R.id.clubmainpage_fragment);
		
		//初始化fragment
		topic_Fragment = new Fragment_ClubMainpage_topic(); 
		activity_Fragment = new Fragment_ClubMainpage_activity();
		fragments = new ArrayList<BaseFragment>();
		fragments.add(topic_Fragment);
		fragments.add(activity_Fragment);
		
		//初始化界面上的数据
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		//mod=group&action=grouplist&client=android&page=1&perpage=10&fid=66
		Intent intent = getIntent();
		//获得点击俱乐部的id
		int fid = intent.getIntExtra("club_id", 0);
		Log.i("ClubMainPageActivity", fid+"");
		params.addQueryStringParameter("mod", "group");
		params.addQueryStringParameter("action", "grouplist");
		params.addQueryStringParameter("client", "android");
		params.addQueryStringParameter("page", "1");
		params.addQueryStringParameter("perpage", "10");
		params.addQueryStringParameter("fid", fid+"");
		http.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "连接网络失败", 0).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				 String result = arg0.result;
				try {
					JSONObject json = new JSONObject(result);
					int err_code = json.getInt("err_code");
					Log.i("czyTest", result);
					if(err_code==0){
						JSONArray arr = json.getJSONArray("data");
						for(int i=0;i<arr.length();i++){
							JSONObject json2 = arr.getJSONObject(i);
							int club_id = json2.getInt("fid");
							String club_name = json2.getString("name");//俱乐部名字
							String club_LOGO = json2.getString("icon");//俱乐部头像
							String club_brief = json2.getString("description");//俱乐部描述
							int addNumber = json2.getInt("membernum");//俱乐部成员数量
							String gprovince = json2.getString("gprovince");//俱乐部的省份
							String gcity = json2.getString("gcity");//俱乐部的城市
							String club_type = json2.getString("group_class");//俱乐部的类型
							int invitation_posts = Integer.parseInt(json2.getString("posts"));//俱乐部的帖子数量
							//拼字符串，正确的地址
							String club_location = gprovince+gcity;
							
							//俱乐部详情中需要的数据
							String club_Dateline = json2.getString("dateline");//创建俱乐部的时间
							String founderUid = json2.getString("founderuid");
							String founderUrl = json2.getString("avatar");
							String founderName = json2.getString("foundername");
							int is_join = json2.getInt("is_join");
							//实例化对象
							club = new Club(club_id, club_name,club_LOGO,
									club_brief,addNumber,club_location,club_type,
									invitation_posts, club_Dateline, founderUid,  
									founderName,founderUrl, is_join);
							
							Log.i("czyTest", club.toString());
						}
						
						//获得数据后将控件赋值
						club_name.setText(club.getClub_name());
						if(club.getIs_join()==1){
							clubmainpage_join.setSelected(true);
						}
						club_location.setText(club.getClub_location());
						club_type.setText(club.getClub_type());
						club_invitation_num.setText(club.getInvitation_posts()+"个帖子");
						
						//开子线程获取图片
						String url1 = club.getClub_LOGO();
						MUtils.useVolleyForRoundImage(mContext, iv_club_LOGO, url1);
						
					}else{
						//弹出错误信息，提示接口无法使用
						Toast.makeText(getApplicationContext(), "接口无法使用", 0).show();
						return;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		});
				
		
		
		//传入俱乐部id到fragment
		Bundle bundle = new Bundle();
		bundle.putString("club_id", fid+"");
		topic_Fragment.setArguments(bundle);
		
		//初始化给一个默认的fragment
		getFragmentManager().beginTransaction().add(R.id.clubmainpage_fragment, topic_Fragment).commit();
		
		
		//给控件添加相应的事件
		clubmainpage_iv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "点击返回", 0).show();
				finish();
				
			}
		});
		
		clubmainpage_join.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 如果没有加入的话
				
				if(!clubmainpage_join.isSelected()){
					Toast.makeText(getApplicationContext(), "点击加入", 0).show();
					//跳转至添加信息界面
					Intent intent = new Intent(ClubMainPageActivity.this,AddClubInfoActivity.class);
					//请求码1表示去填写信息
					startActivityForResult(intent, 1);
				}
				
				
				
			}
		});
		
		//切换活动区Fragment
		clubmainpage_Activities.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 点击活动区
				Toast.makeText(getApplicationContext(), "点击活动", 0).show();
				//互换活动区和讨论区的颜色
				//选中的颜色#5E8D5E，#589755没有选中的颜色#CFCFCF，#ffffff
				clubmainpage_Activities.setTextColor(Color.parseColor("#5E8D5E"));
				line1.setBackgroundColor(Color.parseColor("#589755"));
				
				clubmainpage_Taolun.setTextColor(Color.parseColor("#CFCFCF"));
				line2.setBackgroundColor(Color.parseColor("#ffffff"));
				//改变Fragment
				int nextFragment = 1;
				changeFragment(nextFragment);
				
			}

			
		});
		
		//Fragment的切换
		clubmainpage_Taolun.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 点击讨论
				Toast.makeText(getApplicationContext(), "点击讨论区", 0).show();
				//讨论区与活动的颜色互换
				clubmainpage_Activities.setTextColor(Color.parseColor("#CFCFCF"));
				line1.setBackgroundColor(Color.parseColor("#ffffff"));
				
				clubmainpage_Taolun.setTextColor(Color.parseColor("#5E8D5E"));
				line2.setBackgroundColor(Color.parseColor("#589755"));
				
				int nextFragment = 0;
				changeFragment(nextFragment);
				
				
			}
		});
		
		
		//俱乐部头像点击进入俱乐部详情
		iv_club_LOGO.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 跳转至俱乐部详情页面
				Intent intent = new Intent(ClubMainPageActivity.this,ClubDetailsActivity.class);
				intent.putExtra("Club",club);
				startActivity(intent);
			}
		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode ==1  && resultCode == RESULT_OK){
			//点击直接加入
			clubmainpage_join.setSelected(true);
		}
	}
	
	
	
	
	/**
	 * 改变fragment
	 * @param nextIndex
	 */
	private void changeFragment(int nextIndex) {
		// TODO 改变显示的Fragment
		//判断点击的是否是当前显示的:不是
		if(currentFragment!=nextIndex){
		FragmentTransaction	 transaction=getFragmentManager().beginTransaction();
		
		transaction.hide(fragments.get(currentFragment));
		
		//判断nextIndex是否添加过:没有添加过，添加
		if(!fragments.get(nextIndex).isAdded()){
			transaction.add(R.id.clubmainpage_fragment,fragments.get(nextIndex));
		}
		
		transaction.show(fragments.get(nextIndex)).commit();
		}
		
		//改变currentIndex值：当前显示的fragment
		currentFragment=nextIndex;
		
		
		
	}
	
}
