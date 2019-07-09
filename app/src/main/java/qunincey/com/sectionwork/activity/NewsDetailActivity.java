package qunincey.com.sectionwork.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;

import qunincey.com.sectionwork.R;

public class NewsDetailActivity extends HomeAsUpBaseActivity {
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdatail);
        String url = getIntent().getStringExtra("url");
        Log.i("i" ,url);
        final TextView titleView = findViewById(R.id.title);
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(linearLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(new WebChromeClient(){
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                        titleView.setText(title);
                    }


                })
                .setWebViewClient(mWebViewClient)
                .createAgentWeb()
                .ready()
                .go(url);


    }

    private com.just.agentweb.WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
            Log.i("Info", "BaseWebActivity onPageStarted");
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(mAgentWeb.handleKeyEvent(keyCode,event)){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
