package com.example.administrator.jyly;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.administrator.jyly.home.Homeadapter;
import com.example.administrator.jyly.home.Homeadapter2;
import com.example.administrator.jyly.home.Homeitem;
import com.example.administrator.jyly.home.Homeitem2;

import java.util.ArrayList;
import java.util.List;

public class home_fragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private Homeadapter homeadapter;
    private List<Homeitem> homeitemList=new ArrayList<>();


    private RecyclerView recyclerView2;
    private Homeadapter2 homeadapter2;
    private List<Homeitem2> homeitemList2=new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    private ViewPager viewPager;
    private View view1, view2, view3;
    private List<View> viewList;//view数组



    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = view.findViewById(R.id.home_recyclerView);
        recyclerView2 =  view.findViewById(R.id.home_recyclerView2);

        initHomeitemList();
        initHomeitemList2();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView2.setLayoutManager(staggeredGridLayoutManager);

        homeadapter = new Homeadapter(homeitemList,getActivity());
        homeadapter2 = new Homeadapter2(homeitemList2,getActivity());

        recyclerView.setAdapter(homeadapter);
        recyclerView2.setAdapter(homeadapter2);


        ImageButton imageButton_mes = (ImageButton) view.findViewById(R.id.imageButton);
        imageButton_mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_toMEs = new Intent(getActivity(), mesActivity.class);
                getActivity().startActivity(intent_toMEs);
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sw2);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "getSuccess runing time =30ms 请求超时时间（单位为秒）：默认15s\n" +
                        "        //.setConnectTimeout(30)", Toast.LENGTH_SHORT).show();
                refresh();
            }
        });


        viewPager=view.findViewById(R.id.viewPager);

        inflater=getLayoutInflater();
        view1 = inflater.inflate(R.layout.layout_one, null);
        view2 = inflater.inflate(R.layout.layout_two,null);
        view3 = inflater.inflate(R.layout.layout_three, null);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);


        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };


        viewPager.setAdapter(pagerAdapter);

    

//    swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//        @Override
//        public void onRefresh() {
//            refresh();
//        }
//    });
//        Context context = getActivity();
//        dia = new Dialog(context);
//        dia.setContentView(R.layout.dialog_layout);
//        ImageView imageView = (ImageView) dia.findViewById(R.id.imageView_dialog);
//        imageView.setBackgroundResource(R.drawable.ic_launcher_foreground);
//        //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
//        dia.setCanceledOnTouchOutside(true); // Sets whether this dialog is
//        Window w = dia.getWindow();
//        WindowManager.LayoutParams lp = w.getAttributes();
//        lp.x = 0;
//        lp.y = 40;
//        dia.onWindowAttributesChanged(lp);
//        imageView.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dia.dismiss();
//                    }
//                });

        return view;
    }

    private void initHomeitemList(){
//        for (int i = 0; i < 10; i++) {
//            Homeitem item = new Homeitem(R.drawable.pic_dsroom, "item1");
//            homeitemList.add(item);
//            Homeitem item1 = new Homeitem(R.drawable.pic_dsroom, "item2");
//            homeitemList.add(item1);
//        }
        Homeitem a=new Homeitem(R.drawable.aixin,"婚礼策划");
        homeitemList.add(a);
        Homeitem b=new Homeitem(R.drawable.jiudian,"婚庆酒店");
        homeitemList.add(b);
        Homeitem c=new Homeitem(R.drawable.hunche,"婚车租凭");
        homeitemList.add(c);
        Homeitem d=new Homeitem(R.drawable.sheying,"婚纱摄影");
        homeitemList.add(d);
        Homeitem e=new Homeitem(R.drawable.gouwu,"蜜月套餐");
        homeitemList.add(e);
        Homeitem f=new Homeitem(R.drawable.pic_dsroom,"婚庆视频");
        homeitemList.add(f);
        Homeitem g=new Homeitem(R.drawable.hongbao,"一价全包");
        homeitemList.add(g);
        Homeitem h=new Homeitem(R.drawable.huazhuang,"跟拍跟妆");
        homeitemList.add(h);

    }

    private void initHomeitemList2(){
        for (int i = 0; i <1 ; i++) {
            Homeitem2 a2=new Homeitem2(R.drawable.a1,"  抛却传统的中国大红风格\n" +
                    "古朴素雅的色调与有着中国传统元素相辅相成的新中式风格\n" +
                    "带你走进一个充满中式情怀的东方绮梦");
            homeitemList2.add(a2);
            Homeitem2 b2=new Homeitem2(R.drawable.a2,"  几何框架与半透纱质画布打造家的感觉，黄金视角处两三簇花艺更添静谧美好\n" +
                    "轻盈精巧的花艺在通透的水晶柱上静静盛开，来吧，和我一起去爱的家园");
            homeitemList2.add(b2);
            Homeitem2 c2=new Homeitem2(R.drawable.a3,"  灵感源于皇室绿宝石之王——祖母绿，不但霸占着最优雅高贵的尊宠，且没有一种绿可以比之更美。三块简约大型喷绘品字摆放，忽明忽暗的纹理，以黄金比例切割，配合金色雕刻和酒红玫瑰，视觉上浓烈亦沉稳，为个性婚礼首选。");
            homeitemList2.add(c2);
            Homeitem2 d2=new Homeitem2(R.drawable.a4,"  源于周杰伦与昆凌的盛世婚礼——Selby Abbey塞尔比教堂。用冰丝布幔打底，高价购入建筑类专业阳光板，使得教堂更为通透，辅以高仿真花艺与垂挂璀璨的珠片装饰，在洒满时光印记的教堂许下爱的箴言，神圣隆重，终身难忘。");
            homeitemList2.add(d2);
            Homeitem2 e2=new Homeitem2(R.drawable.a5,"  源自轻时尚·慢生活的设计理念，透露着生活本真的自然和纯粹。大量白绿色绿植花艺，多种金色铁艺造型，打造出立体的森木漾影效果。加上不同吊顶效果后，氧气饱和度被无限放大，清新且大气。");
            homeitemList2.add(e2);
            Homeitem2 f2=new Homeitem2(R.drawable.a6,"  灵感源自南太平洋“阿达拉”人鱼传说，以清新海洋蓝为主色调，定制桁架鱼尾配合欢悦涌动的海豚与海浪，辅以星星点点银色球饰点缀其中，银光微闪，花架灵动，呈现自然舒爽的视觉体验。");
            homeitemList2.add(f2);}
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
                        Homeitem2 h2=new Homeitem2(R.drawable.test,"  测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 测试 ");
                        homeitemList2.add(h2);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();


    }
}
