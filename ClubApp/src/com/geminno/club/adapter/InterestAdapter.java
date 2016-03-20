package com.geminno.club.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.clubapp.R;
import com.geminno.club.activity.ActivityDetailsActivity;
import com.geminno.club.activity.InvitationDetailActivity;

import com.geminno.club.model.Activity;
import com.geminno.club.model.Invitation;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.media.RatingCompat.StarStyle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InterestAdapter extends BaseAdapter {
	List<Object> lists;
	Context context;
	LayoutInflater inflater;
	final int TYPE_COUNT = 2;
	final int TYPE_ONE = 0;
	final int TYPE_TWO = 1;
	List<String> positions = new ArrayList<String>();// 存放position集合
	// List<String> positions_attention = new ArrayList<String>();//
	// 存放position集合

	public InterestAdapter(List<Object> lists, Context context) {
		super();
		this.lists = lists;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	// 判断为哪一种类型
	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		if (lists.get(position) instanceof Activity) {
			return TYPE_ONE;

		} else if (lists.get(position) instanceof Invitation) {
			return TYPE_TWO;

		}
		return 0;
	}

	// 布局数量
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return TYPE_COUNT;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder activity = null;
		ViewHolder invitation = null;
		int type = getItemViewType(position);

		if (convertView == null) {
			switch (type) {
			case TYPE_ONE:
				// 活动
				// 设置item
				convertView = inflater.inflate(R.layout.interest_listview_activity_item, null);
				activity = new ViewHolder();
				activity.user_logo = (ImageView) convertView.findViewById(R.id.interest_activity_person_logo);
				activity.user_name = (TextView) convertView.findViewById(R.id.interest_activity_person_name);
				activity.title = (TextView) convertView.findViewById(R.id.activity_title);
				activity.images = (LinearLayout) convertView.findViewById(R.id.layout_activity_interest_images);
				activity.number = (TextView) convertView.findViewById(R.id.activity_max_member);
				activity.like = (ImageButton) convertView.findViewById(R.id.activity_unlike_like);
				activity.like_count = (TextView) convertView.findViewById(R.id.activity_like_count);
				activity.attention = (Button) convertView.findViewById(R.id.interest_activity_person_attention);
				convertView.setTag(activity);
				
				break;
			
			case TYPE_TWO:
				// 帖子
				// 设置item
				convertView = inflater.inflate(R.layout.interest_listview_invitation_item, null);
				invitation = new ViewHolder();
				invitation.user_logo = (ImageView) convertView.findViewById(R.id.interest_invitation_person_logo);
				invitation.user_name = (TextView) convertView.findViewById(R.id.interest_invitation_person_name);
				invitation.title = (TextView) convertView.findViewById(R.id.invitation_conent);
				invitation.images = (LinearLayout) convertView.findViewById(R.id.layout_invitation_interest_images);
				invitation.number = (TextView) convertView.findViewById(R.id.invitation_reply_count);
				invitation.like = (ImageButton) convertView.findViewById(R.id.invitation_unlike_like);
				invitation.like_count = (TextView) convertView.findViewById(R.id.invitation_like_count);
				invitation.attention = (Button) convertView.findViewById(R.id.interest_invitation_person_attention);
				convertView.setTag(invitation);

				break;

			default:
				break;
			}

		} else {
			switch (type) {
			case TYPE_ONE:

				activity = (ViewHolder) convertView.getTag();
				break;
			case TYPE_TWO:
				invitation = (ViewHolder) convertView.getTag();
				break;

			default:
				break;
			}

		}
		// //imageView设置tag
		// 点赞

		switch (type) {
		case TYPE_ONE:
			activity.like.setTag(position);
			activity.attention.setTag(position);

			if (positions.contains(position + "")) {

				activity.like.setImageResource(R.drawable.dynamic_like);

			} else {
				activity.like.setImageResource(R.drawable.daynamic_unlike);

			}

			break;
		case TYPE_TWO:
			invitation.like.setTag(position);
			// invitation.attention.setTag(position);

			if (positions.contains(position + "")) {

				invitation.like.setImageResource(R.drawable.dynamic_like);

			} else {
				invitation.like.setImageResource(R.drawable.daynamic_unlike);

			}

			// if(positions_attention.contains(position+"")){
			// invitation.attention.setImageResource(R.drawable.friends_attention);
			//
			// }
			// else{
			// invitation.attention.setImageResource(R.drawable.friends_no_attention);
			//
			// }
			break;

		default:
			break;
		}

		// 设置资源
		switch (type) {
		case TYPE_ONE:
			// 活动
			// 用户头像
			Drawable user_logo1 = Drawable.createFromPath(((Activity) lists.get(position)).getUser().getPhoto_URL());
			activity.user_logo.setImageDrawable(user_logo1);
			// 点击查看个人信息
			activity.user_logo.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(context, InvitationDetailActivity.class);
					intent.putExtra("uid", "uid");
					context.startActivity(intent);
					
					
					
					Toast.makeText(context, "跳转到用户对外个人信息", 1).show();
				}
			});
			// 用户昵称
			activity.user_name.setText(((Activity) lists.get(position)).getUser().getNick_name());
			// 获取活动标题
			activity.title.setText(((Activity) lists.get(position)).getTitle());

			Drawable drawable = Drawable.createFromPath(((Activity) lists.get(position)).getPhoto_url());
			activity.images.setBackground(drawable);
			// 跳转到活动详情
			activity.images.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(context, ActivityDetailsActivity.class);
					intent.putExtra("id", "id");
					context.startActivity(intent);
					
					
					Toast.makeText(context, "跳转到活动详情页面", 1).show();
				}
			});
			// 获取人数名额
			activity.number.setText("限" + ((Activity) lists.get(position)).getMax_member() + "" + "人");
			// 获取点赞人数
			activity.like_count.setText(((Activity) lists.get(position)).getLike_count() + "");
			// 点赞
			activity.like.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ImageButton v1 = (ImageButton) v;

					if (positions.contains(position + "")) {
						positions.remove(position + "");
						((ImageView) v).setImageResource(R.drawable.daynamic_unlike);
						return;
					}

					((ImageView) v).setImageResource(R.drawable.dynamic_like);
					positions.add(position + "");

				}
			});
			// 报名:点击进入活动详情
			activity.attention.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(context, InvitationDetailActivity.class);
					intent.putExtra("id", "id");
					context.startActivity(intent);
					Toast.makeText(context, "跳转到活动详情页面", 1).show();
				}
			});
			break;
		case TYPE_TWO:
			// 帖子
			// 用户头像
			Drawable user_logo2 = Drawable.createFromPath(((Invitation) lists.get(position)).getUser().getPhoto_URL());
			invitation.user_logo.setImageDrawable(user_logo2);
			// 点击查看个人信息
			invitation.user_logo.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(context, InvitationDetailActivity.class);
					
					intent.putExtra("uid", "uid");
					context.startActivity(intent);
					Toast.makeText(context, "跳转到用户对外个人信息2", 1).show();
				}
			});
			// 用户昵称
			invitation.user_name.setText(((Invitation) lists.get(position)).getUser().getNick_name());
			// 帖子标题
			invitation.title.setText(((Invitation) lists.get(position)).getTitle());

			Drawable drawable2 = Drawable.createFromPath(((Invitation) lists.get(position)).getPhoto_URL());
			invitation.images.setBackground(drawable2);
			// 点击进入帖子详情
			invitation.images.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(context, ActivityDetailsActivity.class);
					intent.putExtra("id", "id");
					context.startActivity(intent);
					
					Toast.makeText(context, "跳转到活动详情页面", 1).show();
				}
			});
			// 帖子回复人数
			invitation.number.setText(((Invitation) lists.get(position)).getReply_count() + "" + "人回复");
			// 点赞人数
			invitation.like_count.setText(((Invitation) lists.get(position)).getLike_count() + "");

			invitation.like.setOnClickListener(new OnClickListener() {
				// 点赞
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ImageButton v1 = (ImageButton) v;

					if (positions.contains(position + "")) {
						positions.remove(position + "");
						((ImageView) v).setImageResource(R.drawable.daynamic_unlike);
						return;
					}

					((ImageView) v).setImageResource(R.drawable.dynamic_like);
					positions.add(position + "");

				}
			});

			// 查看
			invitation.attention.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(context, InvitationDetailActivity.class);
					intent.putExtra("uid", "uid");
					context.startActivity(intent);
					
					
					Toast.makeText(context, "跳转到帖子详情", 1).show();
				}
			});
			break;

		default:
			break;
		}

		return convertView;
	}

	// 活动布局控件
	public class ViewHolder {
		ImageView user_logo;
		TextView user_name;
		TextView title;
		LinearLayout images;
		TextView number;
		ImageButton like;
		TextView like_count;
		Button attention;

	}
	// 帖子布局控件
	// public class InvitationHolder{
	// ImageView user_logo;
	// TextView user_name;
	// TextView Invitation_title;
	// LinearLayout Invitation_images;
	// TextView reply_count;
	// ImageButton Invitation_like;
	// TextView Invitation_like_count;
	//
	//
	//
	// }

}
