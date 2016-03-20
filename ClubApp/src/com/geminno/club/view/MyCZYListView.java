package com.geminno.club.view;

import com.example.clubapp.R;
import com.geminno.club.view.RefreshListView.OnRefreshCallBack;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

public class MyCZYListView extends ListView implements OnScrollListener{
	private OnRefreshCallBack onRefreshCallBack;

	//接口，封装下拉刷新，上拉加载的方法
	public interface OnRefreshCallBack{
		void onRefresh();//刷新的方法
		void onPull();//下拉加载的办法
	}

	public MyCZYListView(Context context) {
		super(context);
		// TODO 构造方法创建refreshListView
		initAnimation(context);
		initHead(context);
		initFoot(context);
		setOnScrollListener(this);
	}
	public MyCZYListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO 构造方法创建refreshListView
		initAnimation(context);
		initHead(context);
		initFoot(context);
		setOnScrollListener(this);
	}

	public MyCZYListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO 构造方法创建refreshListView
		initAnimation(context);
		initHead(context);
		initFoot(context);
		setOnScrollListener(this);
	}
	
	public void setOnRefreshCallBack(OnRefreshCallBack onRefreshCallBack) {
		// TODO 设置接口
		this.onRefreshCallBack = onRefreshCallBack;
	}
	
	//尾部的布局
	View footView;
	int footHeight;//尾部的高度
	private void initFoot(Context context) {
		// TODO 初始化尾部
		footView=LayoutInflater.from(context).inflate(R.layout.refresh_layout, null);
		ImageView iv = (ImageView) footView.findViewById(R.id.imageView1);
		iv.setImageResource(R.drawable.czy_up_load_list);
		upAnimation = (AnimationDrawable) iv.getDrawable();
		
		//添加foot
		addFooterView(footView);
		//获取foot的高度
		footView.measure(0, 0);
		footHeight=footView.getMeasuredHeight();
		
		//隐藏foot
		footView.setPadding(0, -footHeight, 0, 0);
	}
	
	//加载动画和刷新动画
	AnimationDrawable downAnimation;//下拉刷新过程中的动画
	AnimationDrawable upAnimation;//上拉加载时候的动画
	private void initAnimation(Context context) {
		// TODO 初始化动画
		//这里的话在初始化界面中去操作
	}
	
	View headView;//整个布局文件的
	int headHeight;
	View animaView;//动画的布局文件
	int animaHeight;
	View relationView ;//头部的关系布局
	int relationHeight;//头部的关系布局的高度
	private void initHead(Context context) {
		// TODO 初始化头部
		headView = LayoutInflater.from(context).inflate(R.layout.club_invitation_details_new, null);
		animaView = headView.findViewById(R.id.linearLayout12);
		
		relationView = headView.findViewById(R.id.club_invitation_detail_relat);
		relationView.measure(0, 0);
		//计算动画线性布局的文件
		animaView.measure(0, 0);
		animaHeight = animaView.getMeasuredHeight();
		
		//老师方法测试
		int[] listViewLocation = new int[2];
		//整个布局的位置
		headView.getLocationOnScreen(listViewLocation);
		int listViewY = listViewLocation[1];
		int[] headerLocation = new int[2];
		relationView.getLocationOnScreen(headerLocation);
		//身体布局的位置
		int headerY = headerLocation[1];
		//Toast.makeText(getContext(), listViewY+":"+headerY, 0).show();
		//Log.i("helloWorld", listViewY+":"+headerY);
		Log.i("HHHHH", listViewY+":"+headerY);
		
		
		//实例化下拉刷新动画
		ImageView iv = (ImageView) headView.findViewById(R.id.iv_animation);
		iv.setImageResource(R.drawable.czy_refresh_load_list);
		downAnimation = (AnimationDrawable) iv.getDrawable();
		//添加头部
		addHeaderView(headView);
		//获得head的高度
		headView.measure(0, 0);
		headHeight = headView.getMeasuredHeight();
		//隐藏头部
		headView.setPadding(0, -animaHeight, 0, 0);
	}
	
	float startY;//获取开始时候的Y轴
	float moveY;//获取移动过程中Y轴的坐标
	public int headState;//头部状态（INIT,PREPAREREFERSHER ISREFERING）
	public final int INIT=0;//初始状态
	public  final int PREPAREREFERSH=1;//准备刷新
	public  final int ISREFERING=2;//正在刷新
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO 通过状态来判断是否需要刷新
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//记录初始点坐标
			startY = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			
			
			//老师方法测试
			int[] listViewLocation = new int[2];
			//整个布局的位置
			headView.getLocationOnScreen(listViewLocation);
			int listViewY = listViewLocation[1];
			int[] headerLocation = new int[2];
			relationView.getLocationOnScreen(headerLocation);
			//身体布局的位置
			int headerY = headerLocation[1];
			//Toast.makeText(getContext(), listViewY+":"+headerY, 0).show();
			Log.i("HHHHH", listViewY+":"+headerY);
			
			
			//当前状态，正在刷新
			//如果正在刷新的话，就不让用户继续移动
			if(headState == ISREFERING){
				return false;
			}
			moveY = ev.getY();
			//如果是第一条可见并且下拉，pading 跟着改
			if((moveY-startY)>60 && (moveY>startY)){
				if(headerY<0){
					break;
				}else{
					//任然隐藏的距离
					int paddingHeight = (int)(-animaHeight+(moveY-startY));
					//Toast.makeText(getContext(), visibleItemCount, 0).show();
					//如果拉出的距离大于headHeight；状态改变
					if(paddingHeight >= 0 && headState == INIT){
						Toast.makeText(getContext(), animaView.isShown()+"", 0).show();
						//状态改变，准备刷新
						headState = PREPAREREFERSH;
						//改变控件相关的属性
						changeState();
					}else if(headState==PREPAREREFERSH&&paddingHeight<0 ){
	        			//准备刷新状态，变成初始状态
	        			headState=INIT;
	        			changeState();
	        		}
					headView.setPadding(0, paddingHeight, 0, 0);
					return true;
				}
				
				
			}
			
			break;
		case MotionEvent.ACTION_UP:
			//如果当前距离<头部高度（INIT）：缩回去
			if(headState == INIT){
				headView.setPadding(0, -animaHeight, 0, 0);
			}else if(headState == PREPAREREFERSH){
				//如果距离大于等于头部高度（）
				headState = ISREFERING;
				changeState();//改变界面控件
				//padding值改变
				headView.setPadding(0, 0, 0, 0);
				//开始刷新操作：回调
				if(onRefreshCallBack != null){
					onRefreshCallBack.onRefresh();
				}
			}
			
			break;

		default:
			break;
		}
		
		return super.onTouchEvent(ev);
		
		
	}
	
	private void changeState() {
		// TODO 改变控件的状态
		switch (headState) {
		case INIT:
			downAnimation.start();
			break;
		case PREPAREREFERSH:
			downAnimation.start();
			break;
		case ISREFERING:
			downAnimation.start();
			break;

		default:
			break;
		}
	}
	
	
	//获取当前的第一个可视化的item
	public int firstVisibleItem;
	public int visibleItemCount;
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO 监听手指的触碰
		this.firstVisibleItem = firstVisibleItem;
		this.visibleItemCount = visibleItemCount;
	}
	
	
	//是否正在加载用一个boolean值
	boolean loading =false;
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		//手放开的时候加载数据
		//条件：最后一条记录可见并且手拿掉-->加载操作，当前不再加载
		if(!loading && getLastVisiblePosition() == getCount()-1 && moveY<startY){
			
			//滚动状态
			if(scrollState==SCROLL_STATE_IDLE || scrollState==SCROLL_STATE_TOUCH_SCROLL){
				//foot显示
				footView.setPadding(0, 0, 0, 0);
				upAnimation.start();
				loading = true;
				//调用onpull方法
				if(onRefreshCallBack != null){
					onRefreshCallBack.onPull();
				}
			}
		}
		
	}
	
	//刷新完成后：
	public void completeRefresh(){
		//改变padding值；
		headView.setPadding(0, -animaHeight, 0, 0);
		//改变状态
		headState=INIT;
		changeState();
	}
	
	
	//加载完成:隐藏foot;loading=false
	public void completePull(){
		footView.setPadding(0, -footHeight, 0, 0);
		loading=false;
	}

}
