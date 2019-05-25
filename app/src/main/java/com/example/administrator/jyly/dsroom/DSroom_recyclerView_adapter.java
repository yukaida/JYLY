package com.example.administrator.jyly.dsroom;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.jyly.OtherActivity;
import com.example.administrator.jyly.R;

import java.util.List;

public class DSroom_recyclerView_adapter extends RecyclerView.Adapter<DSroom_recyclerView_adapter.ViewHolder> {
    private List<DSitem> dsItemList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView itemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.dsitem_imageView);
            itemText = (TextView) itemView.findViewById(R.id.dsitem_textView);
        }
    }

    public DSroom_recyclerView_adapter(List<DSitem> itemList) {
        dsItemList = itemList;
    }

    @NonNull
    @Override
    public DSroom_recyclerView_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ds_item_layout, viewGroup, false);
        final DSroom_recyclerView_adapter.ViewHolder holder=new DSroom_recyclerView_adapter.ViewHolder(view);
        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent_pic=new Intent(v.getContext(), OtherActivity.class);
                intent_pic.putExtra("pic", holder.itemImage.getId());




                v.getContext().startActivity(intent_pic,ActivityOptions.makeSceneTransitionAnimation((Activity) v.getContext(), v, "sharedView").toBundle());
                        // 注意这里的sharedView
                        // Content，View（动画作用view），String（和XML一样）
//                ImageView.setImageUri(Uri.fromFile(new File("/sdcard/test.jpg")));


            }
        });



//        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.getContext().startActivity(
//                                                new Intent(view.getContext(), OtherActivity.class),
//                        // 注意这里的sharedView
//                        // Content，View（动画作用view），String（和XML一样）
//                        ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(), view, "sharedView").toBundle());
//            }
//        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DSroom_recyclerView_adapter.ViewHolder holder, int i) {
        DSitem item = dsItemList.get(i);
        holder.itemImage.setImageResource(item.getPic());
        holder.itemText.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return dsItemList.size();
    }


}
