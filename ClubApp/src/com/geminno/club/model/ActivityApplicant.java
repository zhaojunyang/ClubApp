package com.geminno.club.model;

/*
 * 活动与报名者关系表
 * */
public class ActivityApplicant {
	private int aa_id;//主键
	private Activity activity;//获得活动id
	private User  user;//用户id
	private ActivityApplicant(){}
	private ActivityApplicant( Activity activity, User user) {
		super();
		this.activity = activity;
		this.user = user;
	}
	private ActivityApplicant(int aa_id, Activity activity, User user) {
		super();
		this.aa_id = aa_id;
		this.activity = activity;
		this.user = user;
	}

	public int getAa_id() {
		return aa_id;
	}

	public void setAa_id(int aa_id) {
		this.aa_id = aa_id;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ActivityApplicant [aa_id=" + aa_id + ", activity=" + activity
				+ ", user=" + user + "]";
	}
	
}
