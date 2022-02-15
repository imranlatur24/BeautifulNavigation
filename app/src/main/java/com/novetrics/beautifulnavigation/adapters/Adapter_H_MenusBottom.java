package com.novetrics.beautifulnavigation.adapters;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.novetrics.beautifulnavigation.R;
import com.novetrics.beautifulnavigation.fragments.AboutusFragment;
import com.novetrics.beautifulnavigation.fragments.ChangePasswordFragment;
import com.novetrics.beautifulnavigation.fragments.ContactusFragment;
import com.novetrics.beautifulnavigation.fragments.DictionaryFragment;
import com.novetrics.beautifulnavigation.fragments.GalleryFragment;
import com.novetrics.beautifulnavigation.fragments.LibraryFragment;
import com.novetrics.beautifulnavigation.fragments.MemberFragment;
import com.novetrics.beautifulnavigation.fragments.ProfileFragment;
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

        public void bindView(int position) {
            name.setText(Horizontal_Menus2.menus[position]);
            image.setImageResource(Horizontal_Menus2.logopath[position]);

        }

        @Override
        public void onClick(View v) {
            Fragment fm=null;
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            Fragment myFragment;
            switch (getLayoutPosition()) {
                case 0:
                    myFragment = new DictionaryFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, myFragment).addToBackStack(null).commit();
                    break;
                case 1:
                    myFragment = new MemberFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, myFragment).addToBackStack(null).commit();
                    break;
                case 2:
                    myFragment = new ProfileFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, myFragment).addToBackStack(null).commit();
                    break;
                case 3:
                    myFragment = new ChangePasswordFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, myFragment).addToBackStack(null).commit();
                    break;
                case 4:
                    myFragment = new AboutusFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, myFragment).addToBackStack(null).commit();
                    break;
                case 5:
                    myFragment = new ContactusFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, myFragment).addToBackStack(null).commit();
                    break;
                case 6:
                    Toast.makeText(image.getContext(), "calling...", Toast.LENGTH_SHORT).show();
                    break;
                case 7:
                    myFragment = new GalleryFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, myFragment).addToBackStack(null).commit();
                    break;
                case 8:
                    myFragment = new LibraryFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame, myFragment).addToBackStack(null).commit();
                    break;
                default:
//                    Toast.makeText(image.getContext(), "nothing...", Toast.LENGTH_SHORT).show();
                    break;
            }
            if (fm !=null){
                FragmentManager fragmentManager= fm.getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame,fm).commit();
            } else{

//                Log.e(TAG,"Error in creating fragment");
            }
        }

    }
}
