package com.example.ecotrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class GuidelineDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guideline_detail);

        // Handle the back button press
        findViewById(R.id.toolBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Retrieve data from intent
        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        String itemIntro = intent.getStringExtra("itemIntro");
        String itemTips = intent.getStringExtra("itemTips");
        String imageRes = intent.getStringExtra("imageRes"); // Resource name
        String videoUrl = intent.getStringExtra("videoUrl");


        // Find views
        //Toolbar toolbar = findViewById(R.id.toolBar);
        TextView detailName = findViewById(R.id.detailName);
        TextView detailIntro = findViewById(R.id.detailIntro);
        TextView detailTips = findViewById(R.id.detailTips);
        ImageView detailImage = findViewById(R.id.detailImage);
        WebView webView = findViewById(R.id.detailVideo);

        //toolbar.setTitle(itemName);

        // Set the fetched data
        detailName.setText(itemName);
        detailIntro.setText(itemIntro);
        detailTips.setText(itemTips);

        // Load the image using the resource name
        int resId = getResources().getIdentifier(imageRes, "drawable", getPackageName());
        detailImage.setImageResource(resId);

        // Set up WebView for YouTube iframe
        webView.setWebViewClient(new WebViewClient()); // Handle links within the WebView
        webView.setWebChromeClient(new WebChromeClient()); // Optional: to support features like JavaScript
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript for the iframe

        // Load the YouTube video via iframe
        if (videoUrl != null && videoUrl.contains("=")) {
            String videoId = videoUrl.split("=")[1]; // Extract video ID from URL
            String embedUrl = "https://www.youtube.com/embed/" + videoId;
            webView.loadUrl(embedUrl);
        } else {
            Toast.makeText(this, "Invalid video URL", Toast.LENGTH_SHORT).show();
        }
    }
}



