package com.geminno.club.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.clubapp.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class SameCityClubActivity extends Activity {
	
	//获取ActionBar对象
	ActionBar ab;
	ListView lv_samecity_club;
	List<View> views;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_same_city_club);
		
		ab = getActionBar();
		//设置显示“返回”菜单
		ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		ab.setCustomView(R.layout.samecity_club_menu);
		//可以通过ab.getCustomView().findViewById().setOn
		
		//实例化viewws
		views = new ArrayList<View>();
		
		//模拟同城俱乐部中ListVie的数据
		//View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_samecityclub,null);
		//View v2 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_samecityclub,null);
		//View v3 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_samecityclub,null);
		//View v4 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_samecityclub,null);
		
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		views.add(new View(getApplicationContext()));
		//views.add(v2);
		//views.add(v3);
		//views.add(v4);
		
		
		
	}
	
	private class SameCityClubAdapter extends BaseAdapter{

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
			View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_samecityclub,null);
			return v;
		}
		
	}
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.same_city_club, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
