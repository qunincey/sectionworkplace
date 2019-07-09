package qunincey.com.sectionwork.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import qunincey.com.sectionwork.R;
import qunincey.com.sectionwork.bean.VideoBean;
import qunincey.com.sectionwork.utils.ImageUtils;
import qunincey.com.sectionwork.utils.URlUtils;


public class VideoAdapter extends BaseQuickAdapter<VideoBean, BaseViewHolder> {
    public VideoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean item) {
        //todo 添加路径
        ImageUtils.setImage(helper.itemView.getContext(), URlUtils.IP+item.getImg(), (ImageView) helper.getView(R.id.imageView));
    }
}
