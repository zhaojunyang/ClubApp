package com.geminno.club.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.clubapp.R;
public class BulidClubActivity extends Activity {
	//相机选择的弹窗
	private PopupWindow popuWindow;
	//返回图标
	private ImageView buildClub_back;
	//俱乐部LOGO
	private ImageView buildClub_LOGO;
	//俱乐部名字
	private EditText buildClub_name;
	//选择类型的布局
	private View buildClub_type;
	//定位图标
	private ImageView buildClub_location;
	//创建按钮
	private Button btn_build;
	//类型
	private TextView tv_type;
	
	//定位的显示
	private TextView tv_location;
	//表示打开照相机
	private final int OPEN_RESULT =1;
	//表示进入图库
	private final int PICK_RESULT =2;
	//定位 服务
	private LocationManager locationManager;
	//下拉列表中的选项
	String[] strs = {"攀岩","体育","户外"};
	//获得上下文
	private Context mContext;
	private Boolean hasLOGO  = false;//没有获取过头像
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bulid_club);
		mContext = this;
		
		//初始化控件
		//返回按钮
		buildClub_back = (ImageView) findViewById(R.id.buildclub_back);
		//获取LOGO
		buildClub_LOGO = (ImageView) findViewById(R.id.buildclub_LOGO);
		//俱乐部名字
		buildClub_name = (EditText) findViewById(R.id.buildclub_et_clubname);
		//俱乐部类型的选择
		buildClub_type = findViewById(R.id.buildclub_spinner_type);
		//俱乐部类型
		tv_type = (TextView) findViewById(R.id.buildclub_spinner_type_name);
		
		//俱乐部的位置
		tv_location = (TextView) findViewById(R.id.buildclub_location);
		
		//俱乐部地区的定位图标
		buildClub_location = (ImageView) findViewById(R.id.buildclub_iv_loca);
		//俱乐部的创建按钮
		btn_build = (Button) findViewById(R.id.buildclub_button);
		
		//为每个控件添加相应的点击事件
		buildClub_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 结束activity
				Toast.makeText(getApplicationContext(), "点击返回按钮", 0).show();
				finish();
			}
		});
		
		
		buildClub_LOGO.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 切换俱乐部的LOGO
				Toast.makeText(getApplicationContext(), "切换LOGO", 0).show();
				showPopuWindow(v);
			}
		});
		
		
		
		buildClub_location.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 定位服务
				//给出动画，显示定位中
				Toast.makeText(mContext, "定位中", 0).show();
				//判断是否打开GPS
				if(openGPS())
					getLocation();//获取定位的地址
				
			}
		});
		
		
		btn_build.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 点击创建俱乐部
				
				if(!hasLOGO){
					Toast.makeText(getApplicationContext(), "请选择俱乐部的LOGO", 0).show();
					return;
				}
				
				if(TextUtils.isEmpty(buildClub_name.getText().toString())){
					Toast.makeText(getApplicationContext(), "请输入俱乐部的名称", 0).show();
					return;
				}
				if(TextUtils.isEmpty(tv_location.getText().toString())){
					Toast.makeText(getApplicationContext(), "请定位", 0).show();
					return ;
				}
				if(tv_type.getText().toString().equals("请选择...")){
					Toast.makeText(getApplicationContext(), "请选择俱乐部的类型", 0).show();
					return ;
				}
				Toast.makeText(getApplicationContext(), "创建成功", 0).show();
				Intent intent = new Intent();
				setResult(RESULT_OK);
				finish();
			}
		});
		
		
		//为选择类型的布局设置点击事件。弹出popupWindow
		buildClub_type.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 点击弹出弹窗
				showTypeWindow(v);
			}

			
		});
		
		
		
	}
	
	//弹出选择类型的弹窗
	private void showTypeWindow(View v) {
		// TODO 
		View convertView = 
				LayoutInflater.from(mContext).inflate(R.layout.buildclub_choice_type, null);
		
		final PopupWindow popupWindow = new PopupWindow(convertView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,true);
		
		ListView lv = (ListView) convertView.findViewById(R.id.listView1);
		
		lv.setAdapter(new MyAdapter());
		//为类型添加点击事件
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO 
				tv_type.setText(strs[arg2]);
				popupWindow.dismiss();
			}
		});
		
		
		popupWindow.setTouchable(true);
		popupWindow.setTouchInterceptor(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		
		popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.club_choiceness_shape_type));
		//设置窗口显示的动画效果
		popupWindow.setAnimationStyle(R.style.PopupAnimation);
		// 设置好参数之后再show
		popupWindow.showAsDropDown(v, 0, 60);
		//popupWindow.showAtLocation(v, Gravity.BOTTOM,0,0);
		popupWindow.update(); 
		
		
		
	}
	
	
	//设置适配器
	private class MyAdapter extends BaseAdapter{
		
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return strs.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return strs[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO 给相应的控件赋值
			View v = LayoutInflater.from(mContext).inflate(R.layout.item_buildclub_clubtype_choice, null);
			TextView tv = (TextView) v.findViewById(R.id.textView1);
			tv.setText(strs[position]);
			return v;
		}
		
	}
	
	
	
	//判断是否打开GPS
	public Boolean openGPS(){
		//获取系统位置服务管理
		locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//判断服务是否打开
		if(locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)){
			return true;
		}
		Toast.makeText(this, "请打开GPS服务", Toast.LENGTH_LONG).show();
		return false;
	}
	//判断位置
	public void getLocation(){
		LocationManager locationManager=null;
		//获得服务名称
		String servceName=Context.LOCATION_SERVICE;
		//获得具体的位置
		locationManager=(LocationManager) getSystemService(servceName);
		//确定一个比标准
		Criteria criteria=new Criteria();
		//获得精度
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		//获得海拔
		criteria.setAltitudeRequired(false);
		//低功率
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		//获得GPS信息
		String privide=locationManager.getBestProvider(criteria, true);
		//最后一次获得位置
		Location location=locationManager.getLastKnownLocation(privide);
		//更新位置信息
		updateLocation(location);
		locationManager.requestLocationUpdates(privide, 10*1000, 50, listener);
	}
	//创建监听
	private final LocationListener listener=new LocationListener() {
		
		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub
		}
	};
	
	//更新位置信息
	public void updateLocation(Location location){
		if(location!=null){
			double longitude=location.getLongitude();  //经度
			double latitude=location.getLatitude();  //纬度
			getCity(longitude+"",latitude+"");
		}else{
			tv_location.setText("获得地址失败");
		}
	}
	//获得其中的城市
	private void getCity(String longitude,String latitude) {
		//1.创建请求队列
		 RequestQueue  queue=Volley.newRequestQueue(this);
		 String url = "http://api.map.baidu.com/geocoder?output=json&location="+latitude+","+longitude+"key=13162560786";
		 //2.创建请求
		 StringRequest request  = new StringRequest(url, new Listener<String>(){

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				//Gson gson=new Gson();这里嫌建立entity麻烦，所以没有去json解析，直接字符串裁剪了,可以先把线面的接口在360浏览器中打开看一下json的结构
				//http://api.map.baidu.com/geocoder?output=json&location=31.2812680000,120.7487500000key=13162560786
				String[] arr=response.split(":")[9].split(",");
				String[] arr2=response.split(":")[13].split(",");
				String location = arr2[0]+arr[0];
				location = location.replaceAll("\"", "");
				Toast.makeText(BulidClubActivity.this, "定位成功", Toast.LENGTH_LONG).show();
				tv_location.setText(location);	
			}
			 
		 } , new ErrorListener(){

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}});
		
		 //3.请求添加到队列
		 queue.add(request);
	}
	
	//等待跳往照相机返回的结果
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO 等待返回结果展示图片
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			hasLOGO = true;
			switch (requestCode) {
			case OPEN_RESULT:
				//表示从相机之后的结果
				Bundle bundle = data.getExtras();
				Bitmap bitmap = (Bitmap) bundle.get("data");
				buildClub_LOGO.setImageBitmap(bitmap);
				break;
			case PICK_RESULT:
				//表示从图库中取得的图片
				Uri uri = data.getData();
				buildClub_LOGO.setImageURI(uri);
				break;

			default:
				break;
			}
		}
		popuWindow.dismiss();
		
		
	}
	
	
	
	
	
	
	
	//动态显示popuWindow
	public void showPopuWindow(View v){
		View convertView = LayoutInflater.from(BulidClubActivity.this)
				.inflate(R.layout.club_buildclub_popuwindow, null);
		
		//为popupWindow添加点击事件，
		Button btn_cammera = (Button) convertView.findViewById(R.id.club_goto_cammera);
		Button btn_photos = (Button) convertView.findViewById(R.id.club_goto_photos);
		
		btn_cammera.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 打开相机，进行拍照
				Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent,OPEN_RESULT);
			}
		});
		
		
		btn_photos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 进入图库，查找图片
				Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, PICK_RESULT);
			}
			
		});
		
		
		
		popuWindow = new PopupWindow
				(convertView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,true);
		
		popuWindow.setTouchable(true);
		popuWindow.setTouchInterceptor(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO 返回true无法点击popular中的数据
				return false;
			}
		});
		
		popuWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.club_choiceness_shape_type));
		//设置窗口显示的动画效果
		popuWindow.setAnimationStyle(R.style.PopupAnimation);
		// 设置好参数之后再show
		popuWindow.showAtLocation(v, Gravity.BOTTOM,0,0);
		popuWindow.update(); 
	
	}
	
	
}
