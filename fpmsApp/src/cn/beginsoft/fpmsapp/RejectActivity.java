package cn.beginsoft.fpmsapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class RejectActivity extends Activity {
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

    }

    private void initView() {
        //驳回管理
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonClear = (Button) findViewById(R.id.button_clear);
        buttonConfirm = (Button) findViewById(R.id.button_confirm);
        buttonSelect = (Button) findViewById(R.id.button_select);


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
