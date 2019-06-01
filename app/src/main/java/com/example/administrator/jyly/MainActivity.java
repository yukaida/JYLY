package com.example.administrator.jyly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {
        private DrawerLayout drawerLayout;
        private BottomNavigationView bottomNavigationView;
        private MenuItem menuItem;
        public Uri Imageuri;
        public BroadcastReceiver broadcastReceiver;
         public int dayNumber=0;
         public NavigationView navView;

    private static final String TAG = "MainActivity";

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    public Uri getImageuri() {
        return Imageuri;
    }

    public void setImageuri(Uri imageuri) {
        Imageuri = imageuri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        design_fragment mydesign_fragment=new design_fragment();
        mydesign_fragment.setImageUri(getImageuri());

    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


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

            replaceFragment(new home_fragment());

            bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Bmob.initialize(this,"18412fa9be701c096295ccef5d74d1db");

            IntentFilter intentFilter = new IntentFilter("Check");
            broadcastReceiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tell:"+"18170402901"));
                    startActivity(intent);
                    Log.d(TAG, "onReceive: called");
                }
            };
            MainActivity.this.registerReceiver(broadcastReceiver, intentFilter);

        }
        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                menuItem = item;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                    {
                        replaceFragment(new home_fragment());
                    }
                    return true;
                    case R.id.navigation_design:
                    {
                        replaceFragment(new design_fragment());
                    }
                    return true;
                    case R.id.navigation_dsroom:
                    {
                        replaceFragment(new dsroom_fragment());
                    }
                    return true;
                    case R.id.navigation_we:
                    {
                        replaceFragment(new we_fragment());
                    }
                    return true;
                }
                return false;
            }
        };

        private void replaceFragment(Fragment fragment) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.replace(R.id.frameLayout, fragment);
            transaction.commit();
        }

    }