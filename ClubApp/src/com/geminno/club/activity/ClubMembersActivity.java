package com.geminno.club.activity;

import java.lang.reflect.Type;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import com.example.clubapp.R;
import com.geminno.club.adapter.CommonBaseAdapter;
import com.geminno.club.adapter.CommonBaseAdapter.ViewHolder;
import com.geminno.club.model.ClubMembers;
import com.geminno.club.model.ClubMembersInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ClubMembersActivity extends Activity {
	private String url = "http://jlb.oular.com/mobileapp.php";
	
	//编辑输入栏
	private EditText et_search_name;
	//成员列表
	private ListView lv_members;
	//模拟数据
	private ClubMembersInfo clubMembersInfo;
	//删除信息图标
	private ImageView iv_delete ;
	//返回图标
	private ImageView iv_back ;
	private int count = 1;
	private Context mContext ;
	//查询出来的新数据
	private List<ClubMembers> newList = new ArrayList<ClubMembers>();
	private CommonBaseAdapter<ClubMembers> commonBaseAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_club_members);
		mContext = this;
		//初始化控件
		
		et_search_name = (EditText) findViewById(R.id.club_members_et_name);
		lv_members = (ListView) findViewById(R.id.club_members_lv);
		iv_delete = (ImageView) findViewById(R.id.club_friends_iv_delete);
		iv_back = (ImageView) findViewById(R.id.club_members_back);
		
		//获取传入的俱乐部ID
		int club_id = getIntent().getIntExtra("club_id_Members", 0);
		
		//连接网络获取所有的数据
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		//mod=group&action=memberlist&mobile=2&page=1&fid=40&op=alluser
		params.addQueryStringParameter("mod", "group");
		params.addQueryStringParameter("action", "memberlist");
		params.addQueryStringParameter("mobile", "2");
		params.addQueryStringParameter("page", count+"");
		params.addQueryStringParameter("fid", club_id+"");
		params.addQueryStringParameter("op", "alluser");
		
		http.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "网络异常", 0).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result = arg0.result;
				Log.i("ClubMembersActivity", result);
				Gson gson = new Gson();
				//Type type = new TypeToken<ClubMembersInfo>(){}.getType();
				
				clubMembersInfo = gson.fromJson(result, ClubMembersInfo.class);
				//设置控件的值
				Log.i("ClubMembersActivity", clubMembersInfo.toString());
				initData(clubMembersInfo.getData());
			}
			
			
		});
		
		
		//设置适配器
		//lv_members.setAdapter(new ClubMembersAdapter());
		
		//为输入框添加点击事件
		et_search_name.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//清除存放查询的list
				newList.clear();
				// TODO 显示和隐藏删除图标
				if(s.length()!=0){
					iv_delete.setVisibility(View.VISIBLE);
					iv_delete.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO 清空输入框的内容
							et_search_name.setText("");
						}
					});
				}else{
					iv_delete.setVisibility(View.GONE);
				}
				
				if(et_search_name.getText() != null ){
					for (ClubMembers i : clubMembersInfo.getData()) {
						if(i.getUsername().contains(s)){
							newList.add(i);
						}
					}
					if(commonBaseAdapter!= null){
						initData(newList);
					}
					
				}
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO 按内容查找好友
				
				
			}


			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//添加返回事件
		iv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 退出
				finish();
			}
		});
		
	}

	/*private class ClubMembersAdapter extends BaseAdapter{
		
 		private List<String> positions;
		
 		public ClubMembersAdapter() {
			// TODO Auto-generated constructor stub
 			positions = new ArrayList<String>();
 		}
 		
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO 解决点赞问题和代码复用
			ViewHolder vh = null;
			if(convertView == null){
				
				vh = new ViewHolder();
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_club_friends, null);
				vh.iv_LOGO = (ImageView) convertView.findViewById(R.id.club_members_item_logo);
				vh.iv_shenfen = (ImageView) convertView.findViewById(R.id.club_members_iv_ismaster);
				vh.tv_name = (TextView) convertView.findViewById(R.id.club_members_item_name);
				vh.iv_level = (ImageView) convertView.findViewById(R.id.club_members_userlevel);
				vh.iv_isAttention = (ImageView) convertView.findViewById(R.id.club_members_iv_attention);
				vh.tv_brief = (TextView) convertView.findViewById(R.id.club_members_introduce);
				convertView.setTag(vh);
				
			}else{
			
				vh = (ViewHolder) convertView.getTag();
				
			}
			
			//为点击按钮添加tag
			vh.iv_isAttention.setTag(position);
			
			//判断该关注是否被点击过
			if(positions.contains(position+"")){
				vh.iv_isAttention.setSelected(true);
			}else{
				vh.iv_isAttention.setSelected(false);
			}
			
			//对vh上面的控件赋值
			
			vh.iv_isAttention.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 点击关注
					Toast.makeText(getApplicationContext(), "点击关注", 0).show();
					//判断当前的position是否在当前的集合中，在的话移除
					if(positions.contains(position+"")){
						positions.remove(position+"");
						v.setSelected(false);
						return;
					}
					v.setTag(position);
					v.setSelected(true);
					//2、所有点过赞的imageview，加到集合中;
					positions.add((Integer) v.getTag()+"");
					
				}
			});
			
			return convertView;
			
		}
		
		private class ViewHolder{
			private ImageView iv_LOGO;
			private TextView tv_name;
			private ImageView iv_shenfen;
			private ImageView iv_level;
			private ImageView iv_isAttention;
			private TextView tv_brief;
		}
		
		
	}
	*/
	private void initData(List<ClubMembers> list) {
		commonBaseAdapter = new CommonBaseAdapter<ClubMembers>(mContext,list,R.layout.item_club_friends) {

			private List<String> ids;

			@Override
			public void convert(com.geminno.club.adapter.CommonBaseAdapter.ViewHolder holder,
					final ClubMembers item) {
				//个人LOGO 
				holder.setRoundImageByUrl(R.id.club_members_item_logo, item.getAvatar());
				//个人名字
				holder.setText(R.id.club_members_item_name, item.getUsername());
				//个人的简介
				holder.setText(R.id.club_members_introduce, "接口中缺少的字段");
				
				if(item.getIs_friend()==1){
					holder.getView(R.id.club_members_iv_attention).setSelected(true);
				}
				
				
				//判断个人的等级
				switch (item.getLevel()) {
				case "1":
					//之后需要更改
					holder.setImageResource(R.id.club_members_userlevel, R.drawable.level_1);
					break;
				case "2":
					holder.setImageResource(R.id.club_members_userlevel, R.drawable.level_2);
					break;
				case "3":
					holder.setImageResource(R.id.club_members_userlevel, R.drawable.level_3);
					break;
				case "4":
					holder.setImageResource(R.id.club_members_userlevel, R.drawable.level_4);
					break;
				
				default:
					break;
				}
				
				ids = new ArrayList<String>();
				
				//为控件添加点击
				//为点击按钮添加tag
				holder.getView(R.id.club_members_iv_attention).setTag(item.getUid());
				
				//判断该关注是否被点击过
				if(ids.contains(item.getUid()+"")){
					holder.getView(R.id.club_members_iv_attention).setSelected(true);
				}else{
					holder.getView(R.id.club_members_iv_attention).setSelected(false);
				}
				
				//对vh上面的控件赋值
				
				holder.getView(R.id.club_members_iv_attention).setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO 点击关注
						Toast.makeText(getApplicationContext(), "点击关注", 0).show();
						//判断当前的position是否在当前的集合中，在的话移除
						if(ids.contains(item.getUid()+"")){
							ids.remove(item.getUid()+"");
							v.setSelected(false);
							return;
						}
						v.setTag(item.getUid());
						v.setSelected(true);
						//2、所有点过赞的imageview，加到集合中;
						ids.add( v.getTag()+"");
						
					}
				});
				
			}
			
			
			
		};
		lv_members.setAdapter(commonBaseAdapter);
	}
}
