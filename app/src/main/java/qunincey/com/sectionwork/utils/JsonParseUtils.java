package qunincey.com.sectionwork.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import qunincey.com.sectionwork.bean.ConstellationBean;
import qunincey.com.sectionwork.bean.NewsBean;
import qunincey.com.sectionwork.bean.PythonBean;

public class JsonParseUtils {

    static Gson gson = new Gson();

    private static class MyParameterizedType implements ParameterizedType{
        Class raw;

        MyParameterizedType(Class raw){
            this.raw = raw;
        }
        @NotNull
        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{raw};
        }

        @NotNull
        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }

    //返回新闻类型对象
    public static <T> List<T> getList(Class<T> t,String json){
//        Type listType = new TypeToken<List<NewsBean>>(){}.getType();
//        return gson.fromJson(json,listType);

        Type listType = new MyParameterizedType(t);
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
