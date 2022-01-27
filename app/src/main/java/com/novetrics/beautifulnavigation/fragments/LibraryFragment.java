package com.novetrics.beautifulnavigation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.novetrics.beautifulnavigation.R;
import com.novetrics.beautifulnavigation.modal.Adapter_Library;

import java.util.ArrayList;
import java.util.Arrays;


public class LibraryFragment extends Fragment {
    View view;
    // ArrayList for person names
    ArrayList personNames = new ArrayList<>(Arrays.asList("Person 1", "Person 2", "Person 3"));
    ArrayList personImages = new ArrayList<>(Arrays.asList(R.drawable.bird1, R.drawable.bird2, R.drawable.bird3));
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
        // set a LinearLayoutManager with default vertical orientation
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of CustomAdapter to send the reference and data to Adapter
        customAdapter = new Adapter_Library();
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
        return view;
    }
}