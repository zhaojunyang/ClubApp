package com.geminno.club.entity;

import java.util.ArrayList;

public class MyFocusedFriendsEntity {

	private String err_msg;
	private String err_code;
	private String page;
	ArrayList<MyFocusedFriendInfo> data;
	public String getErr_msg() {
		return err_msg;
	}
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public ArrayList<MyFocusedFriendInfo> getData() {
		return data;
	}
	public void setData(ArrayList<MyFocusedFriendInfo> data) {
		this.data = data;
	}
	public MyFocusedFriendsEntity(String err_msg, String err_code, String page,
			ArrayList<MyFocusedFriendInfo> data) {
		super();
		this.err_msg = err_msg;
		this.err_code = err_code;
		this.page = page;
		this.data = data;
	}
	public MyFocusedFriendsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MyFocusedFriendsEntity [err_msg=" + err_msg + ", err_code="
				+ err_code + ", page=" + page + ", data=" + data + "]";
	}
	
}
