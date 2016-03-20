package com.geminno.club.adapter;



import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.geminno.club.view.CircleImageView;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.TextView;


/*
 * 1��һ��convertview��Ӧһ��viewholder��
 * 2�����convertviewΪnull,����������ļ�����ֵ��convertview��convertview��viewholder�󶨣�
 * 3�����convertview��Ϊnull,��ֱ�Ӹ���convertview��ȡviewholder;
 */

public class ViewHolder {

	//˽�й��췽����������㴴������
	/*
	 * ��Դ�ļ���������
	 */
	Context context;
	
	
	View mConvertView;
	
	//��Ų��������пؼ�(Integer-View)
	SparseArray<View> mViews;//�������ϣ����ڴ��id-view
	
	
	private ViewHolder(Context context,ViewGroup parent,int resoureId){
		
		mViews=new SparseArray<View>();
		//������listview������Ӳ��֣�����Ҫ����false;
		mConvertView=	LayoutInflater.from(context).inflate(resoureId, parent,false);
		mConvertView.setTag(this);
		this.context=context;
	}
	/*
	 * һ��viewholder����һ��convertview����Ӧһ��sparsearray(���viewholder�еĿؼ�)
	 */
	
	//��ȡviewholder����-convertview-sparsearray
	
	/*���ۣ�
	 * convertview��mconvertview���Բ���ͬһ�������������ֻ����ʾ5����¼����ô��6����ʱ��convertview���Զ��ǵ�һ����view��
	 * getConvertview��������
	 */
	public static ViewHolder get(Context context,ViewGroup parent,int resoureId,View convertView){
		if(convertView==null){
			//���������ļ�����ֵ��convertview������convertview��viewholder�󶨣�
			Log.i(Constant.TAG, "convertviewΪnull");
			return new ViewHolder(context,parent,resoureId);
		}

			Log.i(Constant.TAG, "convertview��Ϊnull");
			return (ViewHolder) convertView.getTag();
		
	}
	
	
	/*
	 * ��ȡĳ��view������id����ȡĳ��view����
	 * ���������õ������пؼ�������ڼ����У�����ڼ����У���ֱ��ȡ��������ڼ����У����´���view���󣬷��뼯�ϣ�
	 * T��view�����ֻࣺҪ��ֵ��һ��view�����࣬������
	 */
	public <T extends View> T getView(int viewId){
		View v=mViews.get(viewId);
		//���������û��v
		if(v==null){
			//�ҵ��ؼ�:��ֵ��v
			v=mConvertView.findViewById(viewId);
			//��v���뼯��
			mViews.put(viewId, v);
		}
		return (T)v;
	}
	
	
	//��ȡconvertview����
	public View getConvertView(){
		return mConvertView;
	}
	
	/*
	 * ���е�viewholder��ͨ��id�ҵ��ؼ�����ͨ����������ȡ��ֵ�����Խ�������װ��һ����
	 */
	
	//��text���ݸ�ֵ��viewId�Ŀؼ��ϣ�
	public void setText(int viewId,String text){
		//ת����textview
		TextView tv=getView(viewId);
		//�����ݸ�ֵ��textview
		tv.setText(text);
	}
	
	//��ͼƬ������ʾ��imageview��
	public void setImageResource(int viewId,int drawableId){
	ImageView iv=getView(viewId);
	iv.setImageResource(drawableId);
	}
	
	//通过uri来对图片进行赋值
	public void setImageByUri(int viewId,String uri){
		BitmapUtils bitmapUtils = new BitmapUtils(context);
		ImageView view = getView(viewId);
		bitmapUtils.display(view, uri);
	}
	//通用对图片进行赋值
	public  void setCircleImageByUri(int viewId,String uri){
		final ImageView cview = getView(viewId);
		RequestQueue queue = Volley.newRequestQueue(context);
		
		ImageRequest requset = new ImageRequest(uri, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {
				// TODO Auto-generated method stub
				cview.setImageBitmap(response);
			}
		}, 0, 0, Config.RGB_565, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
		
		queue.add(requset);
	}

	
	
	
	

}
