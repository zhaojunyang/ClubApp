package com.geminno.club.activity;

import com.example.clubapp.R;
import com.example.clubapp.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ActivityEnterSuccessActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_enter_success);
	}

	public void goBack(View v){
		Intent intent=new Intent();
		intent.setClass(ActivityEnterSuccessActivity.this, 
				EnterActivityActivity.class);
		startActivity(intent);
		
	}


}
