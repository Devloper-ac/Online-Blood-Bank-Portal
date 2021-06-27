package com.igc.lifeshare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class AboutUs_Activity extends AppCompatActivity {

    WebView mywebview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus_layout);

        mywebview = findViewById(R.id.webview);
        WebSettings webSettings = mywebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mywebview.loadUrl("https://lifeshareofficial.000webhostapp.com/");
    }
}