package com.geminno.club.activity;


import java.lang.reflect.Type;




import com.example.clubapp.R;
import com.geminno.club.model.LoginRegisterEntity;
import com.geminno.club.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author Administrator
 *石然 2016-3-9
 */
public class LoginActivity extends Activity implements OnClickListener{

	EditText et_phonenum;
	EditText et_password;
	Button btn_login;
	TextView tv_register;
	TextView tv_forgetpwd;
	ImageView im_reten;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		et_phonenum = (EditText) findViewById(R.id.et_phonenum);
		et_password = (EditText) findViewById(R.id.et_psw);
		
		
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		
		
		im_reten = (ImageView) findViewById(R.id.im_return);
		im_reten.setOnClickListener(this);
		
	
		tv_register = (TextView) findViewById(R.id.login_register);
		tv_register.setOnClickListener(this);
		tv_forgetpwd = (TextView) findViewById(R.id.tv_forgetpwd);
		tv_forgetpwd.setOnClickListener(this);
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		//点击右上角“注册” 跳转到Register界面
			case R.id.login_register:
				Intent intent = new Intent();
				intent.setClass(this, RegisterActivity.class);
				this.startActivityForResult(intent, 1);
			break;
			
		//点击登录
			case R.id.btn_login:
				
				//获取电话号码和登陆密码
				String phonenum = et_phonenum.getText().toString();
				String pwd = et_password.getText().toString();
				Log.i("club51", phonenum+","+pwd);
				
				/**
				 * 网络连接
				 */
				HttpUtils http = new HttpUtils();
				String url = "http://jlb.oular.com/mobileapp.php";
				RequestParams params = new RequestParams();
				params.addQueryStringParameter("mod","register");
				params.addQueryStringParameter("action","login");
				//测试数据
				params.addQueryStringParameter("userphone","15011111111");
				params.addQueryStringParameter("password","xiaojiang");
				params.addQueryStringParameter("client","android");
				
				http.send(HttpMethod.GET, url,params, new RequestCallBack<String>() {

					//网络连接失败
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						new AlertDialog.Builder(LoginActivity.this).setMessage("网络异常，请稍后······")
						.setPositiveButton("ok", new DialogInterface.OnClickListener() {								
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								
							}
						}).show();
						Log.i("club51", "连接失败");
					}

					//成功的连接并响应
					@Override
					public void onSuccess(ResponseInfo<String> jsonReturn) {
						// TODO Auto-generated method stub

							Gson gson = new Gson();
							Type type = new TypeToken<LoginRegisterEntity>(){}.getType();
								
							String jsonstr = jsonReturn.result;
							Log.i("club51", jsonstr);
							LoginRegisterEntity user = gson.fromJson(jsonstr, type);
							Log.i("club51", user.toString());
							//当登录的用户为合法用户时，则跳转到主页
							if(user.getErr_code()==0){
								Intent intent = new Intent(LoginActivity.this,MainActivity.class);
								intent.putExtra("user_id",String.valueOf(user.getData().getUid()));
								startActivity(intent);
								//跳转之后，销毁login界面
								finish();
							}else{
								Toast.makeText(LoginActivity.this, "用户名或密码不正确!", Toast.LENGTH_LONG).show();
							}
								
							
					}
				});
				
				//改变按钮点击状态̬
				btn_login.setSelected(true);
			break;
				
		//点击返回
			case R.id.im_return:
				
			break;
			
		//点击“忘记密码”
			case R.id.tv_forgetpwd:
				//弹跳一个窗口
				new AlertDialog.Builder(this).setMessage("请到club51官网查询密码")
				.setPositiveButton("ok", new DialogInterface.OnClickListener() {								
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						
					}
				}).show();
			break;
	
		}
	}
}
