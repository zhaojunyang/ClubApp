package com.geminno.club.entity;

import java.util.ArrayList;

public class MyClubsEntity {

	private int err_code;
	private String err_msg;
	private String page;
	private ArrayList<MyClubsInfo> data;
	public int getErr_code() {
		return err_code;
	}
	public void setErr_code(int err_code) {
		this.err_code = err_code;
	}
	public String getErr_msg() {
		return err_msg;
	}
	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public ArrayList<MyClubsInfo> getData() {
		return data;
	}
	public void setData(ArrayList<MyClubsInfo> data) {
		this.data = data;
	}
	public MyClubsEntity(int err_code, String err_msg, String page,
			ArrayList<MyClubsInfo> data) {
		super();
		this.err_code = err_code;
		this.err_msg = err_msg;
		this.page = page;
		this.data = data;
	}
	public MyClubsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MyClubsEntity [err_code=" + err_code + ", err_msg=" + err_msg
				+ ", page=" + page + ", data=" + data + "]";
	}
	
	
}
