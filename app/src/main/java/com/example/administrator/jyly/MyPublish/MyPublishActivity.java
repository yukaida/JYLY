package com.example.administrator.jyly.MyPublish;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.jyly.Bomb.Order;
import com.example.administrator.jyly.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MyPublishActivity extends AppCompatActivity {
    private List<String> orderData = new ArrayList<>();

    public int orderSum=0;
    private static final String TAG = "MyPublishActivity";
    private TextView textView_orderSum;
    private ListView listView_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_publish);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //设置沉浸式状态栏


        textView_orderSum = findViewById(R.id.textView_orderSum);
        listView_order = findViewById(R.id.list_order);
        equal();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    /**
     * 获取订单总数和具体id
     */
    private void equal() {
        BmobQuery<Order> Query = new BmobQuery<>();
        Query.addWhereEqualTo("OrderNumber", 1);
        Query.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> list, BmobException e) {
                if (e == null) {
//                    Snackbar.make(mBtnEqual, "查询成功：" + object.size(), Snackbar.LENGTH_LONG).show();

                        textView_orderSum.setText("订单数："+list.size());
                    for (int i = 0; i < list.size(); i++) {
                        Log.d(TAG, "done: yukaida   "+list.get(i).getObjectId()+","+list.get(i).getCreatedAt()+"/n");
                        String temp="订单id:  "+list.get(i).getObjectId()+"\n"+"         "+"\n"+"                       创建时间:  "+list.get(i).getCreatedAt()+"\n";
                        orderData.add(temp);
                    }

                    ListView listView = findViewById(R.id.list_order);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            MyPublishActivity.this, android.R.layout.simple_list_item_1, orderData);
                    listView.setAdapter(adapter);

                    Log.d(TAG, "done: yukaida orderData"+orderData.size());
                } else {
                    Log.e("BMOB", e.toString());
                    Log.d(TAG, "done: yukaida"+e.toString());
//                    Snackbar.make(mBtnEqual, e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}
