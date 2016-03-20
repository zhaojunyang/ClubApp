package com.geminno.club.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.clubapp.R;



import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;;

public class MyrefreshListView extends ListView implements OnScrollListener {

	// 加载头部
	private View headview;
	private int headHight; //
	// 控件
	private ProgressBar propressbar_head;
	private TextView refresh_head;
	private TextView refresh_time;

	// 加载尾部
	private View footView;
	private int footHight; //
	// 控件
	// ProgressBar propressbar_foot;
	// TextView refresh_foot;

	// 起始位置
	private float startY; // 初始化y坐标
	private float moveY; // 移动的坐标
	private int headState; // 头部的状态

	private final int INIT = 0; // 初始状态
	private final int PREPAREREFERSH = 1; // 准备刷新
	private final int ISREFRESHING = 2; // 正在刷新

	boolean loading = false;
	OnRefreshCallBack onrefreshcallback;
	private int firstVisibleItem;

	public MyrefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if(isInEditMode()){
			return ;
		}
		initHead(context);
		initFoot(context);
		setOnScrollListener(this);

		//
	}

	public MyrefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if(isInEditMode()){
			return ;
		}
		initHead(context);
		initFoot(context);
		setOnScrollListener(this);
		//
	}

	public MyrefreshListView(Context context) {
		super(context);
		if(isInEditMode()){
			return ;
		}
		initHead(context);
		initFoot(context);
		setOnScrollListener(this);
		//
	}
	
	
	
	// 传接口的实现类
		public void setOnRefreshCallBack(OnRefreshCallBack onRefreshCallBack) {
			this.onrefreshcallback = onRefreshCallBack;
		}
		

	/*
	 * 加载头部
	 */
	public void initHead(Context context) {
		// 解析xml
		headview = LayoutInflater.from(context).inflate(R.layout.refresh_listview_head, null);
		// 添加头部
		addHeaderView(headview);
		// head的高度
		headview.measure(0,0);
		headHight = headview.getMeasuredHeight();
		// 隐藏头部
		headview.setPadding(0, -headHight, 0, 0);

		// 初始化头部控件
		propressbar_head = (ProgressBar) findViewById(R.id.progressBar_refresh_head);
		refresh_head = (TextView) findViewById(R.id.refresh_head);
		refresh_time = (TextView) findViewById(R.id.refresh_time);

	}

	/*
	 * 加载尾部
	 */
	public void initFoot(Context context) {
		// 解析xml
		footView = LayoutInflater.from(context).inflate(R.layout.refresh_listview_foot, null);
		// 添加尾部
		addFooterView(footView);
		// foot的高度
		footView.measure(0, 0);
		footHight = footView.getMeasuredHeight();

		// 隐藏尾部
		footView.setPadding(0, -footHight, 0, 0);
		// 初始化尾部控件
		/*
		 * propressbar_foot = (ProgressBar)
		 * findViewById(R.id.progressBar_refresh_foot); refresh_foot =
		 * (TextView) findViewById(R.id.refresh_more);
		 */
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		// down move up的点击事件
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 记录初始坐标
			startY = ev.getY();

			break;
		case MotionEvent.ACTION_MOVE:
			// 如果是正在刷新，不让用户操作
			if (headState == ISREFRESHING) {
				return false;
			}
			moveY = ev.getY();

			// 如果是第一条可见并且下拉，padding 跟着改
			if (firstVisibleItem == 0 && (moveY > startY)) {

				int paddingHeight = (int) (-headHight + (moveY - startY));

				// 如果拉出的距离大于headHeight；状态改变
				if (paddingHeight > 0 && headState == INIT) {
					// 状态改变，准备刷新
					headState = PREPAREREFERSH;
					// 改变控件的相关属性
					changeState();
				} else if (headState == PREPAREREFERSH && paddingHeight < 0) {

					headState = INIT;
					changeState();
				}
				headview.setPadding(0, paddingHeight, 0, 0);
				return true;
			}

			break;
		case MotionEvent.ACTION_UP:
			// 如果当前距离大于头部高度，缩回去
			if (headState == INIT) {
				headview.setPadding(0, -headHight, 0, 0);
			} else if (headState == PREPAREREFERSH) {
				// 如果距离大于等于头部高度
				headState = ISREFRESHING;
				changeState();
				headview.setPadding(0, 0, 0, 0);
				// 开始刷新，回调
				if (onrefreshcallback != null) {
					onrefreshcallback.onRefresh();
				}
			}
			break;
		default:
			break;
		}

		return super.onTouchEvent(ev);
	}

	// 点击事件

	public void changeState() {
		// TODO Auto-generated method stub
		switch (headState) {
		case INIT:
			propressbar_head.setVisibility(View.VISIBLE);
			refresh_head.setText("下拉刷新");
			refresh_time.setText(gettime());

			break;
		case PREPAREREFERSH:
			propressbar_head.setVisibility(View.VISIBLE);
			refresh_head.setText("释放刷新");
			refresh_time.setText(gettime());
			break;
		case ISREFRESHING:
			propressbar_head.setVisibility(View.VISIBLE);
			refresh_head.setText("正在刷新");
			refresh_time.setText(gettime());
			break;

		default:
			break;
		}

	}

	// 获取时间
	public String gettime() {
		// Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = adf.format(date);
		return time;

	}

	// 是否正在加载用一个boolean值
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		// 最后一条记录可见&手在上面||手拿掉==》加载操作 &当前不在加载
		if (!loading && getLastVisiblePosition() == getCount() - 1 && moveY < startY) {
			// 滚动状态
			if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
				// foot显示
				footView.setPadding(0, 0, 0, 0);
				loading = true;
				// 刷新，调用接口的未实现
				if (onrefreshcallback != null) {
					onrefreshcallback.onPull();
				}
			}
		}
	}

	// 获取当前的第一个可视化的item

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		this.firstVisibleItem = firstVisibleItem;

	}

	// 接口， 封装下拉刷新，上啦加载的方法
	public interface OnRefreshCallBack {
		void onRefresh(); // 刷新

		void onPull(); // 加载
	}

	// 刷新完成后
	public void completeRefresh() {
		// 改变padding状态
		headview.setPadding(0, -headHight, 0, 0);
		// 改变状态
		headState = INIT;
		changeState();
	}

	// 加载完成后，隐藏foot;loading=false
	public void completePull() {
		footView.setPadding(0, -footHight, 0, 0);
		loading = false;
	}

	
}
