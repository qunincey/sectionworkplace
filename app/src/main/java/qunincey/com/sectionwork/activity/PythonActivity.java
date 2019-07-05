package qunincey.com.sectionwork.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import qunincey.com.sectionwork.R;
import qunincey.com.sectionwork.adapter.PythonAdapter;
import qunincey.com.sectionwork.bean.PythonBean;
import qunincey.com.sectionwork.utils.JsonParseUtils;
import qunincey.com.sectionwork.utils.NetUtils;
import qunincey.com.sectionwork.utils.URlUtils;

public class PythonActivity extends HomeAsUpBaseActivity {

    private PythonAdapter pythonAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_python);

        final TextView title = findViewById(R.id.title);
        title.setText("Pythone学科");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        pythonAdapter = new PythonAdapter(R.layout.item_python);

        recyclerView.setAdapter(pythonAdapter);

        NetUtils.getDataAsyn(URlUtils.REQUEST_PYTHON_URL, new NetUtils.MyCallBack() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(final String json) throws IOException {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<PythonBean> list = JsonParseUtils.getList(PythonBean.class,json);
                        pythonAdapter.setNewData(list);
                    }
                });
            }
        });
    }
}
