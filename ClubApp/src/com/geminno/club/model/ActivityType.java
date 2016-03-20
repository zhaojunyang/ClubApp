package com.geminno.club.model;
//活动类型表
public class ActivityType {
	private int  activity_type_id;//主键
	private  String activity_type_name;//活动类型名称
	private ActivityType(){}
	private ActivityType(int activity_type_id, String activity_type_name) {
		super();
		this.activity_type_id = activity_type_id;
		this.activity_type_name = activity_type_name;
	}
	public int getActivity_type_id() {
		return activity_type_id;
	}
	public void setActivity_type_id(int activity_type_id) {
		this.activity_type_id = activity_type_id;
	}
	@Override
	public String toString() {
		return "ActivityType [activity_type_id=" + activity_type_id
				+ ", activity_type_name=" + activity_type_name + "]";
	}
	
}
