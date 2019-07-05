package qunincey.com.sectionwork.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import qunincey.com.sectionwork.R;
import qunincey.com.sectionwork.bean.PythonBean;

public class PythonAdapter extends BaseQuickAdapter<PythonBean,BaseViewHolder> {

    public PythonAdapter(int layoutResId) {
        super(layoutResId);

    }

    @Override
    protected void convert(BaseViewHolder helper, PythonBean item) {
        helper.setText(R.id.textView,item.getAddress());
        helper.setText(R.id.textView2,item.getContent());
    }
}
