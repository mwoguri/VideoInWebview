package com.example.videoinwebview

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.webView)
        WebView.setWebContentsDebuggingEnabled(true)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldInterceptRequest( view: WebView?, request: WebResourceRequest?
            ): WebResourceResponse? {
                if (request?.url.toString().contains("1.mp4")) {
                    return WebResourceResponse(
                        "video/mp4",
                        "UTF-8",
                        resources.openRawResource(R.raw.movie)
                    )
                }
                return super.shouldInterceptRequest(view, request)
            }
        }

        webView.loadDataWithBaseURL(
            "http://fake.com",
            """
            <!DOCTYPE html>
            <html>
                <p>Video 1:</p>
                <p><video src="http://fake.com/1.mp4" /></p>
            </html>
        """.trimIndent(), "text/html", StandardCharsets.UTF_8.name(), null
        )
    }
}