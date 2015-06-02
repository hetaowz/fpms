package cn.beginsoft.fpmsapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpResponseHandler;
import org.beginsoft.common.RequestURL;

import org.beginsoft.fpmsapp.base.BaseActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;

import android.widget.Spinner;


import org.beginsoft.vo.MassQus;
import org.beginsoft.vo.QualityProduct;
import org.beginsoft.vo.Reprocess;


public class RejectActivity extends BaseActivity {
	private Context context=null;
	private ListView mListView=null;
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    private TextView textTotalNum;
    private TextView textCustomerName;
    private TextView textProductName;
    private TextView textProductType;
    private TextView textCustomerNum;
    private TextView textReprocessEmployee;
    private TextView textProcessPrice;
    private TextView textSelfNum;
    private TextView textRejectReason1;
    private TextView textRejectReason2;
    private Spinner spinnerReprocess;


    private Button buttonSelect;
    private Button buttonClear;
    private Button buttonConfirm;
    private Button buttonCancel;



    private QualityProduct qualityProduct;
    List<Reprocess> reprocessList;
    private Handler handler;




    @SuppressWarnings("unchecked")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject);
        initDate();
        initView();
        initEvent();
       
    }

    private void initEvent() {
        //新建驳回dialog
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent=new Intent(RejectActivity.this,RejectReasonActivity.class);
            	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            	startActivityForResult(intent, 1);
            }
        });

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0){
                    ReprocessAdapter reprocessAdapter =new ReprocessAdapter(RejectActivity.this);
                    spinnerReprocess.setAdapter(reprocessAdapter);
                }
            }
        };

    }


    @SuppressWarnings("unchecked")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    		ArrayList<MassQus> mList =  (ArrayList<MassQus>) data.getSerializableExtra("massList");
    		for(int i=0;i<mList.size();i++){  
    			MassQus mass = mList.get(i);  
    	         Log.i("RESULT", "GET=====" + mass.getMassQus() + "," + mass.getMonly());  
    	     }  		
	}

	private void initDate() {

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        qualityProduct= (QualityProduct) bundle.get("qualityProduct");
        aSyncHttpClient.post(RequestURL.BASEURL+RequestURL.REPROCESS, params,new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(String content)
            {
                if(!"false".equals(content.trim())){
                    JSONObject jsonObject= JSON.parseObject(content);
                    JSONArray jsonArray=jsonObject.getJSONArray("list");
                    reprocessList=new ArrayList<Reprocess>();
                    for(int i=0;i<jsonArray.size();i++){
                        JSONObject object=jsonArray.getJSONObject(i);
                        Reprocess reprocess=new Reprocess();
                        reprocess.setBeforeProcessId(object.getString("beforeProcessId"));
                        reprocess.setBeforeProcessName(object.getString("beforeProcessName"));
                        reprocessList.add(reprocess);
                        handler.sendEmptyMessage(0);
                        Log.e("reprocessList",reprocess.getBeforeProcessName());
                }
                }

            }

            @Override
            public void onFailure(Throwable error)
            {
                Toast.makeText(context, "网络访问异常，检测是否开启网络", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        //驳回管理
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonClear = (Button) findViewById(R.id.button_clear);
        buttonConfirm = (Button) findViewById(R.id.button_confirm);
        buttonSelect = (Button) findViewById(R.id.button_select);
        textTotalNum = (TextView) findViewById(R.id.text_total_counter);
        textCustomerName = (TextView) findViewById(R.id.text_customer_name);
        textProductName = (TextView) findViewById(R.id.text_product_name);
        textProductType = (TextView) findViewById(R.id.text_product_type);
        textCustomerNum = (TextView) findViewById(R.id.text_customer_num);
        textReprocessEmployee = (TextView) findViewById(R.id.text_re_process_employee);
        textProcessPrice = (TextView) findViewById(R.id.text_process_price);
        textSelfNum = (TextView) findViewById(R.id.text_self_num);
        textRejectReason1 = (TextView) findViewById(R.id.text_reject_reason1);
        textRejectReason2 = (TextView) findViewById(R.id.text_reject_reason2);
        //返工工序
        spinnerReprocess = (Spinner) findViewById(R.id.spinner_re_process);


        textTotalNum.setText(qualityProduct.getAllNumber());
        textCustomerName.setText(qualityProduct.getCustomerName());
        textProductName.setText(qualityProduct.getGoodsName());
        textProductType.setText(qualityProduct.getSofaModel());
        textCustomerNum.setText(qualityProduct.getEmployeeNumber());
        textReprocessEmployee.setText(qualityProduct.getProcePersonName());
        textProcessPrice.setText(qualityProduct.getProceQuantity());
        textSelfNum.setText(qualityProduct.getThreeProceNum());
        //驳回原因


    }

    class ReprocessAdapter extends  BaseAdapter{
        LayoutInflater mInflater;
        Context context;

        public ReprocessAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return reprocessList.size();
        }

        @Override
        public Object getItem(int position) {
            return reprocessList.get(position);
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
                mInflater= LayoutInflater.from(context);
                convertView=mInflater.inflate(R.layout.spinner_reprocess, null);
                viewHolder.textView=(TextView) convertView.findViewById(R.id.text_reprocess_name);
                convertView.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder) convertView.getTag();
            }

            //设置数据
            viewHolder.textView.setText(reprocessList.get(position).getBeforeProcessName());
            return convertView;
        }

        class ViewHolder{
            public TextView textView;
        }
    }


}
