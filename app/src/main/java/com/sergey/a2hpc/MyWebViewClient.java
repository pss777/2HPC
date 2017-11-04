package com.sergey.a2hpc;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Sergey on 29.10.2017.
 */

public class MyWebViewClient extends WebViewClient {

    // ============================================================================
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (url.startsWith("https://2hpc.ru")) {
            // внутренние ссылки открываем в рамках нашего приложения
            view.loadUrl(url); // или return false; как в офф. док.
        } else {
            // внешние ссылки открываем в браузере
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            view.getContext().startActivity(intent);
        }

        return true;
    }
}
