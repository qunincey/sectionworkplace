package qunincey.com.sectionwork.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

import qunincey.com.sectionwork.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash();
    }

    private void splash() {

        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent( SplashActivity.this,MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        timer.schedule(timerTask,3000);
    }
}
