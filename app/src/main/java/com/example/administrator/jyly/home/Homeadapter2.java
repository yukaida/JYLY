package com.example.administrator.jyly.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.jyly.R;
import com.example.administrator.jyly.newsActivity;

import java.util.List;

public class Homeadapter2 extends  RecyclerView.Adapter<Homeadapter2.homeViewholder2>{
    protected Context context;
    protected List<Homeitem2> mlist2;

    public class homeViewholder2 extends RecyclerView.ViewHolder{
        public com.makeramen.roundedimageview.RoundedImageView imageView;
        public TextView textView;
        public View view_home;
        public homeViewholder2(@NonNull View itemView) {
            super(itemView);
            view_home = itemView;
            imageView =itemView.findViewById(R.id.imageView_home_item2);
            textView =itemView.findViewById(R.id.textview_home_item2);
        }
    }
    public Homeadapter2( List<Homeitem2> list,Context contexta) {
        mlist2 = list;
        context=contexta;
    }

    @NonNull
    @Override
    public Homeadapter2.homeViewholder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_item_layout2, viewGroup, false);
        final Homeadapter2.homeViewholder2 holder2=new Homeadapter2.homeViewholder2(view);
        holder2.view_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_to_news=new Intent(context,newsActivity.class);
                context.startActivity(intent_to_news);
            }
        });
        return holder2;
    }

    @Override
    public void onBindViewHolder(@NonNull homeViewholder2 homeViewholder2, int i) {
        Homeitem2 homeitem2 = mlist2.get(i);
        homeViewholder2.textView.setText(homeitem2.getName());
        homeViewholder2.imageView.setImageResource(homeitem2.getIcon());
    }



    @Override
    public int getItemCount() {
        return mlist2.size();
    }


}
