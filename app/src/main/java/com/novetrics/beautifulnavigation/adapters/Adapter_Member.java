package com.novetrics.beautifulnavigation.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.novetrics.beautifulnavigation.R;
import com.novetrics.beautifulnavigation.modal.MemberData;

public class Adapter_Member extends RecyclerView.Adapter {
    View view;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_members,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        ((MyViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return MemberData.title.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // init the item view's
        TextView name,sci_type;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            sci_type = (TextView) itemView.findViewById(R.id.txt_organisation);
            image = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }
    public void bindView(int position)
    {
        name.setText(MemberData.title[position]);
        sci_type.setText("Sci Type : "+MemberData.sci_type[position]);
        image.setImageResource(MemberData.picturepath[position]);
    }

    @Override
    public void onClick(View v)
    {
//      Toast.makeText(image.getContext(),MemberData.title[position], Toast.LENGTH_SHORT).show();
    }
    }
}
