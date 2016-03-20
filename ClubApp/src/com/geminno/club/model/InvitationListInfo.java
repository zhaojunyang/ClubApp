package com.geminno.club.model;

import java.util.List;

public class InvitationListInfo {
	private int err_code;
	private String err_msg;
	private String page;
	private List<InvitationContent> data;
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
	public List<InvitationContent> getData() {
		return data;
	}
	public void setData(List<InvitationContent> data) {
		this.data = data;
	}
	public InvitationListInfo(int err_code, String err_msg, String page, List<InvitationContent> data) {
		super();
		this.err_code = err_code;
		this.err_msg = err_msg;
		this.page = page;
		this.data = data;
	}
	public InvitationListInfo() {
		super();
	}
	@Override
	public String toString() {
		return "InvitationListInfo [err_code=" + err_code + ", err_msg=" + err_msg + ", page=" + page + ", data=" + data
				+ "]";
	}
	
	
	
}
