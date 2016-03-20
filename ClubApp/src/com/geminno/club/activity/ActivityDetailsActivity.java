package com.geminno.club.activity;

import com.example.clubapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityDetailsActivity extends Activity {
	ImageView iv_likeButton;//点赞图标
	TextView tv_likeButton;
	ImageButton ib_activityEnter;//活动报名按钮
	int likeNum;//点赞数
	Boolean likeFlag=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ��������  
		setContentView(R.layout.activity_activity_details);
		iv_likeButton=(ImageView) findViewById(R.id.iv_likeButton);
		tv_likeButton=(TextView) findViewById(R.id.tv_likeButton);
		ib_activityEnter=(ImageButton) findViewById(R.id.ib_activityEnter);
		iv_likeButton.setSelected(true);
		//点赞数
		likeNum=Integer.parseInt(tv_likeButton.getText().toString().split("点")[0].toString());
		iv_likeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!likeFlag){
					iv_likeButton.setSelected(false);
					likeFlag=true;
					tv_likeButton.setText(++likeNum+"点赞");
				}else{
					iv_likeButton.setSelected(true);
					likeFlag=false;
					tv_likeButton.setText(--likeNum+"点赞");
				}
				
			}
			
		});
		ib_activityEnter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(ActivityDetailsActivity.this, EnterActivityActivity.class);
				startActivity(intent);
			}
		});
	}
	//返回上页
		public void goBack(View v){
			Intent intent=new Intent();
			intent.setClass(ActivityDetailsActivity.this, 
					SameCityActivityActivity.class);
			startActivity(intent);
			
		}

	
}
