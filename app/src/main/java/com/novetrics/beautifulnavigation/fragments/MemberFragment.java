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
import com.novetrics.beautifulnavigation.adapters.Adapter_Member;

import java.util.ArrayList;
import java.util.Arrays;

public class MemberFragment extends Fragment {

    View view;
    Adapter_Member adapter_member;
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
        view = inflater.inflate(R.layout.fragment_member, container, false);
        // get the reference of RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_member);
        // set a LinearLayoutManager with default vertical orientation
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of CustomAdapter to send the reference and data to Adapter
        adapter_member = new Adapter_Member();
        recyclerView.setAdapter(adapter_member); // set the Adapter to RecyclerView
        return view;
    }
}