package com.novetrics.beautifulnavigation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.novetrics.beautifulnavigation.R;
import com.novetrics.beautifulnavigation.adapters.Adapter_Library;

import java.util.ArrayList;
import java.util.Arrays;


public class LibraryFragment extends Fragment {
    View view;


    Adapter_Library customAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_library, container, false);
        // get the reference of RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default HORIZONTAL orientation
//        linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL, false);
        // set a LinearLayoutManager with default vertical orientation
        linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of CustomAdapter to send the reference and data to Adapter
        customAdapter = new Adapter_Library();
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
        return view;
    }
}