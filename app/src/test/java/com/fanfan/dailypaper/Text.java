package com.fanfan.dailypaper;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/7.
 */
public class Text extends AndroidTestCase {
    @Test
    public void testDB() {

        String path = "data/data/com.fanfan.dailypaper/user_.db";
        File file = new File(path);
        Context context = getContext();
        if (!file.exists()) {
            String sourceFileName = "user_.db";
            String targetPath = "data/data/com.fanfan.dailypaper";
            String targetFileName = "user_.db";

            AssetManager assetManager = context.getAssets();
            File targetFilePath = new File(targetPath);
            if (!targetFilePath.exists()) {
                targetFilePath.mkdirs();
            }
            File targetFile = new File(targetFilePath, targetFileName);
            try {
                targetFile.createNewFile();

                InputStream is = assetManager.open(sourceFileName);
                OutputStream os = new FileOutputStream(targetFile);

                byte[] buffer = new byte[1024];

                int len = 0;

                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer);
                }

                os.flush();
                os.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SQLiteDatabase database= SQLiteDatabase.openOrCreateDatabase(file,null);
        Cursor cursor=database.rawQuery("select * form user_",null);

        List<Map<String,String>> list=new ArrayList<Map<String, String>>();

        while(cursor.moveToNext()){

            Map<String,String> map=new HashMap<String,String>();
            //获取当前游标所在行的列的总数
            int columnCount=cursor.getColumnCount();

            for (int i=0;i<columnCount;i++){
                String columnName=cursor.getColumnName(i);
                int columnIndex=cursor.getColumnIndex(columnName);
                String columnValue=cursor.getString(columnIndex);
                map.put(columnName,columnValue);
            }
            list.add(map);
        }
    }
}
