package com.example.administrator.jyly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.jyly.Bomb.home;
import com.example.administrator.jyly.MyPublish.MyPublishActivity;
import com.example.administrator.jyly.dsroom.DSitem;
import com.example.administrator.jyly.dsroom.DSroom_recyclerView_adapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class dsroom_fragment extends Fragment {
    private List<DSitem> dsitemList = new ArrayList<>();
    private View view;
    private RecyclerView recyclerView;
    private DSroom_recyclerView_adapter dSroom_recyclerView_adapter;
    private SwipeRefreshLayout swipeRefreshLayout_ds;
    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initItem_ds();
        view = inflater.inflate(R.layout.dsroom_fragment, container, false);
        recyclerView=view.findViewById(R.id.dsroom_recyclerView);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        dSroom_recyclerView_adapter=new DSroom_recyclerView_adapter(dsitemList);
        recyclerView.setAdapter(dSroom_recyclerView_adapter);

        swipeRefreshLayout_ds = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout_ds.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout_ds.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "refresh start", Toast.LENGTH_SHORT).show();
                refresh();
            }
        });

        floatingActionButton = view.findViewById(R.id.floatingActionButton_shopingcar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toMypublish = new Intent(getActivity(), MyPublishActivity.class);
                getActivity().startActivity(intent_toMypublish);
            }
        });
        return view;
    }

    private void initItem_ds() {
        for (int i = 0; i < 2; i++) {
            DSitem item = new DSitem(R.drawable.a11, "白槿花——【记录爱情】婚纱系列 \n" +
                    "风格：\n" +
                    "描述： 幸福不是突如其来，而是弥漫在生活的点点滴滴...记录爱情最真实的瞬间，把最美的一面尽情展现~");
            dsitemList.add(item);
            DSitem item1 = new DSitem(R.drawable.a12, "南昌维纳斯——【让人幸福的事情】 \n" +
                    "风格：\n" +
                    "描述： 让人幸福的事情：有人爱，有事做，有所期待");
            dsitemList.add(item1);
            DSitem item2 = new DSitem(R.drawable.a13, "简约精致 \n" +
                    "拍摄场景： 森系小清新\n" +
                    "风格：\n" +
                    "作品故事： 精致的婚礼 只为献给精致的你");
            dsitemList.add(item2);
        }
    }

    private void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final BmobQuery<home> bmobQuery = new BmobQuery<>();
                        bmobQuery.getObject("fd3oAAAN", new QueryListener<home >() {
                            @Override
                            public void done(home object,BmobException e) {
                                if(e==null){
                                    DSitem h2=new DSitem(R.drawable.a11,object.getMes());
                                    dsitemList.add(h2);
                                    Toast.makeText(getContext(), "查询成功", Toast.LENGTH_SHORT).show();
                                }else{

                                    Toast.makeText(getContext(), "查询失败："+ e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        swipeRefreshLayout_ds.setRefreshing(false);
                    }
                });
            }
        }).start();
    }}
