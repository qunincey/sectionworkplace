package qunincey.com.sectionwork.fragment;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import qunincey.com.sectionwork.R;

import qunincey.com.sectionwork.activity.NewsDetailActivity;
import qunincey.com.sectionwork.activity.PythonActivity;
import qunincey.com.sectionwork.adapter.HomeAdapter;
import qunincey.com.sectionwork.bean.NewsBean;
import qunincey.com.sectionwork.utils.GlideImageLoader;
import qunincey.com.sectionwork.utils.JsonParseUtils;
import qunincey.com.sectionwork.utils.NetUtils;
import qunincey.com.sectionwork.utils.URlUtils;

public class HomeFragment extends BaseFragment {

    private HomeAdapter homeAdapter;
    private Banner banner;

    private List<NewsBean> newsList_news;

    private List<NewsBean> newsList_AD;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        TextView title = view.findViewById(R.id.title);
        title.setText("首页");
        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
                getADData();
                getNewsData();
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
                getNewsData();
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        homeAdapter = new HomeAdapter(null);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        View headView = LayoutInflater.from(activity).inflate(R.layout.head_home, null);
        homeAdapter.addHeaderView(headView);


        banner = headView.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        ArrayList<Integer> images = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            images.add(R.drawable.pic_item_list_default);
        }
        //设置图片集合
        banner.setImages(images);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        View emptyView = LayoutInflater.from(activity).inflate(R.layout.empty_home, null);
        homeAdapter.setEmptyView(emptyView);
        homeAdapter.setHeaderAndEmpty(true);

        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(activity, NewsDetailActivity.class);
                intent.putExtra("url", newsList_news.get(position).getNewsUrl());
                startActivity(intent);
            }
        });

        LinearLayout linearLayout = headView.findViewById(R.id.linearLayout1);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        LinearLayout linearLayout1 = headView.findViewById(R.id.linearLayout1);

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PythonActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();

    }

    private void getNewsData() {

        NetUtils.getDataAsyn(URlUtils.NEWS, new NetUtils.MyCallBack() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                View emptyView = LayoutInflater.from(activity).inflate(R.layout.empty_home, null);
                homeAdapter.setEmptyView(emptyView);
            }

            @Override
            public void onResponse(final String json) throws IOException {
                Log.i("i", json);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        newsList_news = JsonParseUtils.getList(NewsBean.class, json);
                        homeAdapter.setNewData(newsList_news);
                    }
                });
            }
        });


    }

    private void getADData() {
        NetUtils.getDataAsyn(URlUtils.REQUEST_AD_URL, new NetUtils.MyCallBack() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("i",e.getMessage());
            }

            @Override
            public void onResponse(final String json) {
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        newsList_AD = JsonParseUtils.getList(NewsBean.class,json);
                        ArrayList<String> images = new ArrayList<>();
                        for(NewsBean newsBean:newsList_AD){
                            images.add(URlUtils.IP+newsBean.getImg1());
                        }
                        banner.setImages(images);
                        banner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                Intent intent = new Intent(activity, NewsDetailActivity.class);
                                intent.putExtra("url",newsList_AD.get(position).getNewsUrl());
                                activity.startActivity(intent);
                            }
                        });
                        banner.start();
//                        homeAdapter.setNewData(newList);
                    }
                });
            }
        });
    }
}
