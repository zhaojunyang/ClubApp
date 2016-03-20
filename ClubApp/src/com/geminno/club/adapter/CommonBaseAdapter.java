package com.geminno.club.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.clubapp.R;
import com.geminno.club.view.CircleImageView;
import com.geminno.club.view.RoundImageView;
import com.lidroid.xutils.BitmapUtils;

public abstract class CommonBaseAdapter<T> extends BaseAdapter {
	private static Context context;
	private List<T> mDatas;
	private final int mItemLayoutId; 
	
	public CommonBaseAdapter(Context context,List<T> mDatas,int itemLayoutId) {
		super();
		this.context = context;
		this.mDatas=mDatas;
		this.mItemLayoutId=itemLayoutId;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//��ȡViewHolder
		ViewHolder holder=ViewHolder.get(context,convertView,parent,mItemLayoutId,position);
		//��ȡ�ؼ�
		convert(holder,getItem(position));
		//����ConvertView
		return holder.getConvertView();  
	}
	/*
	 * ViewHolder�������item���ֺͿؼ�
	 * */
	public static class ViewHolder {
		private final SparseArray<View> mViews;
		private View mConvertView;
		private Context mContext;
		private ViewHolder(Context context, ViewGroup parent, int layoutId,
				int position) {
			mViews = new SparseArray<View>();
			this.mContext=context;
			mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
					false);
			mConvertView.setTag(this);
		}

		/*
		 * ���ViewHolder
		 */
		public static ViewHolder get(Context context, View convertView,
				ViewGroup parent, int layoutId, int position) {
			if (convertView == null) {
				return new ViewHolder(context, parent, layoutId, position);
			}
			return (ViewHolder) convertView.getTag();
		}

		/*
		 * ͨ��ؼ���Id��ȡ���ڵĿؼ������û�������views
		 */
		public <T extends View> T getView(int viewId) {
			View view = mViews.get(viewId);
			if (view == null) {
				view = mConvertView.findViewById(viewId);
				mViews.put(viewId, view);
			}
			return (T) view;

		}

		/*
		 * ���ConvertView
		 */
		public View getConvertView() {
			return mConvertView;
		}

		/*
		 * ΪTextView�����ַ�
		 */
		public ViewHolder setText(int viewId, String text) {
			TextView view = getView(viewId);
			view.setText(text);
			return this;
		}
		/*
		* ΪImageView����ͼƬ 
		*/  
		   public ViewHolder setImageResource(int viewId, int drawableId)  
		   {  
		       ImageView view = getView(viewId);  
		        view.setImageResource(drawableId);  
		        return this;  
		    }  
		  
		    /** 
		     * ΪImageView����ͼƬ 
		     */  
		    public ViewHolder setImageBitmap(int viewId, Bitmap bm)  
		    {  
		        ImageView view = getView(viewId);  
		        view.setImageBitmap(bm);  
		        return this;  
		    }  
		  
		    /* 
		     * ΪImageView����ͼƬ 
		     */  
		    public ViewHolder setImageByUrl(int viewId, String url)  
		    {  
		        BitmapUtils bitmapUtils=new BitmapUtils(mContext);
		        bitmapUtils.display(getView(viewId), url);
		        return this;  
		    }
		    
		    
		    public ViewHolder setRoundImageByUrl(int viewId, String url)  
		    {  
		    	
		    	final RoundImageView roundView = getView(viewId);
		        RequestQueue mQueue = Volley.newRequestQueue(mContext);
		        
		        ImageRequest request = new ImageRequest(url, new Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap response) {
						// TODO Auto-generated method stub
						roundView.setImageBitmap(response);
					}
		        	
		        }, 0, 0, Config.RGB_565, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						
					}
				});
		        mQueue.add(request);
		        return this;  
		    }  
		    
		    
		    
		    /*
		     * 加载自定义ImageView
		     * */
		    public ViewHolder setCircleImageViewByUrl(int viewId, String url)  
		    {  
//		    	BitmapUtils bitmapUtils=new BitmapUtils(mContext);
//		    	bitmapUtils.display(getView(viewId), url);
		    	
		    	final CircleImageView civ=getView(viewId); 
			    RequestQueue mQueue = Volley.newRequestQueue(context);  
				ImageRequest imageRequest = new ImageRequest(url,  
				        new Response.Listener<Bitmap>() {  
				            @Override  
				            public void onResponse(Bitmap response) {  
				            	civ.setImageBitmap(response);  
				            }  
				        }, 0, 0, Config.RGB_565, new Response.ErrorListener() {  
				            @Override  
				            public void onErrorResponse(VolleyError error) {  
				            	civ.setImageResource(R.drawable.ic_launcher);  
				            }  
				        }); 
				mQueue.add(imageRequest);
		    
		    	return this;  
		    }
		   

	}
	/*
	 * ���⿪���ӿڣ��ؼ���ֵ
	 * */
	public  abstract void convert(ViewHolder holder, T item);
}

