package com.fanfan.dailypaper.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/7/6.
 */
public class SUPUtils {
    private static SharedPreferences mPreferences;
    private static final String NAME="ViewPager";
    //保存数据
    public static void putString(Context context,String key,String value){
        SharedPreferences sp=getPreferences(context);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static SharedPreferences getPreferences(Context context) {
        if (mPreferences==null){
            mPreferences=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        }
        return mPreferences;
    }

    public static String getString(Context context,String key) {
        return getString(context, key,null);
    }

    private static String getString(Context context, String key, String defValue) {
        SharedPreferences sp=getPreferences(context);
        return sp.getString(key, defValue);
    }
}
