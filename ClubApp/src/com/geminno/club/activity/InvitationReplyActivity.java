package com.geminno.club.activity;

import com.example.clubapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InvitationReplyActivity extends Activity {

	private ImageView iv_back;
	private TextView tv_publish;
	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invitation_reply);
		//在界面上找控件
		
		iv_back = (ImageView) findViewById(R.id.club_reply_back);
		tv_publish = (TextView) findViewById(R.id.club_reply_reply);
		et = (EditText) findViewById(R.id.club_reply_et_content);
		
		iv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		tv_publish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(TextUtils.isEmpty(et.getText().toString())){
					Toast.makeText(getApplicationContext(), "请输入回复内容", 0).show();
					return;
				}
				Toast.makeText(getApplicationContext(), "发布成功："+et.getText().toString(), 0).show();
				Intent intent = new Intent();
				setResult(RESULT_OK);
				finish();
			}
		});
	}

	
}
