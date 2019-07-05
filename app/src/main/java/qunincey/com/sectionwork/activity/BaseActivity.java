package qunincey.com.sectionwork.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    protected String TAG;
    private long exitTime;

    private BroadcastReceiver exitBroadcastReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("qunincey.com.sectionwork.activity.exit");
        registerReceiver(exitBroadcastReceiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exitBroadcastReceiver);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        TAG = getClass().getSimpleName();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - exitTime > 2000){
                Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else{
                Intent intent = new Intent();
                intent.setAction("qunincey.com.sectionwork.activity.exit");
                sendBroadcast(intent);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
