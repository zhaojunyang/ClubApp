package com.geminno.club.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.clubapp.R;
import com.geminno.club.fragment.BaseFragment;
import com.geminno.club.fragment.FindPageFragment;
import com.geminno.club.fragment.InterestPageFragment;
import com.geminno.club.fragment.MessagePageFragment;
import com.geminno.club.fragment.PersonPageFragment;
import android.app.ActionBar;
import android.os.Bundle;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

public class MainActivity extends FragmentActivity {
	InterestPageFragment interestPageFragment;
	FindPageFragment findPageFragment;
	MessagePageFragment messagePageFragment;
	PersonPageFragment personPageFragment;
	//定义List集合存放fragment，方便操作
	List<BaseFragment> fragments;
	//定义对应按钮，用来控制状态改变
	View re_interest;
	View re_find;
	View re_message;
	View re_person;
	//创建数据存放View，方便操作
	List<View> views;
	//当前显示的fragment下标
	int currentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//除去标题栏
        setContentView(R.layout.activity_main);
        //fragment初始化
        interestPageFragment=new InterestPageFragment();
        findPageFragment=new FindPageFragment();
        messagePageFragment=new MessagePageFragment();
        personPageFragment=new PersonPageFragment();
        //初始化List集合
        fragments=new ArrayList<BaseFragment>();
        fragments.add(interestPageFragment);
        fragments.add(findPageFragment);
        fragments.add(messagePageFragment);
        fragments.add(personPageFragment);
        //初始化View(主菜单)
        re_interest=findViewById(R.id.re_interest);
        re_find=findViewById(R.id.re_find);
        re_message=findViewById(R.id.re_message);
        re_person=findViewById(R.id.re_person);
        //初始化List<View>
        views=new ArrayList<View>();
        views.add(re_interest);
        views.add(re_find);
        views.add(re_message);
        views.add(re_person);
      //初始化fragment,默认显示第一个
      		getFragmentManager().beginTransaction()
      		.add(R.id.fragment_container, fragments.get(0)).commit();		
    }
    //主菜单点击事件
    public void onTabClicked(View v){
    	//即将显示(用户点击)的fragment下标
    	int nextIndex = 0;
    	switch (v.getId()) {
		case R.id.re_interest:
			/*
			 * 1.没有加过
			 * 2.加过 &当前显示其他 show 隐藏其他
			 * 3加过 &当前显示  不做任何操作
			 * */
			nextIndex=0;
			break;
		case R.id.re_find:
			nextIndex=1;
			break;
		case R.id.re_message:
			nextIndex=2;
			break;
		case R.id.re_person:
			nextIndex=3;
			break;
		}
    	//当前选中按钮取消选中
		views.get(currentIndex).setSelected(false);
		//切换fragment
		fragmentChange(nextIndex);
		//对应按钮的选中状态设置为true
		views.get(nextIndex).setSelected(true);
    }
	/*
	 * 隐藏currentIndex对应的fragment
	 * 显示nextIndex对应的fragment
	 * */
	private void fragmentChange(int nextIndex) {
		//判断点击的是否是当前显示的:不是
		if(currentIndex!=nextIndex){
			FragmentTransaction transaction=getFragmentManager().beginTransaction();
			transaction.hide(fragments.get(currentIndex));
			//判断是否添加过
			if(!fragments.get(nextIndex).isAdded()){
				transaction.add(R.id.fragment_container, fragments.get(nextIndex));
			}
			transaction.show(fragments.get(nextIndex)).commit();
		}
		//改变currentIndex值，当前显示fragment
		currentIndex=nextIndex;
		
	}
}
