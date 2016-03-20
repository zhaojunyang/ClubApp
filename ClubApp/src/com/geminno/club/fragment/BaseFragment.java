package com.geminno.club.fragment;

import android.app.Fragment;
import android.os.Bundle;

/*
 * BaseFragment
 * 初始化view：initView()
 * 控件点击事件:initEvent()
 * 数据准备:initData()
 */

public abstract class BaseFragment extends Fragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		initView();
		initEvent();
		initData();
		
	}
	public abstract void  initView();
	public abstract void  initEvent();
	public abstract void initData();
	
	
}
