 package com.example.myapplication;

 import android.Manifest;
 import android.content.Intent;
 import android.content.pm.PackageManager;
 import android.net.Uri;
 import android.os.Bundle;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.webkit.WebSettings;
 import android.webkit.WebView;
 import android.webkit.WebViewClient;

 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;
 import androidx.core.app.ActivityCompat;
 import androidx.core.content.ContextCompat;

 import org.denom.Binary;

 import team.sls.camera.CameraParameters;
 import team.sls.camera.CameraView;
 import team.sls.zxing.ZXing;

 public class FragmentChat extends FragmentBase {
     // -----------------------------------------------------------------------------------------------------------------
     @Nullable
     @Override
     public View onCreateView( @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState )
     {
         View rootView = inflater.inflate(R.layout.fragment_chat, container, false );
         WebView chatWebView = (WebView) rootView.findViewById(R.id.chatWebView);
         WebSettings webSettings = chatWebView.getSettings();
         // Включаем js
         webSettings.setJavaScriptEnabled(true);
         webSettings.setUseWideViewPort(true);
         webSettings.setLoadWithOverviewMode(true);
         chatWebView.setWebViewClient(new WebViewClient() {
             @Override
             public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                 // все ссылки, в которых содержится домен
                 // будут открываться внутри приложения
                 if (url.contains("https://portal.timacad.ru")) {
                     return false;
                 }
                 // все остальные ссылки будут спрашивать какой браузер открывать
                 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                 activity.startActivity(intent);
                 return true;
             }
         });
         String urlAddress = "https://portal.timacad.ru/online/";
         chatWebView.loadUrl(urlAddress);
         return rootView;
     }

     @Override
     public void onResume() {
         super.onResume();

     }
 }