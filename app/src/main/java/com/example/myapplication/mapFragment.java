package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;

public class mapFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        WebView mapWebView = (WebView) root.findViewById(R.id.mapWebView);
        WebSettings webSettings = mapWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        try {
            InputStream is = this.getContext().getAssets().open("map.html");
            byte[] buffer = new byte[is.available()];
            if (is.read(buffer) <= 0)
            is.close();

            String htmlText = new String(buffer);
            mapWebView.loadDataWithBaseURL("http://ru.yandex.api.yandexmapswebviewexample.ymapapp",
                    htmlText, "text/html", "UTF-8", null
            );
        } catch (IOException e) {
        }

        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        return root;
    }
}