package com.fanfan.dailypaper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.Calendar;

public class HomeActivity extends BaseActivity {
    private CalendarView mCalendarView;
    private Calendar mCalendar;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private Button mbtn_daily,mbtn_dailys;

    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mbtn_daily= (Button) findViewById(R.id.btn_daily);
        mbtn_dailys= (Button) findViewById(R.id.btn_dailys);
        mCalendar=Calendar.getInstance();
        year=mCalendar.get(Calendar.YEAR);
        month=mCalendar.get(Calendar.MONTH);
        day=mCalendar.get(Calendar.DAY_OF_MONTH);
        hour=mCalendar.get(Calendar.HOUR_OF_DAY);
        minute=mCalendar.get(Calendar.MINUTE);
        setTitle(year+"-"+month+"-"+day+"-"+hour+"-"+minute);
        mCalendarView= (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "年" +( month+1 )+ "月" + dayOfMonth + "日";
            }
        });
       mbtn_daily.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=getIntent();

               Bundle bundle=new Bundle();
               bundle.putString("date",date);
               bundle.putString("author",intent.getStringExtra("author"));
               startActivity(daily2Activity.class,bundle);
           }
       });
        mbtn_dailys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(HomeActivity.this,DailyActivity.class);
                startActivity(DailyActivity.class);
            }
        });
    }
}
