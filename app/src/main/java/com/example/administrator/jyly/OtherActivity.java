package com.example.administrator.jyly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.jyly.Bomb.Order;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class OtherActivity extends AppCompatActivity {
public int x=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Intent intent = getIntent();
        String pic=intent.getStringExtra("pic");
        ImageView imageView = (ImageView) findViewById(R.id.imageView111);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        findViewById(R.id.imageView111).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 注意这里不使用finish
                ActivityCompat.finishAfterTransition(OtherActivity.this);
            }
        });


        Button button_tianjia = (Button) findViewById(R.id.button_tianjia);
        button_tianjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x=x+1;
                Order p2 = new Order();
                p2.setOrderNumber(x);
                Toast.makeText(OtherActivity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
                p2.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId,BmobException e) {
                        if(e==null){
//                    toast("添加数据成功，返回objectId为："+objectId);
                            Toast.makeText(OtherActivity.this, "添加数据成功，返回objectId为："+objectId, Toast.LENGTH_SHORT).show();
                        }else{
//                    toast("创建数据失败：" + e.getMessage());
                            Toast.makeText(OtherActivity.this, "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

//        findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 注意这里不使用finish
//                ActivityCompat.finishAfterTransition(OtherActivity.this);
//            }
//        });

    }
}
