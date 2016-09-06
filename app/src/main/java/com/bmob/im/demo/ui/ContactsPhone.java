package com.bmob.im.demo.ui;

import android.app.Activity;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bmob.im.demo.R;
import com.bmob.im.demo.ui.SlideView.OnSlideListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ContactsPhone extends Activity implements OnItemClickListener, OnClickListener,
OnSlideListener  {

	Context mContext = null;  
	int position1;
	
	
    private static final String TAG = "MainActivity";

    private ListViewCompat mListView;

    private List<MessageItem> mMessageItems = new ArrayList<MessageItem>();

    private SlideView mLastSlideViewWithStatusOn;

    private LinearLayout Parent;
    private LayoutInflater mInflater;

    private static final String[] PHONES_PROJECTION = new String[] {  
        Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID,Phone.CONTACT_ID };  
         private static final int PHONES_DISPLAY_NAME_INDEX = 0;
      

    private static final int PHONES_NUMBER_INDEX = 1;  
      

    private static final int PHONES_PHOTO_ID_INDEX = 2;  
     

    private static final int PHONES_CONTACT_ID_INDEX = 3;  
      
  

    private ArrayList<String> mContactsName = new ArrayList<String>();  

    private ArrayList<String> mContactsNumber = new ArrayList<String>();  
  

    private ArrayList<Bitmap> mContactsPhonto = new ArrayList<Bitmap>();  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;  
        
        setContentView(R.layout.set_contacts_p);
        getPhoneContacts();
        initView();
    }

    private void initView() {
    	mInflater=getLayoutInflater();
        Parent= (LinearLayout) mInflater.inflate(R.layout.set_contacts_p,null);
        mListView = (ListViewCompat) findViewById(R.id.list);
        for (int i = 0; i < mContactsName.size(); i++) {
            MessageItem item = new MessageItem();
         
                item.iconRes = R.drawable.default_head;
                item.title =mContactsName.get(i);
                item.msg = mContactsNumber.get(i);
                item.time = "18:18";
            
            mMessageItems.add(item);
        }
        mListView.setAdapter(new SlideAdapter());
        mListView.setOnItemClickListener(this);
    }

    private class SlideAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        SlideAdapter() {
            super();
            mInflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return mMessageItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mMessageItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            SlideView slideView = (SlideView) convertView;
            if (slideView == null) {
                View itemView = mInflater.inflate(R.layout.item_contacts, null);

                slideView = new SlideView(ContactsPhone.this);
                slideView.setContentView(itemView);

                holder = new ViewHolder(slideView);
                slideView.setOnSlideListener(ContactsPhone.this);
                slideView.setTag(holder);
            } else {
                holder = (ViewHolder) slideView.getTag();
            }
            MessageItem item = mMessageItems.get(position);
            item.slideView = slideView;
            item.slideView.shrink();

            holder.icon.setImageResource(item.iconRes);
            holder.title.setText(item.title);
            holder.msg.setText(item.msg);
            holder.time.setText(item.time);
            holder.deleteHolder.setOnClickListener(ContactsPhone.this);
            holder.wifecall.setOnClickListener(ContactsPhone.this);
            holder.call.setOnClickListener(ContactsPhone.this);
            holder.sendmsg.setOnClickListener(ContactsPhone.this);
            return slideView;
        }

    }

    private void getPhoneContacts() {  
    ContentResolver resolver = mContext.getContentResolver();
    Cursor phoneCursor = resolver.query(Phone.CONTENT_URI,PHONES_PROJECTION, null, null, null);
    if (phoneCursor != null) {  
        while (phoneCursor.moveToNext()) {

        String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);  

        if (TextUtils.isEmpty(phoneNumber))  
            continue;  

        String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);  

        Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);  

        Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);  

        Bitmap contactPhoto = null;  
  
        if(photoid > 0 ) {
            Uri uri =ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,contactid);  
            InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri);  
            contactPhoto = BitmapFactory.decodeStream(input);  
        }else {  
            contactPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.default_head);  
        }  
          
        mContactsName.add(contactName);  
        mContactsNumber.add(phoneNumber);  
        mContactsPhonto.add(contactPhoto);  
        }  
  
        phoneCursor.close();  
    }  
    }  
      

    private void getSIMContacts() {  
    ContentResolver resolver = mContext.getContentResolver();  

    Uri uri = Uri.parse("content://icc/adn");  
    Cursor phoneCursor = resolver.query(uri, PHONES_PROJECTION, null, null,  
        null);  
  
    if (phoneCursor != null) {  
        while (phoneCursor.moveToNext()) {  

        String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);  

        if (TextUtils.isEmpty(phoneNumber))  
            continue;  

        String contactName = phoneCursor  
            .getString(PHONES_DISPLAY_NAME_INDEX);  

        mContactsName.add(contactName);  
        mContactsNumber.add(phoneNumber);  
        }  
  
        phoneCursor.close();  
    }  
    }  
    public class MessageItem {
        public int iconRes;
        public String title;
        public String msg;
        public String time;
        public SlideView slideView;
    }

    private static class ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView msg;
        public TextView time;
        public ViewGroup deleteHolder;
        public TextView wifecall;
        public TextView call;
        public TextView sendmsg;

        ViewHolder(View view) {
            icon = (ImageView) view.findViewById(R.id.icon);
            title = (TextView) view.findViewById(R.id.title);
            msg = (TextView) view.findViewById(R.id.msg);
            time = (TextView) view.findViewById(R.id.time);
            deleteHolder = (ViewGroup)view.findViewById(R.id.holder);
            wifecall = (TextView) view.findViewById(R.id.delete);
            call = (TextView) view.findViewById(R.id.call);
            sendmsg = (TextView) view.findViewById(R.id.sendmsg);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
       position1=position;
    }

    @Override
    public void onSlide(View view, int status) {
        if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
            mLastSlideViewWithStatusOn.shrink();
        }

        if (status == SLIDE_STATUS_ON) {
            mLastSlideViewWithStatusOn = (SlideView) view;
        }
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

        int position = mListView.getPositionForView(v);
		  if (v.getId() == R.id.delete) {
	            Log.e(TAG, "delete" + v);
	        }
		  if (v.getId() == R.id.call) {
			 
			    Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri  
			            .parse("tel:" +mContactsName.get(position)));  
			        startActivity(dialIntent);  
	        }
		  if (v.getId() == R.id.sendmsg) {

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri
			            .parse("smsto:" +mContactsName.get(position)));
	            startActivity(intent);  
	        }
	}

   

}
