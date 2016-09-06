package com.bmob.im.demo.paopao;

import java.util.ArrayList;

import com.bmob.im.demo.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	ArrayList<String> data=new ArrayList<String>();
	
	Context context;
	
	public MyAdapter(ArrayList<String> list, Context context) {
	
		
		
		data = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ArrayList user_icon = new ArrayList();
		user_icon.add(R.drawable.icon_user1);
		user_icon.add(R.drawable.user_icon2);
		user_icon.add(R.drawable.icon_user3);
		user_icon.add(R.drawable.icon_user4);
		user_icon.add(R.drawable.icon_user5);
		
		ArrayList<String> name =new ArrayList<String>();
		name.add("梦花剑璃");
		name.add("哈哈");
		name.add("13883807253");
		name.add("13983488187");
		name.add("18712779903");
		ArrayList<String> content=new ArrayList<String>();
		content.add("美美美");
		content.add("sbsbsb");
		content.add("2333333");
		content.add("gg");
		content.add("boom");
		ArrayList<String> time=new ArrayList<String>();
		time.add("刚刚");
		time.add("一天前");
		time.add("一天前");
		time.add("一周前");
		time.add("一个月前");
		ArrayList<String> phone=new ArrayList<String>();
		phone.add("来自XiaoMi");
		phone.add("来自XiaoMi");
		phone.add("来自XiaoMi");
		phone.add("来自Samsung");
		phone.add("来自Iphone6S");
		ArrayList<String> backname=new ArrayList<String>();
		backname.add("13883807253");
		backname.add("梦花剑璃");
		backname.add("哈哈");
		backname.add("18712779903");
		backname.add("13983488187");
		
		ArrayList<String> backcontent=new ArrayList<String>();
		backcontent.add("nice");
		backcontent.add("sb");
		backcontent.add("逗");
		backcontent.add("gg");
		backcontent.add("boomshakalaka");
		ImageView user_icons=null;

		TextView name1 = null;
		TextView content1 = null;
		TextView time1 = null;
		TextView phone1 = null;
		TextView backname1 = null;
		TextView backcontent1 = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_paopao, null);
			if(position < getCount()){
				
				user_icons=(ImageView) convertView.findViewById(R.id.user_icon);
				
				name1=(TextView) convertView.findViewById(R.id.user_name);
				content1=(TextView) convertView.findViewById(R.id.content);
				time1=(TextView) convertView.findViewById(R.id.publish_time);
				phone1=(TextView) convertView.findViewById(R.id.phone);
				backname1=(TextView) convertView.findViewById(R.id.backname);
				backcontent1=(TextView) convertView.findViewById(R.id.backcontent);
				name1.setText(name.get(position));
				content1.setText(content.get(position));
				time1.setText(time.get(position));
				phone1.setText(phone.get(position));
				backname1.setText(backname.get(position));
				backcontent1.setText(backcontent.get(position));
				Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), (Integer) user_icon.get(position)); 
				user_icons.setImageBitmap(bitmap);
			}
			GridView gridView = (GridView) convertView
					.findViewById(R.id.gridView);
			
			// gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			gridView.setAdapter(new ImageAdapter(context));
			final TextView tv = (TextView) convertView
					.findViewById(R.id.content);
			final TextView more = (TextView) convertView
					.findViewById(R.id.more);
			final ImageButton button = (ImageButton) convertView
					.findViewById(R.id.imgButton);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				}
			});
			more.getViewTreeObserver().addOnPreDrawListener(
					new OnPreDrawListener() {

						@Override
						public boolean onPreDraw() {
							// TODO Auto-generated method stub
							if (tv.getLineCount() >= 4) {
								more.setVisibility(View.VISIBLE);
							}
							return true;
						}
					});
		}

		return convertView;
	}

	private static class ViewHolder {

	}

}
