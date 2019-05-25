package com.example.administrator.jyly.jilu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
    private ImageView picture;
    public Uri imageUri;//照片的保存路径


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myidea);

        picture = (ImageView) findViewById(R.id.imageView_picture);//获取用于显示照片的ImageView的实例

        Button button_takephoto = (Button) findViewById(R.id.button_design_takephoto);//获取Button_拍照的实例

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
        final EditText editText = (EditText)findViewById(R.id.editText_design);


        //悬浮按钮我的发布页面  获取实例和设置监听器

        FloatingActionButton fab =(FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_toMyPublish = new Intent(JiluActivity.this, MyPublishActivity.class);
                startActivity(intent_toMyPublish);

            }
        });


        //选择照片按钮实例，设施监听器
        Button button_ch= findViewById(R.id.button_design_chose);
        button_ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromAlbum();
            }
        });

        //发布按钮功能 用于拍照或选择照片并输入文字后发布图片和文字 并跳转到主ACTIVITY

        Button button_fabu = (Button) findViewById(R.id.design_button_send);
        button_fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Input input_mes = new Input();
                input_mes.setInputmes(editText.getText().toString());
                input_mes.update("LpOoHHHu", new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(JiluActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(JiluActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                Person p2 = new Person();
//                p2.setAddress("北京朝阳");
//                p2.update("6b6c11c537", new UpdateListener() {
//
//                    @Override
//                    public void done(BmobException e) {
//                        if(e==null){
//                            toast("更新成功:"+p2.getUpdatedAt());
//                        }else{
//                            toast("更新失败：" + e.getMessage());
//                        }
//                    }
//
//                });
            }
        });
//        Button button_fabu = (Button) findViewById(R.id.design_button_send);
//        button_fabu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Order order1=new Order();
//                order1.setOrderNumber(editText.getText().toString());
//
//                File file = new File(PathGetter.getPath(JiluActivity.this, imageUri));
//                BmobFile bmobFile_chose = new BmobFile(file);
//                order1.setPic(bmobFile_chose);
//                order1.save(new SaveListener<String>() {
//                    @Override
//                    public void done(String objectId,BmobException e) {
//                        if(e==null){
////                    toast("添加数据成功，返回objectId为："+objectId);
//                            Toast.makeText(JiluActivity.this, "发布 成功，返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
//                        }else{
////                    toast("创建数据失败：" + e.getMessage());
//                            Toast.makeText(JiluActivity.this, "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//
////                Intent intent_send = new Intent(JiluActivity.this, MainActivity.class);
////                startActivity(intent_send);
//            }
//        });


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
                        picture.setImageBitmap(bitmap);
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
                    picture.setImageURI(imageUri);
                } catch (Exception e) {
                    e.printStackTrace();
                }


        }}



}
