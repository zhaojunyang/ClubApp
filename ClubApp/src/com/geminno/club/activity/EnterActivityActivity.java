package com.geminno.club.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.clubapp.R;
import com.geminno.club.adapter.CommonBaseAdapter;
import com.geminno.club.model.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class EnterActivityActivity extends Activity {
	LinearLayout ll_activityEnterUser;
	List<User> users=new ArrayList<User>();
	int enterNumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_activity);
		ll_activityEnterUser=(LinearLayout) findViewById(R.id.ll_activityEnterUser);
		
	}

	//添加活动表名人
	public boolean addActivityEnter(View v){
		if(enterNumber>=4){
			Toast.makeText(EnterActivityActivity.this, "同一个账号最多同时报4人", Toast.LENGTH_SHORT).show();
			return false;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("添加报名人");
		//解析添加报名人xml文件
		View view=getLayoutInflater().inflate(R.layout.activity_enter_item_register, null);
		final EditText et_activityEnterName=(EditText) view.findViewById(R.id.et_activityEnterName);
		final EditText et_activityEnterIdCard=(EditText) view.findViewById(R.id.et_activityEnterIdCard);
		final EditText et_activityEnterPhoneNumber=(EditText) view.findViewById(R.id.et_activityEnterPhoneNumber);
		builder.setView(view);
		builder.setCancelable(false);
		builder.setPositiveButton("确认", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				//创建新增报名用户
				create(et_activityEnterName.getText(),et_activityEnterIdCard.getText(),
						et_activityEnterPhoneNumber.getText());
				dialog.dismiss();
			}

			
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				dialog.dismiss();
			}
		});
		builder.show();
		return true;
	}
	//创建新的报名人
	public  void create(Editable enterName, Editable EnterIdCard, Editable enterPhoneNumber) {
		
		//解析布局文件
		View enterItem=getLayoutInflater().inflate(R.layout.activity_enter_item, null);
		TextView tv_enterName=(TextView) enterItem.findViewById(R.id.tv_enterName);
		TextView tv_activityEnterNo=(TextView) enterItem.findViewById(R.id.tv_activityEnterNo);
		TextView tv_enterIdCard=(TextView) enterItem.findViewById(R.id.tv_enterIdCard);
		TextView tv_enterPhone=(TextView) enterItem.findViewById(R.id.tv_enterPhone);
		//ImageView  tv_enter_delete=(ImageView) enterItem.findViewById(R.id.tv_enter_delete);
		tv_activityEnterNo.setText("报名人"+(enterNumber+1));
		tv_enterName.setText(enterName);
		tv_enterIdCard.setText(EnterIdCard);
		tv_enterPhone.setText(enterPhoneNumber);
		ll_activityEnterUser.addView(enterItem);
		enterNumber++;
		
	}
	//删除报名人
	public void deleteActivityEnter(View v){
		ll_activityEnterUser.removeView((LinearLayout) v.getParent().getParent().getParent());
		Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
		enterNumber--;
	}
	//提交报名
	public void submitActicityEnter(View v){
		Intent intent=new Intent();
		intent.setClass(EnterActivityActivity.this, ActivityEnterSuccessActivity.class);
		startActivity(intent);
	}
	//返回上页
	public void goBack(View v){
		Intent intent=new Intent();
		intent.setClass(EnterActivityActivity.this, 
				ActivityDetailsActivity.class);
		startActivity(intent);
		
	}
	
}
