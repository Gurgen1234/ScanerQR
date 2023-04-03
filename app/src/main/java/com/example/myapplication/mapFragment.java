package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;

public class mapFragment extends Fragment {


    @SuppressLint("SuspiciousIndentation")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        WebView mapWebView = (WebView) root.findViewById(R.id.mapWebView);
        WebSettings webSettings = mapWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        int permissionStatus = ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.CAMERA);
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
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
        } else {
            ActivityCompat.requestPermissions(this.getActivity(), new String[] {Manifest.permission.READ_CONTACTS},
                    0);
        }


        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        return root;
    }
    public void onResume() {
        super.onResume();
    }
}