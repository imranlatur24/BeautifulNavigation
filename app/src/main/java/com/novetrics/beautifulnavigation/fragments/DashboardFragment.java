package com.novetrics.beautifulnavigation.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.novetrics.beautifulnavigation.R;
import com.novetrics.beautifulnavigation.adapters.Adapter_H_Menus;
import com.novetrics.beautifulnavigation.adapters.Adapter_Library;
import com.novetrics.beautifulnavigation.modal.Horizontal_Menus;

public class DashboardFragment extends Fragment {

    View view;
    WebView webView;
    Adapter_H_Menus adapter_h_menus;
    RecyclerView recyclerView_horizontal_menus;
    LinearLayoutManager linearLayoutManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_dashboard, container, false);
        webView=(WebView) view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
// specify the url of the web page in loadUrl function
        webView.loadUrl("http://192.168.0.105:8000/");

        recyclerView_horizontal_menus = (RecyclerView) view.findViewById(R.id.recyclerView_horizontal_menus);
        // set a LinearLayoutManager with default HORIZONTAL orientation
        linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerView_horizontal_menus.setLayoutManager(linearLayoutManager);
        adapter_h_menus = new Adapter_H_Menus();
        recyclerView_horizontal_menus.setAdapter(adapter_h_menus); // set the Adapter to RecyclerView
        return  view;
    }
}