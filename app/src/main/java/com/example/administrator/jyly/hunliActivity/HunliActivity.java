package com.example.administrator.jyly.hunliActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.jyly.R;

public class HunliActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunli);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://m.v.qq.com/play.html?&vid=v0509xanxli&ptag=m.baidu.com%23v.play.adaptor%232&mreferrer=https%3A%2F%2Fm.baidu.com%2Ffrom%3D1019023i%2Fbd_page_type%3D1%2Fssid%3D0%2Fuid%3D0%2Fpu%3Dusm%25405%252Csz%2540320_1001%252Cta%2540iphone_2_9.0_24_57.0%2Fbaiduid%3D40124CA0B84FEA4EAA5BB70F69FADB1A%2Fw%3D0_10_%2Ft%3Diphone%2Fl%3D1%2Ftc%3Fref%3Dwww_iphone%26lid%3D7579688186507834126%26order%3D9%26waplogo%3D1%26waput%3D1%26fm%3Dwnor%26isAtom%3D1%26is_baidu%3D0%26h5ad%3D0%26tj%3Dwww_video_normal_9_0_10_title%26hwj%3D1612732424718028%26wd%3D%26eqid%3D6930763f29063400100000005c22365c%26w_qd%3DIlPT2AEptyoA_yk5-Poa8vy53CFOcJC%26tcplug%3D1%26sec%3D35173%26di%3D340aa5fb0a702741%26bdenc%3D1%26tch%3D124.1946.198.1987.3.2222%26nsrc%3DIlPT2AEptyoA_yixCFOxCGZb8c3JV3T5AADPRD5K1TWz7JuVcrWxBcZbZTKqAp7EU-CdbXa4ftVRaWbzKzlq%26clk_type%3D1%26vit%3Dosres%26l%3D1%26baiduid%3D40124CA0B84FEA4EAA5BB70F69FADB1A%26w%3D0_10_%25E5%25A9%259A%25E7%25A4%25BC%25E8%25A7%2586%25E9%25A2%2591%26t%3Diphone%26from%3D1019023i%26ssid\n"
);
    }
}
