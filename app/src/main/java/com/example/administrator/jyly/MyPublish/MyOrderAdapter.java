package com.example.administrator.jyly.MyPublish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.jyly.Bomb.Order;
import com.example.administrator.jyly.R;

import java.util.List;

public class MyOrderAdapter extends BaseAdapter {
    private Context context;
    private List<Order> list;

    public MyOrderAdapter(Context context, List<Order> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            String news;
            news=list.get(position).getName();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.layout, null);//实例化一个布局文件
            TextView textView=convertView.findViewById(R.id.textView_order);
            textView.setText(news);
        }
        return convertView;
    }
}
