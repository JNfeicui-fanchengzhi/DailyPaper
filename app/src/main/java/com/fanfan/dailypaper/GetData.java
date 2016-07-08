package com.fanfan.dailypaper;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
public class GetData {
    public static final String TAG="djs";
    Context context;
    public GetData(Context context){
        Log.d(TAG, "getData:度假卧室的就看到 ");
        this.context=context;
    }
    public List<Map<String,String>> getData(String table){

        String path="data/data/com.fanfan.dailypaper/test.txt";
        File file=new File(path);
        Log.d(TAG, "getData: "+file);

        if (!file.exists()){
            String sourceFileName="test.txt";
            String targetPath="data/data/com.fanfan.dailypaper/";
            String targetFileName="test.txt";


            AssetManager assetManager=context.getAssets();
            File targetFilePath=new File(targetPath);

            if (!targetFilePath.exists()){
                targetFilePath.mkdirs();
            }

            File targetFile=new File(targetFilePath,targetFileName);
            Log.d(TAG, "getData: "+targetFile);

            try {
                targetFile.createNewFile();

                InputStream is=assetManager.open(sourceFileName);
                OutputStream os=new FileOutputStream(targetFile);

                byte[] buffer=new byte[1024];

                int len=0;

                while ((len=is.read(buffer))!=-1){
                    os.write(buffer,0,len);
                    Log.d(TAG, "getData: "+os);
                }

                os.flush();
                os.close();
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(file,null);
        Cursor cursor=db.rawQuery(table,null);

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
        return list;
    }
}
