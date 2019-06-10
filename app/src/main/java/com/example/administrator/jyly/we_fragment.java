package com.example.administrator.jyly;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jyly.Activity_add.SetActivity;
import com.example.administrator.jyly.MyPublish.MyPublishActivity;
import com.example.administrator.jyly.loginAvtivity.LoginActivity;
import com.example.administrator.jyly.we.Item;
import com.example.administrator.jyly.we.We_recyclerView_adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.NOTIFICATION_SERVICE;

public class we_fragment extends Fragment {
    private List<Item> itemList = new ArrayList<>();
    private View view;

    private RecyclerView recyclerView;
    private We_recyclerView_adapter we_recyclerView_adapter;
    private Button button_call;
    private Button button_shouchang;

    private TextView textView_day;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private ImageView imageView_set;

    private TextView textView_set;
    public int dayNumber=0;
    private static final String TAG = "we_fragment";
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initItem();
        view = inflater.inflate(R.layout.we_fragment, container, false);
        recyclerView=view.findViewById(R.id.we_recyclerView);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        we_recyclerView_adapter = new We_recyclerView_adapter(getContext(), itemList);
        recyclerView.setAdapter(we_recyclerView_adapter);

        Button button_zuji = view.findViewById(R.id.button4);
        button_zuji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "足迹", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MyPublishActivity.class);
                getActivity().startActivity(intent);
            }
        });

        Button button_login =view.findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(),LoginActivity.class);
                getActivity().startActivity(intent1);
            }
        });

        button_call = view.findViewById(R.id.button9);
        button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view, "即将拨打客服电话", Snackbar.LENGTH_SHORT).setAction("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "拨打", Toast.LENGTH_SHORT).show();
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                Intent intent_call = new Intent(Intent.ACTION_CALL);
                                intent_call.setData(Uri.parse("tel:10086"));
                                startActivity(intent_call);
                            }
                        };
                        timer.schedule(task, 2000);
                    }
                }).show();
            }
        });


        button_shouchang = view.findViewById(R.id.button_sc);
        button_shouchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DefautAvtivity.class);
                getActivity().startActivity(intent);
            }
        });

        imageView_set=view.findViewById(R.id.imageView_set);
        imageView_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toSetActivity = new Intent(getActivity(), SetActivity.class);
                getActivity().startActivity(intent_toSetActivity);
            }
        });

        final String channelId = "MarryDay";
        String channelName = "婚礼日期通知";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        createNotificationChannel(channelId, channelName, importance);

        textView_day = view.findViewById(R.id.textView_day);
        textView_day.setText("婚礼倒计时"+dayNumber+"天");
        textView_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar=Calendar.getInstance();
                datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        Log.d(TAG, "onDateSet: "+year+"/"+month+"/"+dayOfMonth);
                        String dateHuli=year+"-"+month+"-"+dayOfMonth;

                        dayNumber =(int)dateDiff(getDate(),dateHuli , "yyyy-MM-dd");
                        //获取日期差

                        Log.d(TAG, "onDateSet:yukaida 当前日期"+getDate()+"/  婚礼日期"+dateHuli+"/  相差"+dayNumber);
                        textView_day.setText("婚礼倒计时"+dayNumber+"天");

                        NotificationManager manager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
                        Notification notification = new NotificationCompat.Builder(getContext(), "MarryDay")
                                .setContentTitle("婚礼日期")
                                .setContentText(dateHuli)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.drawable.aixin)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.aixin))
                                .setAutoCancel(true)
                                .setVibrate(new long[]{0, 1000, 1000, 1000})
                                .setLights(Color.GREEN, 1000, 1000)
                                .setContentText("金玉良缘app祝您万事如意"+ "\n"+"婚期：" +dateHuli)
                                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.aixin3)))
                                .build();
                        manager.notify(1, notification);
                        Log.d(TAG, "onDateSet: yukaida notificationCreated");
                    }
                },calendar.get(Calendar.YEAR), calendar
                               .get(Calendar.MONTH), calendar
                               .get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        return view;
    }

    private void initItem() {
            Item item = new Item(R.drawable.qintie, "我的订单");
            itemList.add(item);
            Item item1 = new Item(R.drawable.aixin2, "我的相册");
            itemList.add(item1);
            Item item2 = new Item(R.drawable.shiping, "我的婚礼录像");
            itemList.add(item2);
            Item item3 = new Item(R.drawable.shangjia, "商家入驻");
            itemList.add(item3);
            Item item4 = new Item(R.drawable.qiqiu, "联系在线客服");
            itemList.add(item4);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    private String getDate(){
        int year = calendar.get(Calendar.YEAR);
//月
        int month = calendar.get(Calendar.MONTH)+1;
//日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date=year+"-"+month+"-"+day;
        return date;
    }

    public long dateDiff(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime()
                    - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            if (day<=0) {
                Toast.makeText(getActivity(), "请选择一个未来的日期！", Toast.LENGTH_LONG).show();
            }
            Log.d(TAG, "dateDiff: 时间相差：" + day + "天");
            if (day>=1) {
                return day;
            }else {
                if (day==0) {
                    return 1;
                }else {
                    return 0;
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.setmenu, menu);
    }
}
