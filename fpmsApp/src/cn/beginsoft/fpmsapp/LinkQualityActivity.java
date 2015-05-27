package cn.beginsoft.fpmsapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpResponseHandler;
import org.beginsoft.common.RequestURL;
import org.beginsoft.fpmsapp.base.BaseActivity;
import org.beginsoft.vo.Product;

import android.os.Bundle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import org.beginsoft.vo.QualityProduct;

public class LinkQualityActivity extends BaseActivity {

	private ListView listView;
	List<QualityProduct> qualityProductList;
	public Handler handler;
	private AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();


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

		mAsyncHttpClient.post(RequestURL.BASEURL + RequestURL.LINKQUALITY, null, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String content) {
				if (!"false".equals(content.trim())) {
					Log.e("content", content);
					JSONObject jsonObject = JSON.parseObject(content);
					JSONArray jsonArray=jsonObject.getJSONArray("list");
					qualityProductList=new ArrayList<QualityProduct>();

					for(int i=0;i<jsonArray.size();i++){

						JSONObject object=jsonArray.getJSONObject(i);
						QualityProduct qualityProduct=new QualityProduct();
						qualityProduct.setAllNumber(object.getString("allNumber"));
						qualityProduct.setCustomerMark(object.getString("customerMark"));
						qualityProduct.setEmployeeNumber(object.getString("employeeNumber"));
						qualityProduct.setFlowLine(object.getString("flowLine"));
						qualityProduct.setGoodsName(object.getString("goodsName"));
						qualityProduct.setProcePersonName(object.getString("procePersonName"));
						qualityProduct.setProceQuantity(object.getString("proceQuantity"));
						qualityProduct.setProceState(object.getString("proceState"));
						qualityProduct.setSofaModel(object.getString("sofaModel"));
						qualityProduct.setSofaName(object.getString("sofaName"));
						qualityProduct.setZstatu(object.getString("zstatu"));
						qualityProduct.setWorkShop(object.getString("workShop"));
						qualityProduct.setThreeProceNum(object.getString("threeProceNum"));
						qualityProduct.setTwoProceName(object.getString("twoProceName"));
						qualityProductList.add(qualityProduct);
						Log.e("qualityProductList",qualityProduct.getAllNumber());
						handler.sendEmptyMessage(0);

					}

				}

			}

			@Override
			public void onFailure(Throwable error) {
				Toast.makeText(LinkQualityActivity.this, "网络访问异常，检测是否开启网络", Toast.LENGTH_SHORT).show();
			}
		});

	}





	private void initEvent() {

		handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if(msg.what==0){
					DataAdapter dataAdapter=new DataAdapter(LinkQualityActivity.this);
					listView.setAdapter(dataAdapter);
				}
			}
		};

		
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
			return qualityProductList.size();
		}

		@Override
		public Object getItem(int position) {
			return qualityProductList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
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
						Bundle bundle=new Bundle();
						bundle.putSerializable("qualityProduct",qualityProductList.get(position));
						intent.putExtras(bundle);
						intent.setClass(LinkQualityActivity.this,RejectActivity.class);
						
						startActivity(intent);
					}
				});
				
				convertView.setTag(viewHolder);
			}else{
				viewHolder=(ViewHolder) convertView.getTag();
			}
			
			//设置数据
			QualityProduct qualityProduct=qualityProductList.get(position);
			viewHolder.textNum.setText(position+"");
			viewHolder.textData.setText(qualityProduct.getAllNumber());
						
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
