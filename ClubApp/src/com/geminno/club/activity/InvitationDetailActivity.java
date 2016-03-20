package com.geminno.club.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.clubapp.R;
import com.geminno.club.view.MyCZYListView;
import com.geminno.club.view.MyCZYListView.OnRefreshCallBack;
public class InvitationDetailActivity extends Activity {
	public static final int REQUEST_REPLY = 2;
	
	//回复的listView
	private MyCZYListView lv_reply;
	
	//模拟数据
	private List<View> views;
	
	
	//返回按钮
	private ImageView iv_back ;
	
	//分享朋友圈图标
	private ImageView shareToCircle;
	
	//分享到微信好友
	private ImageView shareToWeixin;
	
	//更多分享图标
	private ImageView sharMore;
	
	//帖子的所属俱乐部
	private TextView tv_club_name;
	
	
	
	//发帖人的logo
	private ImageView iv_topicUserLOGO;
	
	//发帖人的名字
	private TextView tv_topicUserName;
	
	//关注图标
	private ImageView iv_isattention;
	
	//帖子名称
	private TextView tv_topicName;
	
	//是否是精华
	private ImageView iv_isJinghua;
	
	//帖子内容
	private TextView tv_topicContent;
	
	//帖子的图片
	private ImageView iv_topic_picture;
	
	//发帖时间
	private TextView tv_topicTime;
	
	//浏览人数
	private TextView tv_topicLookedNum;
	
	//点赞的人数
	private TextView tv_zanNumber;
	
	//点赞图标
	private ImageView iv_zanBody;
	
	//点赞人的头像：
	/**
	 * 这里有9个图标
	 * 有待实现
	 */
	private ImageView iv_zanPerson1;
	
	//底部的点赞图标
	private ImageView iv_zanBotton;
	
	//底部显示的点赞人数
	private TextView tv_zanButtonNumber;
	
	//回复的条数
	private TextView tv_replyNumber;
	
	//底部点赞的布局
	private View v_giveZan;
	
	//回复的布局
	private View v_givenReply;
	
	//布尔变量，用来判断该用户是否点过赞
	private boolean clickZan = false;

	private InvitationItemAdapter invitationItemAdapter;
	
	private Boolean isRefresh = true;
	
