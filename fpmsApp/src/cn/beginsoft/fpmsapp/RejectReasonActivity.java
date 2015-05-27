package cn.beginsoft.fpmsapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import org.beginsoft.common.ActiveUser;
import org.beginsoft.common.EProceState;
import org.beginsoft.common.RequestURL;
import org.beginsoft.fpmsapp.base.BaseActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.beginsoft.fpmsapp.LinkQualityActivity.DataAdapter.ViewHolder;

import com.ta.util.http.AsyncHttpResponseHandler;

import android.app.AlertDialog;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RejectReasonActivity extends BaseActivity {
	private Context context=null;
	private ListView mListView=null;
	private TextView mTotalNum;
	private TextView mCurrentNum;
	private Button mPreview;
	private Button mNext;
	private Button btn_ok;
	private Button btn_cancel;
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
   
    /*此处json用于获取质量金额列表*/
	private JSONObject jsonObject=null;
	private JSONArray jsonArray=null;
	private static final String TAG="SOFT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject_reason);
        this.context=this;
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        


    }

    private void initData() {
    	dataFill();
    }

    private void initView() {
        //驳回管理
    	mListView=(ListView)findViewById(R.id.list_reject_type);
    	View view=LayoutInflater.from(this).inflate(R.layout.list_reject_type_item, null);
    	view.findViewById(R.id.check_box).setVisibility(View.INVISIBLE);
    	mListView.addHeaderView(view);
    	mTotalNum=(TextView) findViewById(R.id.total_number);
    	mCurrentNum=(TextView) findViewById(R.id.current_number);
    	mPreview=(Button) findViewById(R.id.btn_preview);
    	mNext=(Button) findViewById(R.id.btn_next);
    	btn_ok=(Button) findViewById(R.id.reject_ok);
    	btn_cancel=(Button) findViewById(R.id.reject_cancel);
    	
    }

    /**
     * 数据初始化填充驳回原因listview
     */
    private void dataFill() {
    	//异步访问
    	
    	aSyncHttpClient.post(RequestURL.BASEURL+RequestURL.QUALITY, params,new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(String content)
            {
            	try {
	            	jsonObject=new JSONObject(content);
					jsonArray=jsonObject.getJSONArray("list");
					Log.i(TAG, jsonArray.toString());
            	} catch (JSONException e) {
    				e.printStackTrace();
    			}
            	setListView();
            }

			@Override
            public void onFailure(Throwable error)
            {
            	Toast.makeText(context,"网络访问异常，检测是否开启网络", Toast.LENGTH_SHORT).show(); 
            }
        });
    }

    private void setListView() {
		 if(jsonArray!=null){
			    JSONObject temp;
			    Map<String, String> map;
			    for(int position=0;position<jsonArray.length();position++){
					try {
						temp = (JSONObject)jsonArray.get(position);
					    map = new HashMap<String, String>(); 
					    map.put("gdamage", temp.getString("gdamage"));
		                map.put("massQus", temp.getString("massQus"));
					    map.put("monly", temp.getString("monly"));
					    this.list.add(map);
					} catch (JSONException e) {
						e.printStackTrace();
					}
			    	}
				 }else{
			    		Toast.makeText(context,"暂无数据", Toast.LENGTH_SHORT).show(); 
			    }
		    Log.i(TAG, list.toString());
		 	MyRejectAdapter simpleAdapter=new MyRejectAdapter(this);
		 	this.mListView.setAdapter(simpleAdapter);
	}
    
    private class MyRejectAdapter extends BaseAdapter{
    	LayoutInflater mInflater;
		Context context;
		public MyRejectAdapter(Context context){
			super();
			this.context=context;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list == null ? 0 : list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list == null ? null : list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return list == null ? 0 : position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder=null;
			if(convertView==null){
				viewHolder=new ViewHolder();
				mInflater=LayoutInflater.from(context);
				convertView=mInflater.inflate(R.layout.list_reject_type_item, null);
				
				viewHolder.textNum = (TextView) convertView.findViewById(R.id.text_typeNum);
				viewHolder.checkBox=(CheckBox) convertView.findViewById(R.id.check_box);
				viewHolder.textTypeName = (TextView) convertView.findViewById(R.id.text_typeName);
				viewHolder.textMassQus = (TextView) convertView.findViewById(R.id.quality_question);
				viewHolder.textMoney = (TextView) convertView.findViewById(R.id.text_money);

				convertView.setTag(viewHolder);
			}else{
				viewHolder=(ViewHolder) convertView.getTag();
			}
		    
		    JSONObject temp ;
			    try {
			    	temp = (JSONObject)jsonArray.get(position);
			    	viewHolder.textNum.setText(position+1+"");
			    	viewHolder.textTypeName.setText(temp.getString("gdamage"));
			    	
			    	viewHolder.textMassQus.setText(temp.getString("massQus"));
			    	viewHolder.textMoney.setText(temp.getString("monly"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
		    
    	    return convertView;
		}
    	
		 class ViewHolder{
				TextView textNum;
				CheckBox checkBox;
				TextView textTypeName;
				TextView textMassQus;
				TextView textMoney;
			}
    }

}
