package com.geminno.club.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.clubapp.R;
import com.geminno.club.adapter.CommonBaseAdapter;
import com.geminno.club.view.CircleImageView;
import com.geminno.club.view.RefreshListView;
import com.geminno.club.view.RefreshListView.OnRefreshCallBack;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class SameCityActivityActivity extends Activity implements OnItemClickListener{
	 PopupWindow popupWindow;//活动排序弹出窗口
	 ListView sortListView;//排序listView
	 View contentView ;//排序窗口XML
	 RefreshListView lv_activity_list;//活动列表
	 int count=1;//第几页：加载次数+1
	 int pageSize=10;
	 int firstClick;//第一次单击item时间
	 int secondClick;//第二次单击item时间
	 Handler handler=new Handler();//
	 CommonBaseAdapter<com.geminno.club.model.Activity> adapter;
	 List<com.geminno.club.model.Activity> activites=new ArrayList<com.geminno.club.model.Activity>();//活动列表数据
	//记录上一次不满的记录集合
	List<com.geminno.club.model.Activity> preActivites=new ArrayList<com.geminno.club.model.Activity>();
	 private String[] timeSort = {"时间降序", "时间升序"};
	private String[] destinationSort = {"北京", "上海", "广州"};///iiiiii777
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 除去标题栏
		setContentView(R.layout.activity_same_city_activity);
		
		 //解析popuwindow窗口XML文件
        contentView = getLayoutInflater().inflate(R.layout.activity_sort_popwindow, null);
        //查找listView
        sortListView = (ListView) contentView.findViewById(R.id.listView);
        //监听事件
        sortListView.setOnItemClickListener(this);
        //活动列表listView
        lv_activity_list=(RefreshListView) findViewById(R.id.lv_activity_list);
        //初始化活动列表数据
        initData();
        //活动列表添加监听事件
        lv_activity_list.setOnRefreshCallBack(new OnRefreshCallBack() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				//过2s，执行run()；
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						//改变界面内容:清空原来的+新的数据
						//products=newproducts;//
						
						//添加新的数据:products
						//refreshData();
						activites.clear();
						initData();
						//改变listview数据显示
//						if(adapter!=null){
//						adapter.notifyDataSetChanged();
//						}
						//调用刷新完成方法
						lv_activity_list.completeRefresh();
					}
				}, 2000);
			}
			
			@Override
			public void onPull() {
				// TODO Auto-generated method stub
				//去网络上取：下一页数据（List）
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						
					
						
						count++;//加载次数加1
						//加载：第一次加载，获取第二页数据；第二次加载，获取第三页数据；
						
						/*
						 * 取下一页的数据；没有取到，弹出提示；
						 * 取到，更新adapter;
						 */
						addData();
						
						lv_activity_list.completePull();
		
					}

				
				}, 2000);
			}
		});
      //缩放动画
      		ScaleAnimation animation=new ScaleAnimation(0, 1, 0, 1);
      		//延迟秒数
      		animation.setDuration(1000);
      		//获取动画控制器,第二个参数设置动画间隔秒数，第一个控件执行0.5*2000秒后，第二个控件开始执行动画
      		LayoutAnimationController lac=new LayoutAnimationController(animation, 0);
      		//设置控件动画顺序
      		lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
      		//动画控制器绑定到布局上
      		lv_activity_list.setLayoutAnimation(lac);
      		//添加监听事件
      		lv_activity_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(firstClick==0){
					firstClick=(int) System.currentTimeMillis();
				}else{
					secondClick=(int) System.currentTimeMillis();
					if((secondClick-firstClick)<1000){
						Intent intent=new Intent();
						intent.setClass(SameCityActivityActivity.this, ActivityDetailsActivity.class);
						startActivity(intent);
					}
					firstClick=0;
					secondClick=0;
				}
				
				
			}
		});
	}
	//初始化活动列表数据
	private void initData() {
		
		//获取服务器数据
		HttpUtils httpUtils=new HttpUtils();
	
		//设置参数
		RequestParams params=new RequestParams();
		params.addQueryStringParameter("mod", "group");
		params.addQueryStringParameter("action", "activity_list");
		params.addQueryStringParameter("client", "android");
		params.addQueryStringParameter("fid", "0");
		params.addQueryStringParameter("page", String.valueOf(count));//第一次加载
		params.addQueryStringParameter("perpage", String.valueOf(pageSize));
//		params.addQueryStringParameter("pageNo", String.valueOf(count));//第一次加载
//		params.addQueryStringParameter("pageSize", String.valueOf(pageSize));
		//http://jlb.oular.com/mobileapp.php?mod=group&action=activity_list&client=android&fid=0&page=1&perpage=10
		//设置不缓存，及时获取服务器数据
		httpUtils.configCurrentHttpCacheExpiry(0); 
		httpUtils.send(HttpMethod.GET, "http://jlb.oular.com/mobileapp.php",
		//httpUtils.send(HttpMethod.GET, "http://10.201.1.38:8080/ClubManagement/ActivityListServlet",
				params,new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						//返回结果
					String result=	responseInfo.result;
					//裁减掉通讯状态
					String str1=result.split("data\":")[1].toString();
					String str2=str1.substring(0, str1.length()-1);
					//裁剪<fields>
					String str3=str2.replace("\"fields\":{", "")
							.replace("},\"is_praise", ",\"is_praise")
							.replace("class", "type").toString();
					System.out.println(str3);
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					//json转化成Product对象
					Gson gson=new Gson();
					Type type= new TypeToken<List<com.geminno.club.model.Activity>>(){}.getType();
					//刷新后取到的新数据
					List<com.geminno.club.model.Activity> newActivities=gson.fromJson(result, type);
				
					
					//查询到数据，添加到集合
					activites.addAll(newActivities);
					
					//改变listview的数据源
					if(adapter==null){
					//获取到数据之后，设置adapter
//					adapter=new MyBaseAdapter<Product>(products,RefreshListViewActivity.this,R.layout.product_item) {
//
//						@Override
//						public void convert(ViewHolder viewHolder, Product item) {
//							// TODO Auto-generated method stub
//							//给控件赋值
//							//viewHolder.setText(R.id.textView1, item.getName());
//							//viewHolder.setText(R.id.textView2, String.valueOf(item.getPrice()));
//							viewHolder.setText(R.id.textView1, "是的发送到");
//							viewHolder.setText(R.id.textView2, "你们");
//						}
//					};
						adapter=new CommonBaseAdapter<com.geminno.club.model.Activity>(SameCityActivityActivity.this,
								activites, R.layout.activity_item) {

							@Override
							public void convert(
									com.geminno.club.adapter.CommonBaseAdapter.ViewHolder holder,
									com.geminno.club.model.Activity item) {
								// TODO Auto-generated method stub
								System.out.println();
								System.out.println();
								holder.setText(R.id.tv_activityDate, "123");
								holder.setCircleImageViewByUrl(R.id.civ_club_logo, "http://img.7139.com/file/201206/21/e518e53e13fe38d1b0ed5ceaee3b8732.jpg");
//								
								
							}
						};
					
					//设置adpater
					lv_activity_list.setAdapter(adapter);
					}else{
						adapter.notifyDataSetChanged();
					}
				
					}


					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub
					}
				});
	}
	//加载数据
			public void addData(){
				
				//获取服务器数据
				HttpUtils httpUtils=new HttpUtils();
			
				//设置请求参数
				RequestParams params=new RequestParams();
				params.addQueryStringParameter("pageNo", String.valueOf(count));//第一次加载
				params.addQueryStringParameter("pageSize", String.valueOf(pageSize));
				
				
				httpUtils.configCurrentHttpCacheExpiry(0); 
				httpUtils.send(HttpMethod.GET, "http://10.201.1.38:8080/ClubManagement/ActivityListServlet",
						params,new RequestCallBack<String>() {

							@Override
							public void onSuccess(ResponseInfo<String> responseInfo) {
								// TODO Auto-generated method stub
								//返回结果
							String result=	responseInfo.result;
							//json转化成Product对象
							Gson gson=new Gson();
							Type type= new TypeToken<List<com.geminno.club.model.Activity>>(){}.getType();
							//刷新后取到的新数据:新加载的数据
							List<com.geminno.club.model.Activity> newProducts=gson.fromJson(result, type);
							
							//判断preproduct是否有记录，如果有，删掉
							if(preActivites!=null&&preActivites.size()!=0){
								//products移除preProducts
								activites.removeAll(preActivites);
								//清除preproducts
								preActivites.clear();
							}

							//判断加载时候，有没有数据:没有数据，弹出提示；有数据，判断有没有达到pagesize
							if(newProducts==null||newProducts.size()==0){
								Toast.makeText(SameCityActivityActivity.this, "没有更多数据", 1).show();
							    //如果没有加载到数据，页数不变（pull:+1;-1）
								count--; 
								
							}else {
								//只要查出来结果不为null

								//没有加载满;newProducts.size()<每页的记录条数
								if(newProducts!=null&&newProducts.size()<pageSize){
								//页数不变
								count--;
								//记录上一次的products
								preActivites.addAll(newProducts);
								}
							
							//添加新的集合
								activites.addAll(newProducts);
							
							//改变listview的数据源
							if(adapter==null){
							//获取到数据之后，设置adapter
							adapter=new CommonBaseAdapter<com.geminno.club.model.Activity>(SameCityActivityActivity.this,
										activites, R.layout.activity_item) {

									@Override
									public void convert(
											com.geminno.club.adapter.CommonBaseAdapter.ViewHolder holder,
											com.geminno.club.model.Activity item) {
										// TODO Auto-generated method stub
										
									}
								};
//							
							//设置adpater
							lv_activity_list.setAdapter(adapter);
							}else{
								adapter.notifyDataSetChanged();
							}
							}
							}

							@Override
							public void onFailure(HttpException error, String msg) {
								// TODO Auto-generated method stub
							}
						});
			}
	public void openActivitySort(View v){
		switch (v.getId()) {
		case R.id.rl_sortTime:
			initPopWindow(timeSort);
			break;
		case R.id.rl_sortDestination:
			initPopWindow(destinationSort);
			break;

		}
		popupWindow.showAsDropDown(v);
    }
	public void initPopWindow(String[] names){
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(SameCityActivityActivity.this,
        		R.layout.activity_sort_item, R.id.tv_sortValue, names);
		//listView绑定适配器
		sortListView.setAdapter(adapter);
        //popupWindow设置参数
    	popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
    			ViewGroup.LayoutParams.WRAP_CONTENT);
    	popupWindow.setFocusable(true);//取得焦点
    	popupWindow.setBackgroundDrawable(new BitmapDrawable());
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String sortType=(String) parent.getItemAtPosition(position);
		if("时间降序".equals(sortType)){
			
		}else if("时间升序".equals(sortType)){
			
		}else if("北京".equals(sortType)){
			
		}else if("上海".equals(sortType)){
			
		}else if("广州".equals(sortType)){
			
		}
		
	}
	//返回上页
			public void goBack(View v){
				Intent intent=new Intent();
				intent.setClass(SameCityActivityActivity.this, 
						MainActivity.class);
				startActivity(intent);
				
			}

}


