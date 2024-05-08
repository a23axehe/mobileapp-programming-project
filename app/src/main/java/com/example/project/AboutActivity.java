package com.example.project;

import android.os.Bundle;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    private WebView aboutWebView;
    private class aboutWebViewClient extends WebViewClient{
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }
    public void showInternalWebPage() {
        aboutWebView.loadUrl("file:///android_asset/about.html");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutWebView = findViewById(R.id.my_webview);

        aboutWebView.getSettings().setJavaScriptEnabled(true);

        WebViewClient webViewClient = new aboutWebViewClient();
        aboutWebView.setWebViewClient(webViewClient);
        showInternalWebPage();
    }
}