package com.example.administrator.jyly.MyPublish;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jyly.Bomb.Order;
import com.example.administrator.jyly.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class MyPublishActivity extends AppCompatActivity {
    private List<String> orderData = new ArrayList<>();
    private static final String TAG = "MyPublishActivity";
    private TextView textView_orderSum;
    private ListView listView_order;
    private Button button_clean_shoppingCart;

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
        {Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);}
        //设置沉浸式状态栏

        initView();
        equal();
    }

    /**获取订单总数和具体id*/
    private void equal() {
        BmobQuery<Order> Query = new BmobQuery<>();
        Query.addWhereEqualTo("OrderNumber", 1);
        Query.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> list, BmobException e) {
                if (e == null) {
                        textView_orderSum.setText("订单数："+list.size());
                    for (int i = 0; i < list.size(); i++) {
                        Log.d(TAG, "done: yukaida   "+list.get(i).getObjectId()+","+list.get(i).getCreatedAt()+"/n");
                        String temp="订单id:  "+list.get(i).getObjectId()+"\n"+"         "+"\n"+"                       创建时间:  "+list.get(i).getCreatedAt()+"\n";
                        orderData.add(temp);
                    }
                    listView_order = findViewById(R.id.list_order);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            MyPublishActivity.this, android.R.layout.simple_list_item_1, orderData);
                    listView_order.setAdapter(adapter);
                    Log.d(TAG, "done: yukaida orderData"+orderData.size());
                } else {
                    Log.e("BMOB", e.toString());
                    Log.d(TAG, "done: yukaida"+e.toString());
                }
            }
        });
    }
    /**实例化控件*/
    private void initView() {
        textView_orderSum = findViewById(R.id.textView_orderSum);
        listView_order = findViewById(R.id.list_order);
        //清空购物车按钮的实现
        button_clean_shoppingCart = findViewById(R.id.button_clean_shoppingCart);
        button_clean_shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteShoppingCart();
                List<String> orderData2 = new ArrayList<>();
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                        MyPublishActivity.this, android.R.layout.simple_list_item_1, orderData2);
                listView_order.setAdapter(adapter2);
                textView_orderSum.setText("订单数：0");
            }
        });
    }
    /**清空购物车方法实现*/
    private void deleteShoppingCart(){
        BmobQuery<Order> Query = new BmobQuery<>();
        Query.addWhereEqualTo("OrderNumber", 1);
        Query.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> query, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < query.size(); i++) {
                        deleteData(query.get(i).getObjectId());

//                        Intent intent_refash = new Intent(MyPublishActivity.this, MyPublishActivity.class);
//                        startActivity(intent_refash);
                    }
                } else {
                    Log.e("BMOB", e.toString());
                }
            }
        });
    }

    /**删除数据 */
    private void deleteData(String id){
        Order x = new Order();
        x.setObjectId(id);
        x.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(MyPublishActivity.this, "购物车已清空", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d(TAG, "done: "+e.getMessage());
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
