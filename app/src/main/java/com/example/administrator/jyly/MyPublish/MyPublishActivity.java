package com.example.administrator.jyly.MyPublish;

import android.R.layout;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jyly.Bomb.Order;
import com.example.administrator.jyly.MainActivity;
import com.example.administrator.jyly.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;

public class MyPublishActivity extends AppCompatActivity {
    private String[] data = {"订单1", "订单2"};

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
        sum();
//        equal();
//        ChangeTextView();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MyPublishActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_order);
        listView.setAdapter(adapter);
    }

    private void sum() {
        BmobQuery<Order> query = new BmobQuery<Order>();
        query.addWhereEqualTo("OrderNumber",1);
        query.count(Order.class, new CountListener() {
            @Override
            public void done(Integer count, BmobException e) {
                if(e==null){
                    orderSum =count;
//                    Log.d(TAG, "done: kd"+orderSum);
                    textView_orderSum.setText("订单数："+orderSum);
                    Toast.makeText(MyPublishActivity.this, "查询成功:订单数为"+count, Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

//    private void ChangeTextView(){
//
//        Log.d(TAG, "ChangeTextView: kd"+orderSum);
//
//    }


    /**
     * name为football的类别
     */
    private void equal() {
        BmobQuery<Order> Query = new BmobQuery<>();
        Query.addWhereEqualTo("name", "name");
        Query.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> list, BmobException e) {
                if (e == null) {
//                    Snackbar.make(mBtnEqual, "查询成功：" + object.size(), Snackbar.LENGTH_LONG).show();

                        Log.d(TAG, "done: kd equal  "+list.size());
                    for (int i = 0; i < list.size(); i++) {
                        Log.d(TAG, "done: yukaida   "+list.get(i).getName()+"/n");
                    }
                } else {
                    Log.e("BMOB", e.toString());
                    Log.d(TAG, "done: kd"+e.toString());
//                    Snackbar.make(mBtnEqual, e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}
