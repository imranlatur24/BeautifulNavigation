package com.novetrics.beautifulnavigation.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.novetrics.beautifulnavigation.R;
import com.novetrics.beautifulnavigation.modal.Horizontal_Menus;
import com.novetrics.beautifulnavigation.modal.Horizontal_Menus2;

import de.hdodenhof.circleimageview.CircleImageView;
import pl.droidsonroids.gif.GifImageView;


public class Adapter_H_MenusBottom extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_h_menus2,
                parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return Horizontal_Menus2.menus.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // init the item view's
        TextView name;
        CircleImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            image = (CircleImageView) itemView.findViewById(R.id.circle_menus_image2);
            itemView.setOnClickListener(this);
        }
    public void bindView(int position){
            name.setText(Horizontal_Menus2.menus[position]);
            image.setImageResource(Horizontal_Menus2.logopath[position]);
    }

        @Override
        public void onClick(View v) {
//            Toast.makeText(image.getContext(),OutData.title[position], Toast.LENGTH_SHORT).show();
        }
    }
}
