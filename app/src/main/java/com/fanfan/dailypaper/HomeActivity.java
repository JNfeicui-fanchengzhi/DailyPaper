package com.fanfan.dailypaper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    private CalendarView mCalendarView;
    private Calendar mCalendar;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
                setTitle(year+"-"+month+"-"+dayOfMonth);
                Intent intent=new Intent(HomeActivity.this,DailyActivity.class);
                startActivity(intent);
            }
        });

    }
}