//	@Override
//	public void onNothingSelected(AdapterView<?> parent) {
//		// TODO Auto-generated method stub
//	}
//
//	 //加载活动列表
//	private void initActivityList(int time,final int distination,final int
//	 layoutId) {
//	 //1.创建请求队列
//	 RequestQueue queue=Volley.newRequestQueue(this);
//	 //2.创建请求
//	 StringRequest request=new
//	 StringRequest("http://10.40.5.3:8080/ClubManagement/ActivityListServlet",
//	 new Listener<String>() {
//	 @Override
//	 public void onResponse(String response) {
//	 // TODO Auto-generated method stub
//	 Log.d("response", "response12");
//	 Gson gson=new Gson();
//	 Type type=new
//	 TypeToken<List<com.geminno.club.model.Activity>>(){}.getType();
//	 activities=gson.fromJson(response, type);
//	 lv_activity_list.setAdapter(new
//	 CommonBaseAdapter<com.geminno.club.model.Activity>(SameCityActivityActivity.this,
//	 activities, layoutId) {
//	
//	 @Override
//	 public void convert(
//	 com.geminno.club.adapter.CommonBaseAdapter.ViewHolder holder,
//	 com.geminno.club.model.Activity item) {
//	 // TODO Auto-generated method stub
//	
//	 }
//	 });
//	 }
//	 }, new ErrorListener() {
//	
//	 @Override
//	 public void onErrorResponse(VolleyError error) {
//	 // TODO Auto-generated method stub
//	 }
//	 });
//	 //3.请求添加到队列
//	 queue.add(request);
//	 }
//	// 初始化数据+刷新+加载
////	public void initActivityList(int time, final int distination,
////			final int layoutId) {
////
////		// 获取服务器数据
////		HttpUtils httpUtils = new HttpUtils();
////
////		// 设置参数
////		RequestParams params = new RequestParams();
////		params.addQueryStringParameter("pageNo", String.valueOf(count));// 第一次加载
////		params.addQueryStringParameter("pageSize", String.valueOf(pageSize));
////
////		// 设置不缓存，及时获取服务器数据
////		httpUtils.configCurrentHttpCacheExpiry(0);
////		httpUtils.send(HttpMethod.GET,
////				"http://10.40.5.3:8080/ClubManagement/ActivityListServlet",
////				params, new RequestCallBack<String>() {
////
////					@Override
////					public void onSuccess(ResponseInfo<String> responseInfo) {
////						// TODO Auto-generated method stub
////						// 返回结果
////						String result = responseInfo.result;
////						// json转化成Product对象
////						Gson gson = new Gson();
////						Type type = new TypeToken<List<com.geminno.club.model.Activity>>() {
////						}.getType();
////						// 刷新后取到的新数据
////						List<com.geminno.club.model.Activity> newProducts = gson
////								.fromJson(result, type);
////
////						// 查询到数据，添加到集合
////						activities.addAll(newProducts);
////
////						// 改变listview的数据源
////						if (adapter == null) {
////							// 获取到数据之后，设置adapter
////							// adapter=new
////							// MyBaseAdapter<com.geminno.club.model.Activity>(products,RefreshListViewActivity.this,R.layout.product_item)
////							// {
////							//
////							// @Override
////							// public void convert(ViewHolder viewHolder,
////							// com.geminno.club.model.Activity item) {
////							// // TODO Auto-generated method stub
////							// //给控件赋值
////							// viewHolder.setText(R.id.textView1,
////							// item.getName());
////							// viewHolder.setText(R.id.textView2,
////							// String.valueOf(item.getPrice()));
////							// }
////							// };
////							adapter = new CommonBaseAdapter<com.geminno.club.model.Activity>(
////									SameCityActivityActivity.this,
////									getActivities(), R.layout.activity_item) {
////
////								@Override
////								public void convert(
////										com.geminno.club.adapter.CommonBaseAdapter.ViewHolder holder,
////										com.geminno.club.model.Activity item) {
////									// TODO Auto-generated method stub
////									holder.setText(R.id.tv_activityDate,
////											"23123")
////											.setText(R.id.tv_activityDetails,
////													"sdfsdfsd");
////
////								}
////
////							};
////
////							// 设置adpater
////							lv_activity_list.setAdapter(adapter);
////						} else {
////							adapter.notifyDataSetChanged();
////						}
////
////					}
////
////					private List<com.geminno.club.model.Activity> getActivities() {
////						// TODO Auto-generated method stub
////						activities=new ArrayList<com.geminno.club.model.Activity>();
////						activities.add(new com.geminno.club.model.Activity());
////						activities.add(new com.geminno.club.model.Activity());
////						activities.add(new com.geminno.club.model.Activity());
////						activities.add(new com.geminno.club.model.Activity());
////						
////						return null;
////					}
////
////					@Override
////					public void onFailure(HttpException error, String msg) {
////						// TODO Auto-generated method stub
////					}
////				});
////	}


