package com.example.administrator.jyly.we;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jyly.Activity_add.SplashActivity;
import com.example.administrator.jyly.JoinActivity;
import com.example.administrator.jyly.MyPublish.MyPublishActivity;
import com.example.administrator.jyly.R;
import com.example.administrator.jyly.myActivity.Activity_webview;

import java.util.List;

public class We_recyclerView_adapter extends RecyclerView.Adapter<We_recyclerView_adapter.ViewHolder> {
    private List<Item> ItemList;
    private Context context1;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView itemText;
        View myitemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myitemView=itemView;
            itemImage = itemView.findViewById(R.id.item_imageView);
            itemText = itemView.findViewById(R.id.item_textView);
        }
    }
    public We_recyclerView_adapter(Context context,List<Item> itemList) {
        ItemList = itemList;
        context1 = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.we_item_layout, viewGroup, false);
        final ViewHolder holder=new ViewHolder(view);
        holder.myitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Item item = ItemList.get(position);
                switch (position) {

                    case 0:
                        Intent intent_myorder = new Intent(context1, MyPublishActivity.class);
                        context1.startActivity(intent_myorder);
                        break;

                    case 1:
                        Intent intent = new Intent(Intent.ACTION_VIEW,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        context1.startActivity(intent);
                        break;

                    case 2:
                        Intent intent_toHunli = new Intent(context1, Activity_webview.class);
                        intent_toHunli.putExtra("webUrl", "https://m.v.qq.com/x/page/w/m/l/w0822b9rcml.html?");
                        context1.startActivity(intent_toHunli);
                        break;

                    case 3:
                        Intent intent_jion = new Intent(context1, JoinActivity.class);
                        context1.startActivity(intent_jion);
                        break;

                    case 4:
                        AlertDialog.Builder builder = new AlertDialog.Builder(context1);
                        builder.setIcon(R.drawable.kefu);
                        builder.setTitle("选择一个客服");
                        //    指定下拉列表的显示数据
                        final String[] cities = {"客服1", "客服2", "客服3"};
                        //    设置一个下拉的列表选择项
                        builder.setItems(cities, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                switch (which) {
                                    case 0:
                                        QQ("1561072562");
                                        break;
                                    case 1:
                                        QQ("1324702215");
                                        break;
                                    case 2:
                                        QQ("1253896639");
                                        break;
                                }
                            }
                        });
                        builder.show();
                        break;
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Item item = ItemList.get(i);
        holder.itemImage.setImageResource(item.getPic());
        holder.itemText.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }

    public void QQ(String qq){
        try {
            String url = "mqqwpa://im/chat?chat_type=wpa&uin="+qq;//uin是发送过去的qq号码
            context1.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            Toast.makeText(context1, "您未安装QQ", Toast.LENGTH_SHORT).show();
        }
    }
}
