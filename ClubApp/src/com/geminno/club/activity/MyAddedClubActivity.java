package com.geminno.club.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.clubapp.R;
import com.geminno.club.adapter.CommonBaseAdapter;
import com.geminno.club.adapter.CommonBaseAdapter.ViewHolder;
import com.geminno.club.model.Club;
import com.geminno.club.view.RefreshListView;
import com.geminno.club.view.RefreshListView.OnRefreshCallBack;
import com.geminno.club.view.RoundImageView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAddedClubActivity extends Activity {
	
	//需要访问的url(访问我加入的俱乐部)
	private String url = "http://jlb.oular.com/mobileapp.php";
	
	
	private final int REQUEST_BUILD = 3;
	
	//用来模拟数据
	 private List<Club> clubs = new ArrayList<Club>();
	
	private RefreshListView lv;

	private ImageView iv_back;

	private TextView tv_build;
	
	private CommonBaseAdapter<Club> commonBaseAdapter;
	
	//用来延迟刷新的动画
	private Handler handler = new Handler();
	
	//使用布尔类型判断是否在加载状态
	private boolean isRefresh = true;//表示可以进行其他操作
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_added_club);
		
		//找到界上的控件
		iv_back = (ImageView) findViewById(R.id.club_myclub_back);
		tv_build = (TextView) findViewById(R.id.club_myclub_tv_build);
		lv = (RefreshListView)findViewById(R.id.club_myclub_lv);
		
		iv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 退出
				finish();
			}
		});
		
		tv_build.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(isRefresh){
					// TODO 跳转到创建页面
					Intent intent = new Intent(MyAddedClubActivity.this,BulidClubActivity.class);
					
					startActivityForResult(intent, REQUEST_BUILD);
				}
				
			}
		});
		
		
		inidData();//初始化数据
		
		
		
		
		
		//为item添加点击事件
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO 跳转至俱乐部主页
				if(isRefresh){
					int club_id = clubs.get(arg2-1).getId();
					Intent intent = new Intent(MyAddedClubActivity.this,ClubMainPageActivity.class);
					intent.putExtra("club_id", club_id);
					Log.i("MyAddedClubActivity", "点击的索引"+arg2+"");
					Log.i("MyAddedClubActivity", club_id+"");
					startActivity(intent);
				}
			}
		});
		
		lv.setOnRefreshCallBack(new OnRefreshCallBack() {
			
			@Override
			public void onRefresh() {
				// TODO 下拉刷新，连接网络获取数据后，还原
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


	//初始化数据
	private void inidData() {
		//连接数据，
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("mod", "group");
		params.addQueryStringParameter("action", "my_grouplist");
		params.addQueryStringParameter("sauthtoken", "124d330e1ebf869263c31d4cbda5fe37");
		
		
		http.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				
				Toast.makeText(getApplicationContext(), "网络异常", 0).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				
				String reslut = arg0.result;
				try {
					JSONObject json = new JSONObject(reslut);
					int err_code = json.getInt("err_code");
					Log.i("czyTest", reslut);
					if(err_code==0){
						JSONArray arr = json.getJSONArray("data");
						for(int i=0;i<arr.length();i++){
							JSONObject json2 = arr.getJSONObject(i);
							int id = json2.getInt("fid");
							String club_name = json2.getString("name");
							String club_LOGO = json2.getString("icon");
							String club_brief = json2.getString("description");
							int club_number = json2.getInt("membernum");
							clubs.add(new Club(id,club_name,club_LOGO,club_brief,club_number));
							Log.i("czyTest", new Club(id,club_name,club_LOGO,club_brief,club_number).toString());
						}
						
						//获得数据后，设置适配器
						if(commonBaseAdapter==null){
							commonBaseAdapter = new CommonBaseAdapter<Club>(MyAddedClubActivity.this,clubs,R.layout.item_samecityclub) {
								
								@Override
								public void convert(ViewHolder holder, Club item) {
									// TODO Auto-generated method stub
									
									holder.setRoundImageByUrl(R.id.samecityclub_roundImageView1, item.getClub_LOGO());
									holder.setText(R.id.samecityclub_clubname, item.getClub_name());
									holder.setText(R.id.samecityclub_number, item.getAddNumber()+"人参加");
									holder.setText(R.id.samecityclub_detail, item.getClub_brief());
									
								}
							};
							
							lv.setAdapter(commonBaseAdapter);
						}
						
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
	}
	
	
	
	private void  upToLoadData(){
		//连接网路加载数据
		
		
		//myClubAdapter.notifyDataSetChanged();
		
		// TODO 上拉加载连接网络获取数据后，还原
		lv.completePull();
		isRefresh = true;
	}
	
	
	private void downToRefreshData(){
		//连接网络加载数据，这里就模拟一下数据
		clubs.clear();
		
		inidData();
		
		if(commonBaseAdapter!= null){
			commonBaseAdapter.notifyDataSetChanged();
		}
		
		//完成加载后控件复原
		lv.completeRefresh();
		isRefresh = true;
	}
	
	
	//等待完成创建
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_BUILD && resultCode== RESULT_OK){
			Toast.makeText(getApplicationContext(), "请耐心等待系统审批", 0).show();
		}
	
	}
	
	
		
	
}
