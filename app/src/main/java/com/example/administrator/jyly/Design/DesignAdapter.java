package com.example.administrator.jyly.Design;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.jyly.R;

import java.util.List;

public class DesignAdapter extends RecyclerView.Adapter<DesignAdapter.myViewHolder> {
    private List<DesignItem> delist;


    public class myViewHolder extends RecyclerView.ViewHolder {
        public ImageView Image;
        public TextView mes;

        public myViewHolder(@NonNull View itemView) {

            super(itemView);
            Image = (ImageView) itemView.findViewById(R.id.imageView_design);
            mes = (TextView) itemView.findViewById(R.id.textView_design);

        }
    }

    public DesignAdapter(List<DesignItem> list) {

        delist = list;
    }

    @NonNull
    @Override
    public DesignAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.design_layout_item, viewGroup, false);
        DesignAdapter.myViewHolder holder = new DesignAdapter.myViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DesignAdapter.myViewHolder viewHolder, int i) {
        DesignItem designItem = delist.get(i);
        viewHolder.Image.setImageResource(designItem.getImage());
        viewHolder.mes.setText(designItem.getMes());

    }

    @Override
    public int getItemCount() {
        return delist.size();
    }


}
