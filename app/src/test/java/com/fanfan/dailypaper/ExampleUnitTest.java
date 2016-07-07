package com.fanfan.dailypaper;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test(){
        Calendar mCalendar=Calendar.getInstance();
        int year=mCalendar.get(Calendar.YEAR);
        int month=mCalendar.get(Calendar.MONTH);
        int day=mCalendar.get(Calendar.DAY_OF_MONTH);
        int hour=mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute=mCalendar.get(Calendar.MINUTE);
        System.out.println(year+"-"+month+"-"+day+"-"+hour+"-"+minute);
    }
}