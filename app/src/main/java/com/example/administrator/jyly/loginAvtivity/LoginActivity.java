package com.example.administrator.jyly.loginAvtivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.jyly.MainActivity;
import com.example.administrator.jyly.R;
import com.example.administrator.jyly.signinActivity.SigninActivity;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button button_signin;
    Button button_login;
    EditText editText_name;
    EditText editText_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button_signin = (Button) findViewById(R.id.button_signin);
        button_login = (Button) findViewById(R.id.button_login);

        button_login.setOnClickListener(this);
        button_signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_signin:
                Intent intent_toSigninActivity = new Intent(LoginActivity.this, SigninActivity.class);
                startActivity(intent_toSigninActivity);
                break;
            case R.id.button_login:

                Toast.makeText(this, "验证通过", Toast.LENGTH_SHORT).show();
                Timer time = new Timer();
                TimerTask timetask = new TimerTask() {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    @Override
                    public void run() {
                        startActivity(intent);
                        finish();
                    }
                };
                time.schedule(timetask, 2000);
                
                
                break;
            default:
                break;
        }
    }
}
