package com.geminno.club.activity;

import com.example.clubapp.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 
 * @author Administrator
 *石然  2016-3-15
 */
public class SettingActivity extends Activity implements OnClickListener{

	@ViewInject(R.id.setting_back)
	ImageView backImageView;
	
	@ViewInject(R.id.relativelayout1)
	RelativeLayout currentLayout;
	
	@ViewInject(R.id.RelativeLayout01)
	RelativeLayout clearcacheLayout;
	
	@ViewInject(R.id.layout_exit)
	RelativeLayout exit;
	
	@ViewInject(R.id.count_cache)
	TextView countcacheTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		//获取所有控件
		ViewUtils.inject(this);
		//设置监听事件
		backImageView.setOnClickListener(this);
		currentLayout.setOnClickListener(this);
		clearcacheLayout.setOnClickListener(this);
		exit.setOnClickListener(this);
		backImageView.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.setting_back:
			finish();
			break;
		case R.id.relativelayout1:
			new AlertDialog.Builder(this).setMessage("版本1.0")
			.setPositiveButton("ok", new DialogInterface.OnClickListener() {								
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					
				}
			}).show();
			break;
		case R.id.RelativeLayout01:
			
			break;
		case R.id.layout_exit:
			
			break;

		
		}
	}

	

}
