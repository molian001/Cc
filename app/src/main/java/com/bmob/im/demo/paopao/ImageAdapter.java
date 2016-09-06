package com.bmob.im.demo.paopao;

import java.util.ArrayList;

import com.bmob.im.demo.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Integer> data;

	public ImageAdapter(Context context) {
		this.context = context;
	
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if (convertView == null) {
			ImageView view = new ImageView(context);
			view.setImageResource(R.drawable.user_icon);
			convertView = view;
		}

		return convertView;
	}

}
