package com.fanfan.dailypaper;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class daily2Activity extends BaseActivity {
    private TextView mtv_name;
    private EditText myesterday;
    private EditText met_plan;
    private EditText met_question;
    private Button   mbtn_Preservation;
    private Button   mbtn_Abandon;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily2);
        mtv_name= (TextView) findViewById(R.id.tv_name);
        myesterday= (EditText) findViewById(R.id.et_yesterday);
        met_plan= (EditText) findViewById(R.id.et_plan);
        met_question= (EditText) findViewById(R.id.et_question);
        mbtn_Preservation= (Button) findViewById(R.id.btn_Preservation);
        mbtn_Abandon= (Button) findViewById(R.id.btn_Abandon);
//        Intent intent=getIntent();
//        str=intent.getStringExtra("author").concat(intent.getStringExtra("date"));
//        mtv_name.setText(str);
        mbtn_Preservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1=myesterday.getText().toString();
                String str2=met_plan.getText().toString();
                String str3=met_question.getText().toString();

                String path = "data/data/com.fanfan.dailypaper/test.txt";
                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path, null);
                db.execSQL("INSERT INTO daily_ (daily_name,yesterday_task,today_pl,question_) VALUES (?, ?, ?, ?)",
                        new Object[]{str,str1, str2,str3});
                Toast.makeText(getApplicationContext(),"保存成功", Toast.LENGTH_SHORT).show();
            }
        });
        mbtn_Abandon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String strSql = " delete from classlist  where id=? ";
                String path = "data/data/com.fanfan.dailypaper/test.txt";
                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(path, null);
                 db.execSQL("DELETE FROM daily_");
            }
        });
    }
}
