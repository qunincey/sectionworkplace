package qunincey.com.sectionwork.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import qunincey.com.sectionwork.bean.ConstellationBean;
import qunincey.com.sectionwork.bean.NewsBean;
import qunincey.com.sectionwork.bean.PythonBean;

public class JsonParseUtils {

    static Gson gson = new Gson();

    //返回新闻类型对象
    public static List<NewsBean> getNewList(String json){
        Type listType = new TypeToken<List<NewsBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }
    public static List<NewsBean> getADList(String json){
        Type listType = new TypeToken<List<NewsBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }

    public static List<PythonBean> getPythonList(String json){
        Type listType = new TypeToken<List<PythonBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }
    public static List<ConstellationBean> getConstellationList(String json){
        Type listType = new TypeToken<List<ConstellationBean>>(){}.getType();
        return gson.fromJson(json,listType);
    }

}
