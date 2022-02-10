package com.novetrics.beautifulnavigation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

import com.novetrics.beautifulnavigation.activities.CustomGalleryAdapter;
import com.novetrics.beautifulnavigation.R;

public class GalleryFragment extends Fragment {
    View view;
    Gallery simpleGallery;
    CustomGalleryAdapter customGalleryAdapter;
    ImageView selectedImageView;
    // array of images
    int[] images = {R.drawable.bird1, R.drawable.bird2, R.drawable.bird3,
            R.drawable.bird4,R.drawable.person,R.drawable.logo};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gallery, container, false);

        // Inflate the layout for this fragment
        simpleGallery = (Gallery) view.findViewById(R.id.simpleGallery); // get the reference of Gallery
        selectedImageView = (ImageView) view.findViewById(R.id.selectedImageView); // get the reference of ImageView
        customGalleryAdapter = new CustomGalleryAdapter(getActivity(), images); // initialize the adapter
        simpleGallery.setAdapter(customGalleryAdapter); // set the adapter
        simpleGallery.setSpacing(10);
        // perform setOnItemClickListener event on the Gallery
        simpleGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set the selected image in the ImageView
                selectedImageView.setImageResource(images[position]);

            }
        });

        return view;
    }
}