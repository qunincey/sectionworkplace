package qunincey.com.sectionwork.adapter;


import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.List;

import qunincey.com.sectionwork.R;
import qunincey.com.sectionwork.bean.NewsBean;
import qunincey.com.sectionwork.utils.URlUtils;
import qunincey.com.sectionwork.viewholder.NewViewHolder;

public class HomeAdapter extends BaseMultiItemQuickAdapter<NewsBean, NewViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeAdapter(List data) {
        super(data);
        addItemType(1, R.layout.item_new1);
        addItemType(2, R.layout.item_new2);
    }

    @Override
    protected void convert(NewViewHolder helper, NewsBean item) {
        switch (helper.getItemViewType()) {
            //获取类型后设置数据
            case 1:
                helper.setText(R.id.textView,item.getNewsName());
                helper.setText(R.id.textView2,item.getNewsName());
                ImageView imageView = helper.getView(R.id.imageView);
                //加上主路径,获取对象图片，类似的同样
                Glide.with(helper.itemView.getContext())
                        .load(URlUtils.IP+item.getImg1()).into(imageView);
                break;

            case 2:
                helper.setText(R.id.textView,item.getNewsName());
                helper.setText(R.id.textView2,item.getNewsName());

                ImageView imageView1 = helper.getView(R.id.imageView);
                ImageView imageView2 = helper.getView(R.id.imageView2);
                ImageView imageView3 = helper.getView(R.id.imageView3);
                Glide.with(helper.itemView.getContext())
                        .load(URlUtils.IP+item.getImg1()).into(imageView1);
                Glide.with(helper.itemView.getContext())
                        .load(URlUtils.IP+item.getImg1()).into(imageView2);
                Glide.with(helper.itemView.getContext())
                        .load(URlUtils.IP+item.getImg1()).into(imageView3);
                break;
        }
    }
}
