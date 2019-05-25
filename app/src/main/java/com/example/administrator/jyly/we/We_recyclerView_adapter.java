package com.example.administrator.jyly.we;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.jyly.JoinActivity;
import com.example.administrator.jyly.MyPublish.MyPublishActivity;
import com.example.administrator.jyly.R;
import com.example.administrator.jyly.hunliActivity.HunliActivity;

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
            itemImage = (ImageView) itemView.findViewById(R.id.item_imageView);
            itemText = (TextView) itemView.findViewById(R.id.item_textView);
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
                        Snackbar.make(view, "录像被点击",Snackbar.LENGTH_SHORT).show();
                        Intent intent_toHunli = new Intent(context1, HunliActivity.class);
                        context1.startActivity(intent_toHunli);
                        break;

                    case 3:
                        Intent intent_jion = new Intent(context1, JoinActivity.class);
                        context1.startActivity(intent_jion);
                        break;

                    case 4:
                        QQ();
                        break;
                }
//                if (position==2) {
//                    Snackbar.make(view, "录像被点击",Snackbar.LENGTH_SHORT).show();
//
//            }
                


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


    public void QQ(){
        try {
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=1561072562";//uin是发送过去的qq号码
            context1.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

        } catch (Exception e) {
            Toast.makeText(context1, "您未安装QQ", Toast.LENGTH_SHORT).show();

        }
    }
}
