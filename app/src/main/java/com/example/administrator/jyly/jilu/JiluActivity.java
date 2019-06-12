package com.example.administrator.jyly.jilu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.jyly.Bomb.Input;
import com.example.administrator.jyly.MyPublish.MyPublishActivity;
import com.example.administrator.jyly.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class JiluActivity extends AppCompatActivity {
    public static final int TAKE_PHOTO=1;//照片拍摄完毕以后的返回码
    private ImageView imageView_picture;
    public Uri imageUri;//照片的保存路径


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myidea);

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

        imageView_picture = findViewById(R.id.imageView_picture);//获取用于显示照片的ImageView的实例

        Button button_takephoto = findViewById(R.id.button_design_takephoto);//获取Button_拍照的实例

        button_takephoto.setOnClickListener(new View.OnClickListener() {//设置Button_拍照的按钮监听器
            @Override
            public void onClick(View v) {
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");

                if (outputImage.exists()) {
                    outputImage.delete();
                }
                try {
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //创建图片文件路径调用Intent启动拍照活动，完成后返回识别码TAKE_PHOTO
                imageUri = FileProvider.getUriForFile(JiluActivity.this, "com.example.camerralbumtest.fileprovider", outputImage);
                Intent intent_takephoto = new Intent("android.media.action.IMAGE_CAPTURE");
                intent_takephoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent_takephoto,TAKE_PHOTO);
            }
        });

        //输入框实例
        final EditText editText = findViewById(R.id.editText_design);

        //选择照片按钮实例，设施监听器
        Button button_ch= findViewById(R.id.button_design_chose);
        button_ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromAlbum();
            }
        });

        //发布按钮功能 用于拍照或选择照片并输入文字后发布图片和文字 并跳转到主ACTIVITY

        Button button_fabu =  findViewById(R.id.design_button_send);
        button_fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Input input_mes = new Input();
                input_mes.setInputmes(editText.getText().toString());
                input_mes.update("yfhALLLa", new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(JiluActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(JiluActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    public void pickImageFromAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 111);
        //选择照片功能
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //处理返回码  TAKE_PHOTO则启用拍照后的图片处理逻辑，111则启用从相册选择照片后的图片处理逻辑
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        //压缩图片用于显示
                        imageView_picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case 111:
                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(JiluActivity.this, "点击取消从相册选择", Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    Uri imageUri = data.getData();
                    Log.e("TAG", imageUri.toString());
                    imageView_picture.setImageURI(imageUri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }}
}
