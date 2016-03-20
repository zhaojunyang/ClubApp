package com.geminno.club.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.clubapp.R;
import com.geminno.club.adapter.InterestAdapter;
import com.geminno.club.model.Invitation;
import com.geminno.club.model.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TestSActivity extends Activity implements OnItemClickListener {
	ListView lv ;
	List<Object> lists =  new ArrayList<Object>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		lv = (ListView) findViewById(R.id.test_listView);
		User user  =  new User(1, 100, "小黑", "123", "xiaohei", null, null, "男", "苏州", "zzz");
		lists.add(new Invitation(user, "帖子3", null, 32, 33));
		lists.add(new Invitation(user, "帖子1", null, 10, 11));
		lists.add(new Invitation(user, "帖子2", null, 21, 22));
		lists.add(new Invitation(user, "帖子4", null, 43, 44));
		lists.add(new com.geminno.club.model.Activity(user, "活动1", 10, null, 12));
		lists.add(new com.geminno.club.model.Activity(user, "活动2", 20, null, 12));
		lists.add(new com.geminno.club.model.Activity(user, "活动3", 30, null, 12));
		lists.add(new com.geminno.club.model.Activity(user, "活动4", 40, null, 12));

		
		lv.setAdapter(new InterestAdapter(lists, this));
		//lv.setOnItemClickListener(this);
		
		
		
		
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
	}

	
}
