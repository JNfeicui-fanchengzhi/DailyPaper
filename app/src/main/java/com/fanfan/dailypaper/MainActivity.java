package com.fanfan.dailypaper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "dsjd";
    private Button mbtn_Login,mbtn_register;
    private EditText met_name, met_pwd;
    private List<Map<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        met_name= (EditText) findViewById(R.id.et_name);
        met_pwd= (EditText) findViewById(R.id.et_pwd);
        mbtn_Login= (Button) findViewById(R.id.btn_Login);
        mbtn_register= (Button) findViewById(R.id.btn_register);
        mbtn_register.setOnClickListener(this);
        mbtn_Login.setOnClickListener(this);
        list = new GetData(this).getData("select * from user_");
        Intent intent=getIntent();
        String name1=intent.getStringExtra("user_name");
        Log.d(TAG, "onClick:"+name1);
        String pwd1=intent.getStringExtra("user_password");
        Log.d(TAG, "onClick:"+pwd1);
        met_name.setText(name1);
        met_pwd.setText(pwd1);

//        // 校验文本框内容
//        String name=met_text.getText().toString().trim();
//        //判断是否为空
//        if (TextUtils.isEmpty(name)){
//            Toast.makeText(MainActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//       //保存名字
//        String savename= SUPUtils.getString(getApplicationContext(), Utils.USERNAME);
//        //判断相等
//        if (!savename.equals(name)){
//            Toast.makeText(MainActivity.this, "用户名不一致，请重新输入", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        SUPUtils.putString(getApplicationContext(),Utils.USERNAME,savename);
//        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
//        startActivity(intent);
//        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Login:
                boolean bl=false;
                Log.d(TAG, "onClick:建设现代技术 ");
                for (Map<String,String> map: list) {
                    String name=met_name.getText().toString();
                    String pwds1=met_pwd.getText().toString();
                    Log.d(TAG, "onClick:建设现代技术12 "+name+pwds1);
                    String str=map.get("user_name");
                    String str1=map.get("user_password");
                    Log.d(TAG, "onClick:建设现代技术 13"+str+str1);
                    if (name.equals(str)&&pwds1.equals(str1)) {
                        Log.d(TAG, "onClick:建设现代技术14 ");
                        Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onClick:建设现代技术 15");
                        Bundle bundle=new Bundle();
                        bundle.putString("author",name);
                        startActivity(HomeActivity.class,bundle);
                        bl=true;
                        break;
                    }
                }
                if (bl==false){
                    Toast.makeText(getApplicationContext(), "用户名不存在,请注册", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_register:
                startActivity(RegisterActivity.class);
                break;
        }
    }
}
