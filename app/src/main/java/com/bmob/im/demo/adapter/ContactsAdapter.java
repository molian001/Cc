package com.bmob.im.demo.adapter;

import java.io.InputStream;
import java.util.ArrayList;

import com.bmob.im.demo.R;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.im.task.BRequest;
import cn.bmob.im.util.BmobLog;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;







import com.bmob.im.demo.adapter.AddFriendAdapter;
import com.bmob.im.demo.ui.BaseActivity.OnLeftButtonClickListener;
import com.bmob.im.demo.util.CollectionUtils;
import com.bmob.im.demo.view.HeaderLayout;
import com.bmob.im.demo.view.HeaderLayout.HeaderStyle;
import com.bmob.im.demo.view.HeaderLayout.onLeftImageButtonClickListener;
import com.bmob.im.demo.view.xlist.XListView;
import com.bmob.im.demo.view.xlist.XListView.IXListViewListener;


public class ContactsAdapter extends BaseAdapter {
	Context mContext = null;  
	
	  
    /**获取库Phon表字段**/  
    private static final String[] PHONES_PROJECTION = new String[] {  
        Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID,Phone.CONTACT_ID };  
     
    /**联系人显示名称**/  
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;  
      
    /**电话号码**/  
    private static final int PHONES_NUMBER_INDEX = 1;  
      
    /**头像ID**/  
    private static final int PHONES_PHOTO_ID_INDEX = 2;  
     
    /**联系人的ID**/  
    private static final int PHONES_CONTACT_ID_INDEX = 3;  
      
  
    /**联系人名称**/  
    private ArrayList<String> mContactsName = new ArrayList<String>();  
      
    /**联系人头像**/  
    private ArrayList<String> mContactsNumber = new ArrayList<String>();  
  
    /**联系人头像**/  
    private ArrayList<Bitmap> mContactsPhonto = new ArrayList<Bitmap>();  
      
    
   
  
	public ContactsAdapter(Context context) {  
        mContext = context;  
    }  
  
    public int getCount() {  
        //设置绘制数量  
        return mContactsName.size();  
    }  
  
    @Override  
    public boolean areAllItemsEnabled() {  
        return false;  
    }  
  
    public Object getItem(int position) {  
        return position;  
    }  
  
    public long getItemId(int position) {  
        return position;  
    }  
  
    public View getView(int position, View convertView, ViewGroup parent) {
    	ImageView iamge = null;
    	TextView title = null;
    	TextView text = null;
        View top=null;
        top=LayoutInflater.from(mContext).inflate(
    	    	R.layout.include_action_bar,null);
    	if (convertView == null || position < mContactsNumber.size()) {
    		convertView = LayoutInflater.from(mContext).inflate(
        	    	R.layout.set_contacts_p,null);
//    	iamge = (ImageView) convertView.findViewById(R.id.color_image);
//    	title = (TextView) convertView.findViewById(R.id.color_title);
//    	text = (TextView) convertView.findViewById(R.id.color_text);
    	}
    	// 绘制联系人名称
    	title.setText(mContactsName.get(position));
    	// 绘制联系人号码
    	text.setText(mContactsNumber.get(position));
    	// 绘制联系人头像
    	iamge.setImageBitmap(mContactsPhonto.get(position));
    	
    	return convertView;
    	}

	
   
	
}
