package com.lyd.androidNews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by lyd10892 on 2016/5/18.
 */
public class DescriptionActivity extends AppCompatActivity {

    private static final String EXTRA_URL_ID = "url_id";
    private WebView mWebView = null;
    private String mUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_activity);
        initBundle();
        initView();
        initData();
    }

    public static Bundle getBundle(String descUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_URL_ID, descUrl);
        return bundle;
    }

    public void initBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUrl = bundle.getString(EXTRA_URL_ID);
        }
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.news_desc_webView);
        WebSettings setting = mWebView.getSettings();
        setting.setUseWideViewPort(true);
        setting.setLoadWithOverviewMode(true);
        setting.setDisplayZoomControls(false);
    }

    private void initData() {
        mWebView.loadUrl(mUrl);
    }
}
