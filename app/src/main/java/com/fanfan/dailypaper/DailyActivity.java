package com.fanfan.dailypaper;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyActivity extends BaseActivity {
    private ListView mListView;
    private List<Map<String,String>> list;
    private Map<String,Boolean> CBmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        mListView= (ListView) findViewById(R.id.lv_item);
        list=new GetData(this).getData("select daily_name from daily_ ");
        CBmap=new HashMap<>();
        for (Map<String,String> map:list){
            CBmap.put(map.get("daily_name"),false);
        }
        mListView.setAdapter(ba);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=new Intent(DailyActivity.this,daily2Activity.class);
                startActivity(daily2Activity.class);
            }
        });
    }
    BaseAdapter ba=new BaseAdapter() {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Map<String,String> map= (Map<String, String>) getItem(position);
             View view=getLayoutInflater().inflate(R.layout.daily_item,null);
            CheckBox cb= (CheckBox) view.findViewById(R.id.cb_each);
            TextView tv= (TextView) view.findViewById(R.id.tv_item);
            cb.setChecked(CBmap.get(map.get("daily_name")));
            tv.setText(map.get("daily_name"));
            return view;
        }
    };
}
