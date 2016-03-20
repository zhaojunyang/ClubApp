package com.geminno.club.entity;

public class PeopleCenterEntity {

	private String err_code;
	private String err_msg;
	private String page;
	private PeopleCenterInfo data;
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
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
	public PeopleCenterInfo getData() {
		return data;
	}
	public void setData(PeopleCenterInfo data) {
		this.data = data;
	}
	public PeopleCenterEntity(String err_code, String err_msg, String page,
			PeopleCenterInfo data) {
		super();
		this.err_code = err_code;
		this.err_msg = err_msg;
		this.page = page;
		this.data = data;
	}
	public PeopleCenterEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PeopleCenterEntity [err_code=" + err_code + ", err_msg="
				+ err_msg + ", page=" + page + ", data=" + data + "]";
	}

	
}
