package com.example.administrator.jyly.Activity_add;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.jyly.Bomb.Input;
import com.example.administrator.jyly.R;

import java.io.File;
import java.net.URL;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UploadFileListener;

public class UploadActivity extends AppCompatActivity {
    private Button button_upload;
    private ImageView imageView_upload;
    private static final String TAG = "UploadActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());

        imageView_upload = findViewById(R.id.imageView_upload);

        button_upload = findViewById(R.id.button_upload);
        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });

    }

    private void uploadPic(){
        String picPath = "/storage/emulated/0/temp.jpg";
        final BmobFile bmobFile = new BmobFile(new File(picPath));
        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    Toast.makeText(UploadActivity.this, "上传文件成功:" + bmobFile.getFileUrl(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UploadActivity.this, "文件上传失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }


    /*
     * 在此例中，通过按钮点击事件，来下载图片
     * BmobQuery的泛型为你的数据类
     * 当e == null时，表明数据获取成功，将得到的List传入show_add类中下载图片*/

    private void query() {
            BmobQuery<Input> query=new BmobQuery<Input>();
            query.findObjects(new FindListener<Input>() {
                @Override
                public void done(List<Input> list, BmobException e) {
                    if(e == null){
                        String picUrl=list.get(0).getImage().getUrl();
                        Log.d(TAG, "done:yukaida: "+picUrl);
                        Bitmap bitmap=ImageTools.getIcon(picUrl);
                        imageView_upload.setImageBitmap(bitmap);
                    }else{
                        Toast.makeText(UploadActivity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    /*
     * 例子中取得其中一个图片展示，所以选择的是list.get(0)
     * 获取到BmobFile对象，然后调用下载方法。
     * onProgress方法表示下载进程，目前用不到。
     * done表明下载完成，得到的String s表明下载之后的储存的默认地址(当然可以自定义地址，方法请自行查询Bmob开发文档)
     * */
    public void show_ad(List<Input> list){
        Input ad = list.get(0);
        BmobFile icon=ad.getImage();
        icon.download(new DownloadFileListener() {
            @Override
            public void onProgress(Integer integer, long l) {

            }

            @Override
            public void done(String s, BmobException e) {
                if(e == null){
//                    iv_ad.setImageBitmap(BitmapFactory.decodeFile(s));   //根据地址解码并显示图片
                }
            }
        });
    }

}
