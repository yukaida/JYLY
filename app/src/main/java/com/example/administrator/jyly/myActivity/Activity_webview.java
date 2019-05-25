package com.example.administrator.jyly.myActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.jyly.R;

public class Activity_webview extends AppCompatActivity {
    private WebView webView_shouye;
    private String weburl="";
    private static final String TAG = "Activity_webview";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent intent_getWebUrl = getIntent();
        weburl=intent_getWebUrl.getStringExtra("webUrl");

        Log.d(TAG, "webUrl : "+weburl);

        webView_shouye = findViewById(R.id.webView_shouye);
        webView_shouye.getSettings().setJavaScriptEnabled(true);
        webView_shouye.setWebViewClient(new WebViewClient());
        webView_shouye.loadUrl(weburl);
    }
}
