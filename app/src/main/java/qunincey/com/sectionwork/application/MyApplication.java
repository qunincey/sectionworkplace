package qunincey.com.sectionwork.application;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,"747cc743ea60d076a8071c417d77a632");
    }
}
