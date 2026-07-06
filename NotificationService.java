// پیدا کردن WebView
WebView webView = (WebView) findViewById(R.id.webview1);

// ===== تنظیمات فوق سریع برای ChatGPT =====
WebSettings webSettings = webView.getSettings();

// تنظیمات پایه ضروری
webSettings.setJavaScriptEnabled(true);
webSettings.setDomStorageEnabled(true);
webSettings.setLoadWithOverviewMode(true);
webSettings.setUseWideViewPort(true);
webSettings.setBuiltInZoomControls(false);
webSettings.setDisplayZoomControls(false);
webSettings.setSupportMultipleWindows(false);

// ===== افزایش سرعت بارگذاری =====
webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
webSettings.setAppCacheEnabled(true);
webSettings.setDatabaseEnabled(true);
webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

// ===== غیرفعال کردن موارد اضافی برای سرعت =====
webSettings.setLoadsImagesAutomatically(true);
webSettings.setBlockNetworkImage(false);
webSettings.setBlockNetworkLoads(false);

// ===== User-Agent شبیه مرورگر واقعی =====
webSettings.setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

// ===== فعال کردن کوکی‌ها برای گوگل =====
CookieManager cookieManager = CookieManager.getInstance();
cookieManager.setAcceptCookie(true);
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    cookieManager.setAcceptThirdPartyCookies(webView, true);
}

// ===== تنظیم هاردور برای سرعت بیشتر =====
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
}

// ===== WebChromeClient =====
webView.setWebChromeClient(new WebChromeClient() {
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        // نمایش پیشرفت بارگذاری
    }
});

// ===== WebViewClient اصلی با قابلیت تغییر تم به مشکی =====
webView.setWebViewClient(new WebViewClient() {
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        
        // ===== بهینه‌سازی و تغییر تم به مشکی کامل =====
        String javascript = "javascript:(function() { " +
            "try { " +
            "   // تغییر پس‌زمینه به مشکی کامل " +
            "   document.body.style.backgroundColor = '#000000'; " +
            "   document.body.style.color = '#ffffff'; " +
            "   " +
            "   // تغییر همه عناصر به مشکی " +
            "   var allElements = document.querySelectorAll('*'); " +
            "   for(var i = 0; i < allElements.length; i++) { " +
            "       var el = allElements[i]; " +
            "       el.style.backgroundColor = '#000000'; " +
            "       el.style.color = '#ffffff'; " +
            "       el.style.borderColor = '#333333'; " +
            "       el.style.boxShadow = 'none'; " +
            "   } " +
            "   " +
            "   // تغییر رنگ اسکرول بار " +
            "   var style = document.createElement('style'); " +
            "   style.innerHTML = ' " +
            "       ::-webkit-scrollbar { width: 8px; background: #000000; } " +
            "       ::-webkit-scrollbar-thumb { background: #333333; border-radius: 4px; } " +
            "       ::-webkit-scrollbar-track { background: #000000; } " +
            "       * { animation: none !important; transition: none !important; } " +
            "       input, textarea, button { background: #1a1a1a !important; color: #ffffff !important; border: 1px solid #333333 !important; } " +
            "       a { color: #4a9eff !important; } " +
            "   '; " +
            "   document.head.appendChild(style); " +
            "   " +
            "   // حذف انیمیشن‌ها برای سرعت " +
            "   var style2 = document.createElement('style'); " +
            "   style2.innerHTML = '* { animation: none !important; transition: none !important; }'; " +
            "   document.head.appendChild(style2); " +
            "   " +
            "   // پیدا کردن دکمه ورود با گوگل " +
            "   var buttons = document.querySelectorAll('button, a'); " +
            "   for(var i = 0; i < buttons.length; i++) { " +
            "       var btn = buttons[i]; " +
            "       if(btn.textContent && btn.textContent.toLowerCase().includes('google')) { " +
            "           btn.style.backgroundColor = '#1a1a1a'; " +
            "           btn.style.color = '#ffffff'; " +
            "           btn.style.border = '1px solid #333333'; " +
            "           setTimeout(function() { btn.click(); }, 1500); " +
            "           break; " +
            "       } " +
            "   } " +
            "   console.log('Dark theme applied and speed optimized'); " +
            "} catch(e) { console.log('Error:', e); } " +
        "})()";
        view.loadUrl(javascript);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // اجازه بارگذاری همه لینک‌ها
        view.loadUrl(url);
        return true;
    }
});

// ===== پاک کردن کش =====
webView.clearCache(true);
webView.clearHistory();

// ===== هدرهای بهینه =====
Map<String, String> headers = new HashMap<>();
headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
headers.put("Accept-Language", "fa-IR,fa;q=0.9,en-US;q=0.8,en;q=0.7");
headers.put("Cache-Control", "max-age=0");
headers.put("Connection", "keep-alive");

// ===== بارگذاری نهایی =====
webView.loadUrl("https://chatgpt.com/", headers);
