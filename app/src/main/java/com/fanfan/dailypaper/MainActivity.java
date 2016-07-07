package com.fanfan.dailypaper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fanfan.dailypaper.utils.SUPUtils;
import com.fanfan.dailypaper.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private Button mbtn_Login,mbtn_register;
    private EditText met_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        met_text= (EditText) findViewById(R.id.et_text);
        mbtn_Login= (Button) findViewById(R.id.btn_Login);
        mbtn_register= (Button) findViewById(R.id.btn_register);
        mbtn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEnterDialog();
            }
        });
        mbtn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showEnterDialog() {
        // 校验文本框内容
        String name=met_text.getText().toString().trim();
        //判断是否为空
        if (TextUtils.isEmpty(name)){
            Toast.makeText(MainActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
       //保存名字
        String savename= SUPUtils.getString(getApplicationContext(), Utils.USERNAME);
        //判断相等
        if (!savename.equals(name)){
            Toast.makeText(MainActivity.this, "用户名不一致，请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }
        SUPUtils.putString(getApplicationContext(),Utils.USERNAME,savename);
        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
