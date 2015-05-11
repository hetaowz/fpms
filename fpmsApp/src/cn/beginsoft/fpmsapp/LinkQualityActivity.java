package cn.beginsoft.fpmsapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import org.beginsoft.fpmsapp.base.BaseActivity;
import org.beginsoft.vo.Product;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class LinkQualityActivity extends BaseActivity {

	private ListView listView;
	List<Product> products;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link_quality);
		
		initView();
		initData();
		initEvent();
		menuInit();
		
	}
	
    private void menuInit(){
         menuListener();
         //设置菜单选中颜色
         TextView bnt_userinfo=(TextView)findViewById(R.id.btn_link_quality);
         bnt_userinfo.setTextColor(this.getResources().getColor(R.color.green)); 
         LinearLayout ll_link_quality=(LinearLayout)findViewById(R.id.ll_link_quality);
         ll_link_quality.setBackgroundColor(this.getResources().getColor(R.color.jblue));
    }
    
	@Override
	public void menuListener() {
		// TODO Auto-generated method stub
		super.menuListener();
	}



	private void initView() {
		listView=(ListView) findViewById(R.id.list_link_quality);
		
		
	}
	

	
	private void initData() {
		Product product0=new Product("serialNum", "customerName", "productName", "productVersion", "employeeNum", "employeeName", "processPrice", "selfNum");
		Product product1=new Product("serialNum", "customerName", "productName", "productVersion", "employeeNum", "employeeName", "processPrice", "selfNum");
		Product product2=new Product("serialNum", "customerName", "productName", "productVersion", "employeeNum", "employeeName", "processPrice", "selfNum");
		Product product3=new Product("serialNum", "customerName", "productName", "productVersion", "employeeNum", "employeeName", "processPrice", "selfNum");
	    products=new ArrayList<Product>();
		products.add(product0);
		products.add(product1);
		products.add(product2);
		products.add(product3);
	}





	private void initEvent() {
		DataAdapter dataAdapter=new DataAdapter(this);
		listView.setAdapter(dataAdapter);
		
	}
	
	
	
	class DataAdapter extends BaseAdapter{
		LayoutInflater mInflater;
		Context context;
		

		public DataAdapter(Context context) {
			super();
			this.context = context;
		}

		@Override
		public int getCount() {
			return products.size();
		}

		@Override
		public Object getItem(int position) {
			return products.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		    ViewHolder viewHolder=null;
			if(convertView==null){
				viewHolder=new ViewHolder();
				mInflater=LayoutInflater.from(context);
				convertView=mInflater.inflate(R.layout.list_link_quality, null);
				viewHolder.textNum=(TextView) convertView.findViewById(R.id.text_num);
				viewHolder.textData=(TextView) convertView.findViewById(R.id.text_data);
				viewHolder.buttonOK=(Button) convertView.findViewById(R.id.button_ok);
				viewHolder.buttonReject=(Button) convertView.findViewById(R.id.button_reject);
				viewHolder.buttonReject.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent=new Intent();
						intent.setClass(LinkQualityActivity.this,RejectActivity.class);
						startActivity(intent);
					}
				});
				
				convertView.setTag(viewHolder);
			}else{
				viewHolder=(ViewHolder) convertView.getTag();
			}
			
			//设置数据
			Product product=products.get(position);
			viewHolder.textNum.setText(position+"");
			viewHolder.textData.setText(product.getCustomerName());
						
			return convertView;
		}
		
		
	 class ViewHolder{
			TextView textNum;
			TextView textData;
			Button buttonOK;
			Button buttonReject;
		}
		
	}











	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.link_quality, menu);
		return true;
	}

}
