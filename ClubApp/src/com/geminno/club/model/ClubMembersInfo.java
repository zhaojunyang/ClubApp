package com.geminno.club.model;

import java.util.List;

public class ClubMembersInfo {
	
	private int err_code;
	private String err_msg;
	private String page;
	private List<ClubMembers> data;
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
	public List<ClubMembers> getData() {
		return data;
	}
	public void setData(List<ClubMembers> data) {
		this.data = data;
	}
	public ClubMembersInfo(int err_code, String err_msg, String page, List<ClubMembers> data) {
		super();
		this.err_code = err_code;
		this.err_msg = err_msg;
		this.page = page;
		this.data = data;
	}
	public ClubMembersInfo() {
		super();
	}
	@Override
	public String toString() {
		return "ClubMembersInfo [err_code=" + err_code + ", err_msg=" + err_msg + ", page=" + page + ", data=" + data
				+ "]";
	}
	
	
}
