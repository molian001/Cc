package com.bmob.im.demo.paopao;

import java.util.ArrayList;






import com.bmob.im.demo.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;

public class Activity_paopao extends Activity  {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add("a");
		list.add("a");
		list.add("a");
		list.add("a");
	
	
		MyListView lv = new MyListView(this, list);
		setContentView(lv);
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, list);
		lv.setAdapter(new MyAdapter(list, this));
	}
}
