package cn.beginsoft.fpmsapp;

import java.util.ArrayList;
import java.util.List;

import org.beginsoft.vo.Product;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class LinkQualityActivity extends Activity {

	private ListView listView;
	List<Product> products;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link_quality);
		
		initView();
		initData();
		initEvent();
		
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
			if(viewHolder==null){
				viewHolder=new ViewHolder();
				mInflater=LayoutInflater.from(context);
				convertView=mInflater.inflate(R.layout.list_link_quality, null);
				viewHolder.textNum=(TextView) convertView.findViewById(R.id.text_num);
				viewHolder.textData=(TextView) convertView.findViewById(R.id.text_data);
				viewHolder.buttonOK=(Button) convertView.findViewById(R.id.button_ok);
				viewHolder.buttonReject=(Button) convertView.findViewById(R.id.button_reject);
			    convertView.setTag(viewHolder);
				
			}else{
				viewHolder=(ViewHolder) convertView.getTag();
			}
			
			//…Ë÷√ ˝æ›
			Product product=products.get(position);
			viewHolder.textNum.setText(position);
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
