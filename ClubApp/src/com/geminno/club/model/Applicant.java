package com.geminno.club.model;

/*
 * 活动报名申请表
 * */
public class Applicant {
	private int app_id;//主键
	private Activity activity;//获得活动id
	private String app_name;//报名用户姓名
	private String phone_number;//报名用户手机号
	private int age;//报名用户年龄
	private String health_condition;//报名用户健康状况
	private String sex;//报名用户性别
	private String status;//报名状态   0未审核 1审核未通过 2审核通过
	
	private Applicant(){}

	private Applicant( Activity activity, String app_name,
			String phone_number, int age, String health_condition, String sex,
			String status) {
		super();
		this.activity = activity;
		this.app_name = app_name;
		this.phone_number = phone_number;
		this.age = age;
		this.health_condition = health_condition;
		this.sex = sex;
		this.status = status;
	}
	private Applicant(int app_id, Activity activity, String app_name,
			String phone_number, int age, String health_condition, String sex,
			String status) {
		super();
		this.app_id = app_id;
		this.activity = activity;
		this.app_name = app_name;
		this.phone_number = phone_number;
		this.age = age;
		this.health_condition = health_condition;
		this.sex = sex;
		this.status = status;
	}

	public int getApp_id() {
		return app_id;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHealth_condition() {
		return health_condition;
	}

	public void setHealth_condition(String health_condition) {
		this.health_condition = health_condition;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Applicant [app_id=" + app_id + ", activity=" + activity
				+ ", app_name=" + app_name + ", phone_number=" + phone_number
				+ ", age=" + age + ", health_condition=" + health_condition
				+ ", sex=" + sex + ", status=" + status + "]";
	}
	
	
}
