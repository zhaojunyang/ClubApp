package com.geminno.club.model;

public class CodeEntity {

	private int err_code ;//返回的错误码，“0”表示正常
	private String err_msg ;//错误信息说明
	private String page ;// 页码, -1是没有下一页
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
	public CodeEntity(int err_code, String err_msg, String page) {
		super();
		this.err_code = err_code;
		this.err_msg = err_msg;
		this.page = page;
	}
	public CodeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CodeEntity [err_code=" + err_code + ", err_msg=" + err_msg
				+ ", page=" + page + "]";
	}
	
}
