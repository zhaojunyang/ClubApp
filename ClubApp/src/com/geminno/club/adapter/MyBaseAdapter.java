package com.geminno.club.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/*
 * ���е�adapter��Ҫ��дgetcount�ȷ��������Ҷ���һ���Ĵ��룻���Է�װ��һ��;
 * ���е�adapter����Ҫ��һ������
 * �����ࣺ�ڹ��췽����ָ����������
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {

	public List<T> list;
	Context context;
	int resoureId;
	
	/*
	 * ���췽���д��뷺������
	 */
	
	public MyBaseAdapter(List<T> list,Context context,int resoureId){
		this.list=list;
		this.context=context;
		this.resoureId=resoureId;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public T getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);//��ȡĳ��item�Ķ���
	}

	
	//???
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//�ҵ����ֵ�view���ҵ��ؼ������ÿؼ�ֵ��
		/*
		 * ԭ���߼���
		 * �ж�convertview�Ƿ�Ϊnull��Ϊnull:���½������֣�����convertview��
		 * convertview��viewholder�󶨣�ֻ����һ�οؼ���
		 */
		
		ViewHolder viewHolder=ViewHolder.get(context, parent, resoureId, convertView);
		
		/*�����һ�����������ĳһ���ؼ������ÿؼ���ֵ���ɣ�
		 * convert���ҳ�һ���ؼ������ÿؼ���ֵ
		 * viewholder�������пؼ�����ļ��ϣ�
		 * getItem(position)���������ݵļ��ϣ�
		 * 
		 */
		
		convert(viewHolder,getItem(position));
		return viewHolder.getConvertView();
		
	}

	/*
	 * convert���ҳ�һ���ؼ������ÿؼ���ֵ
		 * viewholder�������пؼ�����ļ��ϣ�
		 * getItem(position)���������ݵļ��ϣ�
	 */
	public abstract void convert(ViewHolder viewHolder,T item);
}
