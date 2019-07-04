package qunincey.com.sectionwork.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;

import qunincey.com.sectionwork.R;
import qunincey.com.sectionwork.activity.HomeAsUpBaseActivity;

public class NewsDetailActivity extends HomeAsUpBaseActivity {
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdatail);
        String url = getIntent().getStringExtra("url");
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
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(mAgentWeb.handleKeyEvent(keyCode,event)){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