	private Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invitation_detail);
		
		/**
		 * 初始化界面
		 */
		lv_reply = (MyCZYListView) findViewById(R.id.myCZYListView1);
		
		iv_back = (ImageView) findViewById(R.id.club_invitation_detail);
		
		shareToCircle = (ImageView) findViewById(R.id.club_invitation_detail_share_weixin_circle);
		
		shareToWeixin = (ImageView) findViewById(R.id.club_invitation_detail_share_weixin);
		
		sharMore = (ImageView) findViewById(R.id.club_invitation_detail_sharemore);
		
		tv_club_name = (TextView) lv_reply.findViewById(R.id.club_invitation_detail_clubname);
		
		tv_topicUserName =	(TextView) lv_reply.findViewById(R.id.club_invitation_detail_username);
		
		iv_topicUserLOGO =   (ImageView) lv_reply.findViewById(R.id.club_invitation_detail_userlogo);
		
		iv_isattention =   (ImageView) lv_reply.findViewById(R.id.club_invitation_detail_isattention);
		
		tv_topicName =   (TextView) lv_reply.findViewById(R.id.club_invitation_detail_topictitle);
		
		iv_isJinghua =   (ImageView) lv_reply.findViewById(R.id.club_invitation_detail_isbest_topic);
		
		tv_topicContent =   (TextView) lv_reply.findViewById(R.id.club_invitation_detail_topiccontent);
		
		iv_topic_picture =   (ImageView) lv_reply.findViewById(R.id.club_invitation_detail_topic_pic);
		
		tv_topicTime =   (TextView) lv_reply.findViewById(R.id.club_invitation_detail_topictime);
		
		tv_topicLookedNum =   (TextView) lv_reply.findViewById(R.id.club_invitation_detail_tv_looknumber);
		
		tv_zanNumber =   (TextView) lv_reply.findViewById(R.id.club_invitation_detail_clicklikenum);
		
		iv_zanBody =   (ImageView) lv_reply.findViewById(R.id.club_invitation_detail_clickclikebtn);
		
		iv_zanPerson1 =   (ImageView) lv_reply.findViewById(R.id.club_invitation_detail6);
		
		iv_zanBotton =   (ImageView) findViewById(R.id.club_invitation_detail1);
		
		tv_zanButtonNumber =   (TextView) findViewById(R.id.club_invitation_detail_relation_clicknumber);
		
		tv_replyNumber =   (TextView) findViewById(R.id.club_invitation_detail_relation_replynumber);
		
		v_giveZan =   findViewById(R.id.club_invitation_detail_relation_clicklike);
		
		v_givenReply =   findViewById(R.id.club_invitation_detail_toreply);
		
		
		/**
		 * 给相应的控件添加点击事件
		 */
		
		iv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 退出
				finish();
			}
		});
		
		//点击分享朋友圈
		shareToCircle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					
					
				
			}
		});
		
		//点击分享到微信
		shareToWeixin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//点击分享到更多
		sharMore.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 点击分享更多，弹出分享弹窗
				
				showSharePopupWindow(v);
				
			}

		});
		
		//点击关注
		iv_isattention.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 点击关注
				if(!iv_isattention.isSelected()){
					iv_isattention.setSelected(true);
				}
				Toast.makeText(getApplicationContext(), "关注成功", 0).show();
			}
			
		});
		
		//进入发帖人的对外个人中心
		iv_topicUserLOGO.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "进入个人中心", 0).show();

			}
		});
		
		//sc中的点赞图标点赞
		iv_zanBody.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 进行点赞(添加动画)
				if(!clickZan){
					iv_zanBody.setSelected(true);
					iv_zanBotton.setSelected(true);
					clickZan = true;
					addClickZan(tv_zanNumber, tv_zanButtonNumber);
				}else{
					iv_zanBody.setSelected(false);
					iv_zanBotton.setSelected(false);
					clickZan = false;
					deleteClickZan(tv_zanNumber, tv_zanButtonNumber);
				}
				
			}
		});
		
		//底部的点赞布局的点击事件
		v_giveZan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!clickZan){
					iv_zanBotton.setSelected(true);
					iv_zanBody.setSelected(true);
					clickZan = true;
					addClickZan(tv_zanNumber, tv_zanButtonNumber);
				}else{
					iv_zanBotton.setSelected(false);
					iv_zanBody.setSelected(false);
					clickZan = false;
					deleteClickZan(tv_zanNumber, tv_zanButtonNumber);
				}
			}
			
		});
		
		v_givenReply.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 跳转到回复页面
				Intent intent = new Intent(InvitationDetailActivity.this,InvitationReplyActivity.class);
				startActivityForResult(intent, REQUEST_REPLY);
				
			}
		});
		
		
		//模拟数据
		
		views = new ArrayList<View>(); 
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		
		invitationItemAdapter = new InvitationItemAdapter();
		
		lv_reply.setAdapter(invitationItemAdapter);
		
	    lv_reply.setOnRefreshCallBack(new OnRefreshCallBack() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
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
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		
		invitationItemAdapter.notifyDataSetChanged();
		
		// TODO 上拉加载连接网络获取数据后，还原
		lv_reply.completePull();
		isRefresh = true;
	}
	
	
	private void downToRefreshData(){
		//连接网络加载数据，这里就模拟一下数据
		views.clear();
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		
		if(invitationItemAdapter!= null){
			invitationItemAdapter.notifyDataSetChanged();
		}
		
		//完成加载后控件复原
		lv_reply.completeRefresh();
		isRefresh = true;
	}
	
	
	
	
	
	
	
	//分享弹窗
	private void showSharePopupWindow(View v) {
		// TODO 弹出分享弹窗
		View convertView = LayoutInflater.from(InvitationDetailActivity.this)
				.inflate(R.layout.club_share_more_popupwindow, null);
		
		PopupWindow popuWindow = new PopupWindow
				(convertView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,true);
		
		popuWindow.setTouchable(true);
		popuWindow.setTouchInterceptor(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO 返回true无法点击popular中的数据
				return false;
			}
		});
		
		popuWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.club_choiceness_shape_type));
		//设置窗口显示的动画效果
		popuWindow.setAnimationStyle(R.style.PopupAnimation);
		// 设置好参数之后再show
		popuWindow.showAtLocation(v, Gravity.BOTTOM,0,0);
		popuWindow.update(); 
		
	}
	
	
	
	
	
	/**
	 * 给两个记录点赞数的TextView增加点赞
	 * @param tv1  滚动体中的点赞数   tv_zanNumber
	 * @param tv2 底部的点赞数量     tv_zanButtonNumber
	 */
	public void addClickZan(TextView tv1,TextView tv2){
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(tv1.getText().toString());
		int replyNumber = Integer.parseInt(m.replaceAll("").trim());
		
		String newStr = replyNumber+1+"人赞过";
		String newStr2 = replyNumber+1+"个点赞";
		
		tv1.setText(newStr);
		tv2.setText(newStr2);
		
	}
	
	public void deleteClickZan(TextView tv1,TextView tv2){
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(tv1.getText().toString());
		int replyNumber = Integer.parseInt(m.replaceAll("").trim());
		
		String newStr = replyNumber-1+"人赞过";
		String newStr2 = replyNumber-1+"个点赞";
		
		tv1.setText(newStr);
		tv2.setText(newStr2);
	}
	
	
	
	/**
	 * 等待回复结束
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == REQUEST_REPLY && resultCode == RESULT_OK){
			//对回复数量加1
			//发布成功后给回复数量加1
			
			//使用正则表达式获取其中的数字
			String regEx="[^0-9]";   
			Pattern p = Pattern.compile(regEx);   
			Matcher m = p.matcher(tv_replyNumber.getText().toString());
			int replyNumber = Integer.parseInt(m.replaceAll("").trim());
			
			String newStr = replyNumber+1+"则回复";
			
			tv_replyNumber.setText(newStr);
		}
	
	}
	
	
     
	
    private class ViewHolder{
    	private ImageView iv_LOGO;
    	private TextView reply_name;
    	private ImageView isMaster;
    	private TextView reply_time;
    	private TextView floorNumber;
    	private TextView content;
    	private ImageView iv_picl;
    }
	class InvitationItemAdapter extends BaseAdapter{

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
			ViewHolder vh = null;
			if(convertView == null){
				vh = new ViewHolder();
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_club_invitation_detail_reply, null);
				vh.iv_LOGO = (ImageView) findViewById(R.id.club_ivitationdetail_item_reply_userlogo);
				vh.reply_name = (TextView) findViewById(R.id.club_ivitationdetail_item_reply_username);
				vh.isMaster =  (ImageView) findViewById(R.id.club_ivitationdetail_item_reply_ismaster);
				vh.floorNumber = (TextView) findViewById(R.id.club_ivitationdetail_item_reply_floornumber);
				vh.content = (TextView) findViewById(R.id.club_ivitationdetail_item_reply_content);
				vh.iv_picl = (ImageView) findViewById(R.id.club_ivitationdetail_item_reply_iv_pic);
				vh.reply_time = (TextView) findViewById(R.id.club_ivitationdetail_item_reply_tv_time);
				convertView.setTag(vh);
			}else{
				vh = (ViewHolder) convertView.getTag();
			}
			
			//设置楼数的自增
			/*String regEx="[^0-9]";   
			Pattern p = Pattern.compile(regEx);   
			Matcher m = p.matcher(vh.floorNumber.getText().toString());
			int replyNumber = Integer.parseInt(m.replaceAll("").trim());
			replyNumber = position;
			vh.floorNumber.setText(replyNumber+"楼");*/
			
			return convertView;
		}
		
	}
}
