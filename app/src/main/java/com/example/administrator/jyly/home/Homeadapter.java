package com.example.administrator.jyly.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jyly.R;
import com.example.administrator.jyly.myActivity.Activity_webview;

import java.util.List;

public class Homeadapter extends RecyclerView.Adapter<Homeadapter.homeViewholder> {
//    private static final String TAG = "Homeadapter";
    protected Context context;
    protected List<Homeitem> mlist;

    public class homeViewholder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public homeViewholder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.home_imageView);
            textView = itemView.findViewById(R.id.home_textView);
        }
    }
    public Homeadapter( List<Homeitem> list,Context context) {
        this.context=context;
        mlist = list;
    }


    @NonNull
    @Override
    public Homeadapter.homeViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_item_layout, viewGroup, false);
        final Homeadapter.homeViewholder holder=new Homeadapter.homeViewholder(view);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
//                Homeitem homeitem = mlist.get(position);
                switch (position) {
                        case 0:
                            Intent intent_toWebActivity0 = new Intent(context, Activity_webview.class);
                            intent_toWebActivity0.putExtra("webUrl", "https:/www.baidu.com");
                            context.startActivity(intent_toWebActivity0);
                        break;

                        case 1:
                            Intent intent_toWebActivity1 = new Intent(context, Activity_webview.class);
                            intent_toWebActivity1.putExtra("webUrl", "https:/www.baidu.com");
                            context.startActivity(intent_toWebActivity1);
                        break;

                        case 2:
                            Intent intent_toWebActivity2 = new Intent(context, Activity_webview.class);
                            intent_toWebActivity2.putExtra("webUrl", "https:/www.baidu.com");
                            context.startActivity(intent_toWebActivity2);
                        break;

                        case 3:
                            Intent intent_toWebActivity3 = new Intent(context, Activity_webview.class);
                            intent_toWebActivity3.putExtra("webUrl", "https:/");
                            context.startActivity(intent_toWebActivity3);
                        break;

                        case 4:
                            Intent intent_toWebActivity4 = new Intent(context, Activity_webview.class);
                            intent_toWebActivity4.putExtra("webUrl", "https:/");
                            context.startActivity(intent_toWebActivity4);
                        break;

                        case 5:
                            Intent intent_toWebActivity5 = new Intent(context, Activity_webview.class);
                            intent_toWebActivity5.putExtra("webUrl", "https:/");
                            context.startActivity(intent_toWebActivity5);
                        break;

                        case 6:
                            Intent intent_toWebActivity6 = new Intent(context, Activity_webview.class);
                            intent_toWebActivity6.putExtra("webUrl", "https:/");
                            context.startActivity(intent_toWebActivity6);
                        break;

                        case 7:
                            Intent intent_toWebActivity7 = new Intent(context, Activity_webview.class);
                            intent_toWebActivity7.putExtra("webUrl", "https:/");
                            context.startActivity(intent_toWebActivity7);
                        break;

                        case 8:
                            Intent intent_toWebActivity8 = new Intent(context, Activity_webview.class);
                            intent_toWebActivity8.putExtra("webUrl", "https:/");
                            context.startActivity(intent_toWebActivity8);
                        break;

                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull homeViewholder homeViewholder, int i) {
        Homeitem homeitem = mlist.get(i);
        homeViewholder.textView.setText(homeitem.getName());
        homeViewholder.imageView.setImageResource(homeitem.getIcon());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

}
