package com.example.administrator.jyly;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.jyly.Bomb.Input;
import com.example.administrator.jyly.Design.DesignAdapter;
import com.example.administrator.jyly.Design.DesignItem;
import com.example.administrator.jyly.jilu.JiluActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

public class design_fragment extends Fragment {
    public static final int TAKE_PHOTO = 1;
    private ImageView picture;
    public Uri imageUri;

    private SwipeRefreshLayout swipeRefreshLayout;

    public List<DesignItem> designItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private String mes;
    private DesignAdapter designAdapter;
    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.design_fragment2, container, false);


        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refersh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });


        Button button_sys = (Button) view.findViewById(R.id.button10);
        button_sys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent_takephoto = new Intent("android.media.action.IMAGE_CAPTURE");
//                intent_takephoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                startActivityForResult(intent_takephoto,TAKE_PHOTO);


                // 通过包名获取要跳转的app，创建intent对象
                Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.android.camera");
// 这里如果intent为空，就说名没有安装要跳转的应用嘛
                if (intent != null) {
                    // 这里跟Activity传递参数一样的嘛，不要担心怎么传递参数，还有接收参数也是跟Activity和Activity传参数一样
                    startActivity(intent);
                } else {
                    // 没有安装要跳转的app应用，提醒一下
                    Toast.makeText(getApplicationContext(), "应用未安装", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button button_to_ideaActivity = (Button) view.findViewById(R.id.button11);
        button_to_ideaActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_idea = new Intent(getActivity(), JiluActivity.class);
                startActivity(intent_to_idea);
            }
        });

        initList(designItemList);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        designAdapter = new DesignAdapter(designItemList);
        recyclerView.setAdapter(designAdapter);


//        Button design_button = (Button) view.findViewById(R.id.design_button_send);
//        design_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


//
//        Button button_takephoto = (Button) view.findViewById(R.id.button_design_takephoto);
//        button_takephoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                File outputImage = new File(getActivity().getExternalCacheDir(), "output_image.jpg");
//
//                if (outputImage.exists()) {
//                    outputImage.delete();
//                }
//                try {
//                    outputImage.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                imageUri = FileProvider.getUriForFile(getActivity(), "com.example.camerralbumtest.fileprovider", outputImage);
//
//                Intent intent_takephoto = new Intent("android.media.action.IMAGE_CAPTURE");
//                intent_takephoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                startActivityForResult(intent_takephoto,TAKE_PHOTO);
//                MainActivity mainActivity=(MainActivity) getActivity();
//                mainActivity.setImageuri(imageUri);
//            }
//        });
//
//        EditText editText = (EditText) view.findViewById(R.id.editText_design);
//
//
//        FloatingActionButton fab =(FloatingActionButton) view.findViewById(R.id.floatingActionButton);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent_toMyPublish = new Intent(getActivity(), MyPublishActivity.class);
//                getActivity().startActivity(intent_toMyPublish);
//
//            }
//        });
//
//
//
//        Button button_chose = (Button) view.findViewById(R.id.button_design_chose);
//        button_chose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
////                // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
////                intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/jpeg");
////                getActivity().startActivity(intentToPickPic);
//                pickImageFromAlbum();
//
//            }
//        });

        return view;
    }

//    public void pickImageFromAlbum() {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        startActivityForResult(intent, 111);
//
//    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        switch (requestCode) {
//            case TAKE_PHOTO:
//                if (resultCode == RESULT_OK) {
//                    try {
//                        Bitmap bitmap=BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(getImageUri()));
//                        picture.setImageBitmap(bitmap);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//                break;
//
//            case 111:
//                if (resultCode == RESULT_CANCELED) {
//                    Toast.makeText(getActivity(), "点击取消从相册选择", Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                try {
//                    Uri imageUri = data.getData();
//                    Log.e("TAG", imageUri.toString());
//                    picture.setImageURI(imageUri);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//            default:
//                break;
//        }
//    }
private  void initList(List<DesignItem> list){
    DesignItem a = new DesignItem(R.drawable.a21,"森系小清新\n" +
            "类似图片所示风格\n  预算10万以下");
    list.add(a);
    DesignItem b = new DesignItem(R.drawable.a22,"格兰云天\n" +
            " 精致的婚礼\n 预算7万\n 预备进行三天");
    list.add(b);
    DesignItem c = new DesignItem(R.drawable.a23," 森林\n" +
            "风格： 浪漫\n" +
            " 你眼中有我 我眼中有你 预算15万 \n ");
    list.add(c);
    DesignItem d = new DesignItem(R.drawable.a24,"待你在灯下\n" +
            "风格： 浪漫\n" +
            "描述： 待你在灯下  预算5万\n");
    list.add(d);



}


private void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final BmobQuery<Input> bmobQuery_input = new BmobQuery<>();
                        bmobQuery_input.getObject("LpOoHHHu", new QueryListener<Input>()  {
                            @Override
                            public void done(Input object,BmobException e) {
                                if(e==null){
                                    DesignItem h2=new DesignItem(R.drawable.car,object.getInputmes());
                                    designItemList.add(h2);
                                    Toast.makeText(getContext(), "获取成功", Toast.LENGTH_SHORT).show();
                                }else{

                                    Toast.makeText(getContext(), "查询失败："+ e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();


}

}
