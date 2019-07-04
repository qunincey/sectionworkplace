package qunincey.com.sectionwork.utils;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import qunincey.com.sectionwork.bean.NewsBean;

public class NetUtils {

    public interface  MyCallBack{

        void onFailure(@NotNull Call call, @NotNull IOException e);

        void onResponse(String json) throws IOException;


    }

    public static void getDataAsyn(String url,final MyCallBack myCallBack){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                myCallBack.onFailure(call,e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResponseBody body = response.body();

                if (body!=null) {
                    final String json = body.string();
                    myCallBack.onResponse(json);
                }

            }
        });
    }
}
