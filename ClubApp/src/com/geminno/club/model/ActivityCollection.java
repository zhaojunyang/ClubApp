package com.geminno.club.model;

/*
 * 活动与收藏人关系表
 * */
public class ActivityCollection {
	private int aco_id;//主键
	private User user;//获得收藏者id
	private Activity activity;//获得收藏活动id
	private ActivityCollection(){}
	private ActivityCollection( User user, Activity activity) {
		super();
		this.user = user;
		this.activity = activity;
	}
	private ActivityCollection(int aco_id, User user, Activity activity) {
		super();
		this.aco_id = aco_id;
		this.user = user;
		this.activity = activity;
	}

	public int getAco_id() {
		return aco_id;
	}

	public void setAco_id(int aco_id) {
		this.aco_id = aco_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@Override
	public String toString() {
		return "ActivityCollection [aco_id=" + aco_id + ", user=" + user
				+ ", activity=" + activity + "]";
	}
	
}
