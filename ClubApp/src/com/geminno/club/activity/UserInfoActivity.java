package com.geminno.club.activity;

import com.example.clubapp.R;
import com.lidroid.xutils.BitmapUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 
 * @author Administrator
 *石然 2016-3-15
 *作为“我的”页面向“个人信息”页面跳转用
 */
public class UserInfoActivity extends Activity implements OnClickListener,OnItemClickListener{

	ImageView backImageView;
	TextView saveTextView;
	ImageView headerImageView;//头像
	RelativeLayout usernameLayout;
	EditText et_name;//用户名
	EditText et_sign;//签名
	
	public static final int USERINFOACTIVITY=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		backImageView = (ImageView) findViewById(R.id.userinfo_back);
		saveTextView = (TextView) findViewById(R.id.userinfo_fragment_tv_save);
		headerImageView = (ImageView) findViewById(R.id.iv_header_icon);
		usernameLayout = (RelativeLayout) findViewById(R.id.layout_username);
		et_name = (EditText) findViewById(R.id.et_username);
		et_sign = (EditText) findViewById(R.id.et_uerinfo_sign);
		
		Intent intent = getIntent();
		String image_headerString = intent.getStringExtra("image_header");
		//设置头像
		BitmapUtils bitmap = new BitmapUtils(this);
		bitmap.display(headerImageView, image_headerString);
		//设置用户名
		String username = intent.getStringExtra("username");
		et_name.setText(username);
		//设置签名
		String sign = intent.getStringExtra("usersign");
		et_sign.setText(sign);
		
		//绑定监听时间
		backImageView.setOnClickListener(this);
		saveTextView.setOnClickListener(this);
		headerImageView.setOnClickListener(this);
		usernameLayout.setOnClickListener(this);
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		//点击返回按钮
		case R.id.userinfo_back:
			/**
			 * 当点击返回的时候，直接销毁这个activity
			 */
			finish();
			break;
		//点击“保存”
		case R.id.userinfo_fragment_tv_save:
			break;
		//点击头像
		case R.id.iv_header_icon:
			break;
			
		}
	}

	
}
