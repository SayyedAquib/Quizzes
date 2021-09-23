package com.example.news

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val url = intent.getStringExtra("URL")
        if (url != null) {
            webView.settings.javaScriptEnabled = true
            webView.settings.userAgentString =
                "Mozila/5.0 (iPhone; U; CPU like MAc OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543A Safari/419.3"
            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = View.GONE
                    webView.visibility = View.VISIBLE
                }
            }
            webView.loadUrl(url)
        }
    }
}