package com.geminno.club.activity;

import com.example.clubapp.R;
import com.geminno.club.model.Club;
import com.geminno.club.util.MUtils;
import com.geminno.club.view.RoundImageView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ClubDetailsActivity extends Activity {
	@ViewInject(R.id.club_details_back)
	private ImageView iv_back;//返回按钮
	@ViewInject(R.id.club_details_goto_memberslist)
	private ImageView iv_toMembersList;//进入成员列表
	@ViewInject(R.id.club_details_clublogo)
	private RoundImageView iv_LOGO;//俱乐部LOGO
	@ViewInject(R.id.club_details_club_name)
	private TextView club_name;//俱乐部的名字
	@ViewInject(R.id.club_details_clublocation)
	private TextView club_location;//俱乐部的位置
	@ViewInject(R.id.club_details_club_type)
	private TextView club_type;//俱乐部的类型
	@ViewInject(R.id.club_details_club_make_time)
	private TextView club_dateline;//俱乐部的创建时间
	@ViewInject(R.id.club_details_clubbuilder_name)
	private TextView club_master_name;//创建者的名字
	@ViewInject(R.id.club_details_clubbuilder_logo)
	private ImageView master_LOGO;//创建者的LOGO
	@ViewInject(R.id.club_details_club_brief)
	private TextView club_brief;//创建者的简介
	private Context mContext;
	private Club club;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_club_details);
		
		ViewUtils.inject(this);
		mContext = this;
		//寻找界面上相应的控件
		
		iv_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 推出俱乐部简介
				finish();
			}
		});
		
		club = (Club) getIntent().getSerializableExtra("Club"); 
		
		MUtils.useVolleyForImage(mContext, iv_LOGO, club.getClub_LOGO());
		
		club_name.setText(club.getClub_name());
		club_location.setText(club.getClub_location());
		club_type.setText(club.getClub_type());
		club_dateline.setText(club.getClub_Dateline());
		club_master_name.setText(club.getFounderName());
		MUtils.useVolleyForImage(mContext, master_LOGO, club.getFounderUrl());
		club_brief.setText(club.getClub_brief());
		
		iv_toMembersList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO 跳转到成员列表页面
				Intent intent = new Intent(ClubDetailsActivity.this,ClubMembersActivity.class);
				
				intent.putExtra("club_id_Members", club.getId());
				
				startActivity(intent);
			}
		});
		
		
		
		
		
		
		
	}

	
}
