package com.example.administrator.jyly.signinActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.jyly.Bomb.Person;
import com.example.administrator.jyly.R;
import com.example.administrator.jyly.loginAvtivity.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SigninActivity extends AppCompatActivity {
    public int x = 0;
    Button button_signin2;
    EditText name;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

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

        button_signin2 = (Button) findViewById(R.id.button_signin2);


        button_signin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "即将完成注册并自动登录", Snackbar.LENGTH_SHORT).setAction("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(SigninActivity.this, "已确认", Toast.LENGTH_SHORT).show();
                    }
                }).show();

                name = (EditText) findViewById(R.id.login_name2);
                password = (EditText) findViewById(R.id.login_password2);

                Person p2 = new Person();
                p2.setName(name.toString());
                p2.setAddress(password.toString());
                p2.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId,BmobException e) {
                        if(e==null){
//                    toast("添加数据成功，返回objectId为："+objectId);
                            Toast.makeText(SigninActivity.this, "添加数据成功，返回objectId为："+objectId, Toast.LENGTH_SHORT).show();
                        }else{
//                    toast("创建数据失败：" + e.getMessage());
                            Toast.makeText(SigninActivity.this, "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                if (x==0){

                    Timer time = new Timer();
                    TimerTask timetask = new TimerTask() {
                        Intent intent = new Intent(SigninActivity.this, LoginActivity.class);

                        @Override
                        public void run() {
                            startActivity(intent);
                            finish();
                        }
                    };
                    time.schedule(timetask, 3000);

                    }

            }
        });



         }
    }
