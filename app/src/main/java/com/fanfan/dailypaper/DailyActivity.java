package com.fanfan.dailypaper;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DailyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        SQLiteDatabase db=openOrCreateDatabase("text",MODE_PRIVATE,null);
        db.execSQL("create table if not exists text()");
    }
}
