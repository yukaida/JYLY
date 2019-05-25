package com.example.administrator.jyly.MyPublish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.jyly.R;

public class MyPublishActivity extends AppCompatActivity {
    private String[]data={"订单1","订单2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_publish);

        ListView listView_order = (ListView) findViewById(R.id.list_order);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MyPublishActivity.this, android.R.layout.simple_list_item_1, data);
        listView_order.setAdapter(adapter);



    }
}
