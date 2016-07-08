package com.fanfan.dailypaper;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "gjh";
    private EditText met_names,met_birthday,met_pwds;
    private CheckBox mcheckBox1;
    private CheckBox mcheckBox2;

    private Button mbtn_cancel, mbtn_log;
    private List<Map<String, String>> list;
    private int i;

//    private LinearLayout mlayout;
//    private RadioButton mradioButton, mradioButton2;
//    private Spinner mSpinner1, mSpinner2, mSpinner3;
//    private List<String> mList;
//    private ArrayAdapter<String> mAdapter;
//    private RadioGroup mrg_btn;
//    String sGendr = "";
//    String sGendr1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        initView();
        met_names = (EditText) findViewById(R.id.et_names);
        met_birthday = (EditText) findViewById(R.id.et_birthday);
        met_pwds = (EditText) findViewById(R.id.et_pwds);
        mcheckBox1 = (CheckBox) findViewById(R.id.checkBox1);
        mcheckBox2 = (CheckBox) findViewById(R.id.checkBox2);
        mbtn_cancel = (Button) findViewById(R.id.btn_cancel);
        mbtn_log = (Button) findViewById(R.id.btn_log);
        list = new GetData(this).getData("select * from user_");
        mbtn_cancel.setOnClickListener(this);
        mbtn_log.setOnClickListener(this);
        mcheckBox1.setOnClickListener(this);
        mcheckBox2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        CheckBox cb=null;
        switch (v.getId()){
            case R.id.btn_cancel:
                startActivity(MainActivity.class);
                break;
            case R.id.btn_log:
                String name=met_names.getText().toString();
                String birthday=met_birthday.getText().toString();
                String pwds=met_pwds.getText().toString();
                boolean bl=false;
                for (Map<String, String> map : list) {
                    String str=map.get("user_name");
                    if (name.equals(str)) {
                        Toast.makeText(getApplicationContext(), "该用户已存在", Toast.LENGTH_SHORT).show();
                        bl=true;
                        break;
                    }
                }
                if (bl==false){
                    String path = "data/data/com.fanfan.dailypaper/test.txt";
                    SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path, null);
                    if (name.equals("")){
                        Toast.makeText(getApplicationContext(),"用户名不能为空",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    db.execSQL("insert into user_ (user_name,user_sex,user_birthday,user_password) VALUES (?,?, ?, ?)",
                            new Object[]{name, i,birthday,pwds});
                    met_pwds.setText(pwds);
                    Bundle bundle=new Bundle();
                    bundle.putString("user_name",name);
                    bundle.putString("user_password",pwds);
                    startActivity(MainActivity.class,bundle);
                    Toast.makeText(getApplicationContext(),"注册成功！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.checkBox1:
                cb= (CheckBox) v;
                cb.setChecked(cb.isChecked());
                mcheckBox2.setChecked(!cb.isChecked());
                if (cb.isChecked()){
                    i=2;
                }
                break;
            case R.id.checkBox2:
                cb= (CheckBox) v;
                cb.setChecked(cb.isChecked());
                mcheckBox1.setChecked(!cb.isChecked());
                if (cb.isChecked()){
                    i=1;
                }
                break;
        }
    }

//    private void showSetupDialog() {
//        Log.d(TAG, "onClick: 动物和虎队5");
//        // 校验文本框内容
//        String name = met_name.getText().toString();
////        String birthday = mlayout.toString();
//        boolean bl=false;
//        for (Map<String, String> map : list) {
//            String str = map.get("user_name");
//            Log.d(TAG, "onClick: 动物和虎队我还得");
//            if (name.equals(str)) {
//                Toast.makeText(RegisterActivity.this, "用户已存在", Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onClick: 动物和虎队我还得1");
//                bl = true;
//                break;
//            }
//        }
//        if (bl = false) {
//            String pash = "data/data/com.fanfan.dailypaper/test";
//            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(pash, null);
//            Log.d(TAG, "onClick: 动物和虎队");
//            if (name.equals("")) {
//                Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onClick: 动物和虎队1");
//
//            }
//            db.execSQL("insert into user_ (user_name,user_sex,user_birthday) values (?,?,?)",
//                    new Object[]{name, null,null});
//            Log.d(TAG, "onClick: 动物和虎队2");
//            Bundle bundle=new Bundle();
//            bundle.putString("user_name",name);
//            startActivity(MainActivity.class,bundle);
//            Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
//        }

//        //判断是否为空
//        if (TextUtils.isEmpty(name)) {
//            Toast.makeText(getApplicationContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        //保存名字
//        String savename = SUPUtils.getString(getApplicationContext(), Utils.USERNAME);
//        //判断相等
//        if (name.equals(savename)) {
//            Toast.makeText(getApplicationContext(), "用户名已存在，请重新输入", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        SUPUtils.putString(getApplicationContext(), Utils.USERNAME, name);
//        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
//        startActivity(intent);
//        finish();
//    }
//    private void initView() {
//        mSpinner1 = (Spinner) findViewById(R.id.spinner1);
//        mSpinner2 = (Spinner) findViewById(R.id.spinner2);
//        mSpinner3 = (Spinner) findViewById(R.id.spinner3);
//        initView1();
//        initView2();
//        initView3();
//    }
//    private void initView3() {
//        mList = new ArrayList<String>();
//        for (int i = 0; i < 32; i++) {
//            mList.add(i + "日");
//        }
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mList);
//        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinner3.setAdapter(mAdapter);
//    }
//
//    private void initView2() {
//        mList = new ArrayList<String>();
//        for (int i = 0; i < 13; i++) {
//            mList.add(i + "月");
//        }
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mList);
//        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinner2.setAdapter(mAdapter);
//    }
//
//    private void initView1() {
//        mList = new ArrayList<String>();
//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//        int begin = currentYear - 30;
//        int end = currentYear + 10;
//        for (int i = end; i > begin; i--) {
//            mList.add(i + "年");
//
//        }
//        Log.d(TAG, "initView1: " + mList);
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mList);
//        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mSpinner1.setAdapter(mAdapter);
//    }

}

