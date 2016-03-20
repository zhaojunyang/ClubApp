package com.geminno.club.entity;

public class BaseEntity {
	private String err_code;
	private String err_msg;
	private String page;
	
	public BaseEntity() {}
	public BaseEntity(String err_code, String err_msg, String page) {
		super();
		this.err_code = err_code;
		this.err_msg = err_msg;
		this.page = page;
	}

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

	@Override
	public String toString() {
		return "BaseEntity [err_code=" + err_code + ", err_msg=" + err_msg
				+ ", page=" + page + "]";
	}
	
}
