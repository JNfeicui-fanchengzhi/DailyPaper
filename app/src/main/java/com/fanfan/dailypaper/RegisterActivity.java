package com.fanfan.dailypaper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.fanfan.dailypaper.utils.SUPUtils;
import com.fanfan.dailypaper.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    public static final String TAG = "gjh";
    private EditText met_name;
    private RadioButton mradioButton, mradioButton2;
    private Button mbtn_cancel, mbtn_log;

    private Spinner mSpinner1, mSpinner2, mSpinner3;
    private List<String> mList;
    private ArrayAdapter<String> mAdapter;
    private RadioGroup mrg_btn;

    String sGendr = "";
    String sGendr1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        met_name = (EditText) findViewById(R.id.et_name);
        mradioButton = (RadioButton) findViewById(R.id.radioButton);
        mradioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        mbtn_cancel = (Button) findViewById(R.id.btn_cancel);
        mbtn_log = (Button) findViewById(R.id.btn_log);
        mrg_btn = (RadioGroup) findViewById(R.id.rg_btn);
        mrg_btn.setOnCheckedChangeListener(this);

        mbtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mbtn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetupDialog();
            }
        });
    }

    private void showSetupDialog() {
        // 校验文本框内容
        String name = met_name.getText().toString().trim();
        //判断是否为空
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //保存名字
        String savename = SUPUtils.getString(getApplicationContext(), Utils.USERNAME);
        //判断相等
        if (name.equals(savename)) {
            Toast.makeText(getApplicationContext(), "用户名已存在，请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }
        SUPUtils.putString(getApplicationContext(), Utils.USERNAME, name);
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    private void initView() {
        mSpinner1 = (Spinner) findViewById(R.id.spinner1);
        mSpinner2 = (Spinner) findViewById(R.id.spinner2);
        mSpinner3 = (Spinner) findViewById(R.id.spinner3);
        initView1();
        initView2();
        initView3();
    }

    private void initView3() {
        mList = new ArrayList<String>();
        for (int i = 0; i < 32; i++) {
            mList.add(i + "日");
        }
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mList);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner3.setAdapter(mAdapter);
    }

    private void initView2() {
        mList = new ArrayList<String>();
        for (int i = 0; i < 13; i++) {
            mList.add(i + "月");
        }
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mList);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner2.setAdapter(mAdapter);
    }

    private void initView1() {
        mList = new ArrayList<String>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int begin = currentYear - 30;
        int end = currentYear + 10;
        for (int i = end; i > begin; i--) {
            mList.add(i + "年");

        }
        Log.d(TAG, "initView1: " + mList);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mList);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner1.setAdapter(mAdapter);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.radioButton:
                sGendr = mradioButton.getText().toString();
                SUPUtils.putString(getApplicationContext(),Utils.BOY,sGendr);
                break;
            case R.id.radioButton2:
                sGendr1 = mradioButton2.getText().toString();
                SUPUtils.putString(getApplicationContext(),Utils.WOMAN,sGendr1);
                break;
        }
    }
}

