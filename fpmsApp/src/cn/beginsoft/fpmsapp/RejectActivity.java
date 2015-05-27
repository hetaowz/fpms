package cn.beginsoft.fpmsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
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

public class RejectActivity extends Activity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reject);
        initView();
        initDate();
        initEvent();
    }

    private void initEvent() {
        //新建驳回dialog
        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRejectDialog();

            }
        });


    }

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

    private void createRejectDialog() {
        AlertDialog dialog;
        AlertDialog.Builder builder = null;
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_reject, null);
        builder = new AlertDialog.Builder(RejectActivity.this);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.reject, menu);
        return true;
    }

}
