package com.example.administrator.jyly;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class mesActivity extends AppCompatActivity {
private String[]data={"您有一条新消息","圣诞快乐","欢迎登入"};
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes);
        Toast.makeText(mesActivity.this, "暂无新消息", Toast.LENGTH_SHORT).show();
        ListView listView = (ListView) findViewById(R.id.listView_mes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mesActivity.this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);


        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout2);
        swipeRefreshLayout.setColorSchemeResources(R.color.tab_checked);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });


    }

    private void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mesActivity.this, "暂无新消息", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();


    }
}
