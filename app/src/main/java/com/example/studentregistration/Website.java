package com.example.studentregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Website extends AppCompatActivity {

    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        webview = (WebView) findViewById(R.id.cstwebsite);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://cst.edu.bt/index.php/en/");
    }
}