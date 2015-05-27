package cn.beginsoft.fpmsapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpResponseHandler;
import org.beginsoft.common.RequestURL;
import org.beginsoft.vo.QualityProduct;


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
    /*此处json用于获取质量金额列表*/
	private JSONObject jsonObject=null;
	private JSONArray jsonArray=null;
	private static final String TAG="SOFT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject);
        this.context=this;
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        //新建驳回dialog
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            	Intent intent=new Intent(RejectActivity.this,RejectReasonActivity.class);
            	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            	startActivity(intent);
            }
        });


    }


    private void initData() {
    	

    private void initDate() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        QualityProduct qualityProduct= (QualityProduct) bundle.get("qualityProduct");




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
        spinnerReprocess = (Spinner) findViewById(R.id.spinner_re_process);

    }


}
