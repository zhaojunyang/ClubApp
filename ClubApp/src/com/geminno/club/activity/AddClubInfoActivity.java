package com.geminno.club.activity;

import com.example.clubapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddClubInfoActivity extends Activity {

	//返回按钮
	private ImageView iv_back;
	//填写的城市
	private EditText et_content;
	//加入按钮
	private Button btn;
	//选择男女
	private ImageView club_apply_selman;
	private ImageView club_apply_selwoman;
	//用户判断选择男女的boolean类型
	private boolean hasSex = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_club_info);
		
		//寻找相应的控件
		//返回
		iv_back = (ImageView) findViewById(R.id.club_apply_back);
		club_apply_selman = (ImageView) findViewById(R.id.club_apply_selman);
		club_apply_selwoman = (ImageView) findViewById(R.id.club_apply_selwoman);
		
		//输入的内容
		et_content = (EditText) findViewById(R.id.club_apply_et_city);
		//加入按钮
		
		btn = (Button) findViewById(R.id.club_apply_addbtn);
		
		iv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "点击返回", 0).show();
				finish();
				
			}
		});
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 发布信息
				if(TextUtils.isEmpty(et_content.getText().toString())){
					Toast.makeText(getApplicationContext(), "请输入城市", 0).show();
					return;
				}
				
				if(!hasSex){
					Toast.makeText(getApplicationContext(), "请选择性别", 0).show();
					return;
				}
				
				//返回主页面
				//可在Intent中添加需要返回的数据
				//Intent intent = new Intent();
				setResult(RESULT_OK);
				finish();
				
			}
		});
		/**
		 * 用来判断是否选择男女
		 */
		club_apply_selman.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(hasSex){
					if(!club_apply_selman.isSelected()){
						club_apply_selman.setSelected(true);
						club_apply_selwoman.setSelected(false);
					}
				}else{
					club_apply_selman.setSelected(true);
					hasSex = true;
				}
			}
		});
		
		club_apply_selwoman.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 如果没有选择，则直接选择，如果选择过了则将另一个设置为非选择
				if(hasSex){
					if(!club_apply_selwoman.isSelected()){
						club_apply_selwoman.setSelected(true);
						club_apply_selman.setSelected(false);
					}
				}else{
					club_apply_selwoman.setSelected(true);
					hasSex = true;
				}
			}
			
		});
		
	
	}

	
}
