package com.example.administrator.jyly;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.jyly.MyPublish.MyPublishActivity;
import com.example.administrator.jyly.loginAvtivity.LoginActivity;
import com.example.administrator.jyly.we.Item;
import com.example.administrator.jyly.we.We_recyclerView_adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class we_fragment extends Fragment {
    private List<Item> itemList = new ArrayList<>();
    private View view;
    private RecyclerView recyclerView;
    private We_recyclerView_adapter we_recyclerView_adapter;
    private Button button_call;
    private Button button_kefu1;
    private Button button_kefu2;
    private Button button_kefu3;
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

        Button button_login = (Button)view.findViewById(R.id.button_login);
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

}
