package qunincey.com.sectionwork.fragment;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import qunincey.com.sectionwork.R;
import qunincey.com.sectionwork.activity.VideoDetailActivity;
import qunincey.com.sectionwork.adapter.VideoAdapter;
import qunincey.com.sectionwork.bean.VideoBean;
import qunincey.com.sectionwork.utils.JsonParseUtils;
import qunincey.com.sectionwork.utils.NetUtils;
import qunincey.com.sectionwork.utils.URlUtils;

public class VideoFragment extends BaseFragment {

    private VideoAdapter videoAdapter;
    private List<VideoBean> list_video;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        TextView title = view.findViewById(R.id.title);
        title.setText("视频");
        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);

            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);

            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        videoAdapter = new VideoAdapter(R.layout.item_video);
        recyclerView.setAdapter(videoAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(activity,DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        videoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(activity, VideoDetailActivity.class);
                intent.putExtra("videoName",list_video.get(position).getName());
                intent.putExtra("videoImg",list_video.get(position).getImg());
                intent.putExtra("videoIntro",list_video.get(position).getIntro());
                intent.putParcelableArrayListExtra("videoList",
                        (ArrayList<VideoBean.VideoDetailListBean>) list_video.get(position).getVideoDetailList());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        getVideoData();
//        getNewsData();
    }

    private void getVideoData() {
        NetUtils.getDataAsyn(URlUtils.REQUEST_VIDEO_URL, new NetUtils.MyCallBack() {


            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(final String json) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list_video = JsonParseUtils.getList(VideoBean.class, json);
                        videoAdapter.setNewData(list_video);
                    }
                });
            }
        });
    }
}
