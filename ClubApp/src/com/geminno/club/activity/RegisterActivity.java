package com.geminno.club.activity;


import java.lang.reflect.Type;

import com.example.clubapp.R;
import com.geminno.club.model.CodeEntity;
import com.geminno.club.model.LoginRegisterEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
/**
 * 
 * @author Administrator
 *石然 2016-3-9
 */
public class RegisterActivity extends Activity implements OnClickListener{

	@ViewInject(R.id.et_phonenum)
	EditText et_phonenum;
	
	@ViewInject(R.id.et_psw)
	EditText et_psw;
	
	@ViewInject(R.id.et_verication_code)
	EditText et_verication_code;
	
	@ViewInject(R.id.get_verication)
	Button get_verication;
	
	@ViewInject(R.id.btn_register)
	Button btn_register;
	
	@ViewInject(R.id.im_return_register)
	ImageView im_return_register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		ViewUtils.inject(this);
		
		
		get_verication.setOnClickListener(this);
		im_return_register.setOnClickListener(this);
		btn_register.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		/**
		 * 返回login界面
		 */
		case R.id.im_return_register:
			/***
			 * 点击注册界面的返回，跳回登陆界面，什么操作都没有，并且消费这个界面
			 */
			Intent intent = new Intent();
			intent.setClass(this, LoginActivity.class);
			this.setResult(1,intent);
			finish();
			break;
		/**
		 * 获取验证码	
		 */
		case R.id.get_verication:
			get_verication.setSelected(true);
			HttpUtils http_get_verication = new HttpUtils();
			String url = "http://jlb.oular.com/mobileapp.php";
			RequestParams params_get_verication = new RequestParams();
			params_get_verication.addQueryStringParameter("mod","register");
			params_get_verication.addQueryStringParameter("action","getcode");
			//测试数据
			params_get_verication.addQueryStringParameter("userphone","15011111111");
			params_get_verication.addQueryStringParameter("client","android");
			params_get_verication.addQueryStringParameter("type","register");
			
			http_get_verication.send(HttpMethod.GET, url, params_get_verication, new RequestCallBack<String>() {

				//网络连接失败
				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					new AlertDialog.Builder(RegisterActivity.this).setMessage("网络异常，请稍后······")
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
						Type type = new TypeToken<CodeEntity>(){}.getType();
							
						String jsonstr = jsonReturn.result;
						Log.i("club51", jsonstr);
						CodeEntity code = gson.fromJson(jsonstr, type);
						//当登录的用户为合法用户时，则跳转到主页
						if(code.getErr_code()==0){
							
							Toast.makeText(RegisterActivity.this, "验证码发送成功!", Toast.LENGTH_LONG).show();
						}else{
							Toast.makeText(RegisterActivity.this, "手机号不存在或已注册过!", Toast.LENGTH_LONG).show();
						}
							
						
				}
			});
			
			break;
		/**
		 * 注册账号	
		 */
		case R.id.btn_register:
			String user_name = et_phonenum.getText().toString();
			String authcode = et_verication_code.getText().toString();
			String password = et_psw.getText().toString();
			/**
			 * 网络连接
			 */
			HttpUtils http_register = new HttpUtils();
			String url_register = "http://jlb.oular.com/mobileapp.php";
			RequestParams params_register = new RequestParams();
			params_register.addQueryStringParameter("mod","register");
			params_register.addQueryStringParameter("action","register");
			//测试数据
			params_register.addQueryStringParameter("userphone","15011111111");
			params_register.addQueryStringParameter("password","xiaojiang");
			params_register.addQueryStringParameter("client","android");
			params_register.addQueryStringParameter("authcode","1011");
			
			http_register.send(HttpMethod.GET, url_register, params_register, new RequestCallBack<String>() {

				//网络连接失败
				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					new AlertDialog.Builder(RegisterActivity.this).setMessage("网络异常，请稍后······")
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
						//当登录的用户为合法用户时，则跳转到主页
						if(user.getErr_code()==0){
							Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
							intent.putExtra("user_id",String.valueOf(user.getData().getUid()));
							startActivity(intent);
							//跳转之后，销毁login界面
							finish();
						}else{
							Toast.makeText(RegisterActivity.this, "用户名或密码已存在!", Toast.LENGTH_LONG).show();
						}
							
						
				}
			});
			
			
			break;	
		}
	}

	

	
}
